package com.qualcomm.QCARSamples.ImageTargets;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutScreen extends Activity {

	private Button            button;
	private Button 			  lensButton;
	private MediaPlayer       player;
	private View              hivelayout;
	private SharedPreferences sharedPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hivelens);
		button      = (Button) findViewById(R.id.button1);
		lensButton  = (Button) findViewById(R.id.button2);
		player      = MediaPlayer.create(this, R.raw.button_push);
		hivelayout  =  findViewById(R.id.layout);
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	}

	public void onLensButtonClick(View v) {
		//Toast.makeText(this, "WORKS", Toast.LENGTH_SHORT).show();
		if(sharedPrefs.getBoolean("enable_sound", true) == true) {
			player.start();
		}
		
//		switch (v.getId())
//		{
//		case R.id.button_start:
			startARActivity();
//			break;
//		}
	}
	
	/** Starts the ImageTargets main activity */
	private void startARActivity()
	{
		Intent i = new Intent(this, ImageTargets.class);
		startActivity(i);
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
        	//Toast.makeText(this, "WORKING", Toast.LENGTH_SHORT).show();
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





/*==============================================================================
//            Copyright (c) 2010-2012 QUALCOMM Austria Research Center GmbH.
//            All Rights Reserved.
//            Qualcomm Confidential and Proprietary
//
//@file
//    AboutScreen.java
//
//@brief
//    About Screen Activity for the ImageTargets sample application
//
//==============================================================================*/
//
//package com.qualcomm.QCARSamples.ImageTargets;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.media.MediaPlayer;
//import android.media.audiofx.BassBoost.Settings;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class AboutScreen extends Activity //implements OnClickListener
//{
//    //private TextView mAboutText;
//    //private Button mStartButton;
//
//	private Button            button;
//	private MediaPlayer       player;
//	private View              hivelayout;
//	private SharedPreferences sharedPrefs;
//
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.about_screen);
//        setContentView(R.layout.activity_hivelens);
//        
//        
//        button      = (Button) findViewById(R.id.button1);
//		player      = MediaPlayer.create(this, R.raw.button_push);
//		hivelayout  =  findViewById(R.id.layout);
//		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//        
////        mAboutText = (TextView) findViewById(R.id.about_text);
////        mAboutText.setText(Html.fromHtml(getString(R.string.about_text)));
////        mAboutText.setMovementMethod(LinkMovementMethod.getInstance());
////
////        // Setups the link color
////        mAboutText.setLinkTextColor(getResources().getColor(
////                R.color.holo_light_blue));
////
////        mStartButton = (Button) findViewById(R.id.button1);
////        mStartButton.setOnClickListener(this);
//    }
//
//
//    /** Starts the ImageTargets main activity */
////    private void startARActivity()
////    {
////        Intent i = new Intent(this, ImageTargets.class);
////        startActivity(i);
////    }
//
//
////    public void onClick(View v)
////    {
////        switch (v.getId())
////        {
////        case R.id.button_start:
////            startARActivity();
////            break;
////        }
////    }
//    
//    
//    public void lensButtonPressed(View v) {
//		// If sound is enabled, played sound for button pressed
//		if(sharedPrefs.getBoolean("enable_sound", true) == true) {
//			player.start();
//		}
//	}
//	
//	public void chatButtonPressed(View v) {
//		// If sound is enabled, played sound for button pressed
//		if(sharedPrefs.getBoolean("enable_sound", true) == true) {
//			player.start();
//		}
//	}
//	
//	@Override
//	protected void onDestroy() {
//		player.release();
//		super.onDestroy();
//	}
//	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu, this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_hivelens, menu);
//		return true;
//	}
//	
//	@Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//		// Launches intent based on the menu item ID 
//        switch (item.getItemId()) {
//        case R.id.menu_settings:
//        	startActivity(new Intent(this, Settings.class));
//            return true;
// 
//        case R.id.menu_about:
//        	startActivity(new Intent(this, About.class));
//            return true;
//            
//        case R.id.menu_help:
//        	startActivity(new Intent(this, Help.class));
//            return true;
// 
//        default:
//            return super.onOptionsItemSelected(item);
//        }
//    }  
//	
//
//}
