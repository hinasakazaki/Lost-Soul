package com.example.alostsoul;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Add extends Activity {
	public String address;
	static String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
	}
	
//	public void get_type(View v){
//		ImageButton heart = (ImageButton)findViewById(R.id.imageButton3);
//		RadioButton drink = (RadioButton)findViewById(R.id.imageButton4);
//		RadioButton house = (RadioButton)findViewById(R.id.imageButton5);
//		
//		
//		if (heart.isClicked()){
//			type = "heart";
//		}
//		
//		else if (drink.isClicked()){
//			type = "drink";	
//		}
//		
//		else if (house.isChecked()){
//			type = "home";
//		}
//	
	public void selectHeart(View v) {
		type = "heart";
	}
	public void selectDrink(View v) {
		type = "drink";
	}
	public void selectHome(View v) {
		type = "home";
	}
	
	public void cancel(View v) {
		//don't do anything
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
	
	FindPlaceTask task;
	public void ok(View v) throws InterruptedException, ExecutionException {
		
		EditText inputTextbox = (EditText)findViewById(R.id.editText1);
		
		if (inputTextbox.getText().toString().length() > 0){
			address = (inputTextbox.getText().toString());
			task = new FindPlaceTask();    
		}
		
		task.execute(address);
		//process stuff into the game map
		int[] coord = task.get();
		int lng = coord[0];
		int lat = coord[1];
		Intent j = new Intent(this, Game.class);
		
		j.putExtra("longitude", lng);
		j.putExtra("latitude", lat);
		j.putExtra("type", type);
		Log.v("blah", type);
		startActivity(j);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}	
}

//Use Location API Text Search Requests
class FindPlaceTask extends AsyncTask<String, Void, int[]> {
	
	private final String API_KEY = "AIzaSyAYs1chpNRjkmN6sf23pAtyjmYeLyYfTzU";
	JSONObject jsonresult = null;
	
	@Override
	protected int[] doInBackground(String... params) {
		if (params != null) {
			final String request = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + Uri.encode(params[0]) + "&key=" + API_KEY + "&sensor=false";
			HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            
            Log.d("Iruvchris", ""+ request);
            
            
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(request));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    responseString = out.toString();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            
            if(responseString != null){
            //now parse!! :D :D :D 
               
            	try {
    				jsonresult = (JSONObject) new JSONTokener(responseString).nextValue();
    				
    				
    				JSONArray res = jsonresult.getJSONArray("results");	
    				if (res.length() > 0){
    				
    					JSONObject obj = (JSONObject) res.get(0);
    					
    					JSONObject geom = obj.getJSONObject("geometry");
    					
    					JSONObject loc = geom.getJSONObject("location");
    					
    					int lng = loc.getInt("lng");
    					
    					int lat = loc.getInt("lat");
    					
    					int[] coord = {lng, lat};
    					
    					return coord;
    				}
    				
    			} catch (JSONException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} 	
            
			
		
            }
		
		}
		return null;}
}