/*==============================================================================
            Copyright (c) 2010-2012 QUALCOMM Austria Research Center GmbH.
            All Rights Reserved.
            Qualcomm Confidential and Proprietary

@file
    ImageTargetsSplashScreen.java

@brief
    Splash screen Activity for the ImageTargets sample application
    Splash screen is displayed for 2 seconds, then the About Screen is shown.

==============================================================================*/

package com.qualcomm.QCARSamples.ImageTargets;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class ImageTargetsSplashScreen extends Activity {
	MediaPlayer splashSound;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Sets the Splash Screen Layout

//		// Make splash full screen
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// CHANGED FROM DEFAULT SPLASH SCREEN TO HIVE SPLASH SCREEN
		// setContentView(R.layout.splash_screen);
		setContentView(R.layout.activity_splash);

		// Start sound
		splashSound = MediaPlayer.create(ImageTargetsSplashScreen.this,
				R.raw.hive_lens_splash);
		splashSound.start();

		// Generates a Handler to launch the About Screen
		// after 2 seconds
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				// Starts the About Screen Activity
				startActivity(new Intent(ImageTargetsSplashScreen.this,
						AboutScreen.class));
			}
		}, 3000L);
	}

	// Stops the intent when splash screen is over
	@Override
	protected void onPause() {
		super.onPause();
		splashSound.release();
		finish();
	}

	public void onConfigurationChanged(Configuration newConfig) {
		// Manages auto rotation for the Splash Screen Layout
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.splash_screen);
	}
}
