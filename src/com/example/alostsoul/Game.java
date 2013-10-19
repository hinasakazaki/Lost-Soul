package com.example.alostsoul;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class Game extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String provider = "";
        int ourlat = 0;
        int ourlong = 0;
        LocationManager loc = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        if (loc.getAllProviders().size()> 0){
        	provider = loc.getAllProviders().get(0);
        	Location here = loc.getLastKnownLocation(provider);
        	ourlat = (int) here.getLatitude(); 
            ourlong = (int) here.getLongitude();
        }
        
        Log.v("ourlat", ""+ourlat);
        Log.v("ourlonng", ""+ourlong);
        
        Bundle extras = this.getIntent().getExtras();
		int latitude = extras.getInt("latitude");
		int longitude = extras.getInt("longitude");
		String type = extras.getString("type");
		Log.v("nowhowaboutthis", type);
		
		Log.v("destlat", ""+latitude);
        Log.v("destlong", ""+longitude);
		
		if ((ourlat < latitude) && (ourlong < longitude)){
			Log.v("did it get past here", type);
			if (type.equals("heart")){
				Log.v("what about here", type);
				((ImageView)findViewById(R.id.NE)).setImageResource(R.drawable.heart);
				
			}
			
			if (type.equals("home")){
				((ImageView)findViewById(R.id.NE)).setImageResource(R.drawable.home);
			}
			
			if (type.equals("drink")){
				((ImageView)findViewById(R.id.NE)).setImageResource(R.drawable.drink);
			}
			
			 
		}
		else if ((ourlat < latitude) && (ourlong > longitude)){
			Log.v("did it get past here", type);
			if (type == "heart"){
				Log.v("what about here", type);
				((ImageView)findViewById(R.id.SE)).setImageResource(R.drawable.heart);
				
			}
			
			if (type == "home"){
				((ImageView)findViewById(R.id.SE)).setImageResource(R.drawable.home);
			}
			
			if (type == "drink"){
				((ImageView)findViewById(R.id.SE)).setImageResource(R.drawable.drink);
			}
			
			
		}
		else if ((ourlat > latitude) && (ourlong > longitude)){
			Log.v("did it get past here", type);
			if (type == "heart"){
				Log.v("what about here", type);
				((ImageView)findViewById(R.id.SW)).setImageResource(R.drawable.heart);
				
			}
			
			if (type == "home"){
				((ImageView)findViewById(R.id.SW)).setImageResource(R.drawable.home);
			}
			
			if (type == "drink"){
				((ImageView)findViewById(R.id.SW)).setImageResource(R.drawable.drink);
			}
			
		}
		else if ((ourlat > latitude) && (ourlong < longitude)){
			Log.v("did it get past here", type);
			if (type == "heart"){
				Log.v("what about here", type);
				((ImageView)findViewById(R.id.NW)).setImageResource(R.drawable.heart);
				
			}
			
			if (type == "home"){
				((ImageView)findViewById(R.id.NW)).setImageResource(R.drawable.home);
			}
			
			if (type == "drink"){
				((ImageView)findViewById(R.id.NW)).setImageResource(R.drawable.drink);
			}
			
		}
		else{
			Log.v("did it get past here5", type);
			if (type.equals("heart")){
				Log.v("what about here", type);
				((ImageView)findViewById(R.id.HERE)).setImageResource(R.drawable.heart);
				
			}
			
			if (type.equals("home")){
				((ImageView)findViewById(R.id.HERE)).setImageResource(R.drawable.home);
			}
			
			if (type.equals("drink")){
				((ImageView)findViewById(R.id.HERE)).setImageResource(R.drawable.drink);
			}
			
		}
        
    }
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }
    
}
