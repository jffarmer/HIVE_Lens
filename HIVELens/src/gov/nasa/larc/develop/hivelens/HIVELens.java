package gov.nasa.larc.develop.hivelens;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HIVELens extends Activity {

	Button            button;
	MediaPlayer       player;
	View              hivelayout;
	SharedPreferences sharedPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hivelens);
		button      = (Button) findViewById(R.id.button1);
		player      = MediaPlayer.create(this, R.raw.button_push);
		hivelayout  =  findViewById(R.id.layout);
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	}

	public void lensButtonPressed(View v) {
		// If sound is enabled, played sound for button pressed
		if(sharedPrefs.getBoolean("enable_sound", true) == true) {
			player.start();
		}
	}
	
	public void chatButtonPressed(View v) {
		// If sound is enabled, played sound for button pressed
		if(sharedPrefs.getBoolean("enable_sound", true) == true) {
			player.start();
		}
	}
	
	@Override
	protected void onDestroy() {
		player.release();
		super.onDestroy();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu, this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hivelens, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
		// Launches intent based on the menu item ID 
        switch (item.getItemId()) {
        case R.id.menu_settings:
        	startActivity(new Intent(this, Settings.class));
            return true;
 
        case R.id.menu_about:
        	startActivity(new Intent(this, About.class));
            return true;
            
        case R.id.menu_help:
        	startActivity(new Intent(this, Help.class));
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }  
	
}
