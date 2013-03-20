package gov.nasa.larc.develop.hivelens;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {
	MediaPlayer splashSound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Make splash full screen 
        requestWindowFeature(Window.FEATURE_NO_TITLE);																		
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);	
		
        // Set view to splash screen 
		setContentView(R.layout.activity_splash);
		
		// Start sound
		splashSound = MediaPlayer.create(Splash.this, R.raw.hive_lens_splash);
		splashSound.start();
		
		// Create a timer
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(3000); // wait 1.5 seconds
				} catch (InterruptedException e){
					e.printStackTrace();
				} finally{
					// Open Hive Lens activity
					Intent openHIVELens = new Intent("gov.nasa.larc.develop.hivelens.HIVELens");
					startActivity(openHIVELens);
				}
			}
		};
		timer.start();
	}

	// Stops the intent when splash screen is over
	@Override
	protected void onPause() {
		super.onPause();
		splashSound.release();
		finish();
	}
	

}
