package com.example.alostsoul;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void addClicked(View v) {
		Intent i = new Intent(this, Add.class);
		startActivity(i);
	}
	
	public void playClicked(View v) {
		Intent j = new Intent(this, Game.class);
		startActivity(j);
	}

	public void aboutClicked(View v) {
		Intent k = new Intent(this, About.class);
		startActivity(k);
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
