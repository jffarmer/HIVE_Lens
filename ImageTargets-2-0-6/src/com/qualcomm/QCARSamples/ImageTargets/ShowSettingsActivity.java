package com.qualcomm.QCARSamples.ImageTargets;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;

public class ShowSettingsActivity extends Activity {

	SharedPreferences sharedPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_settings);
		
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		 
		StringBuilder builder = new StringBuilder();
		 
		builder.append("\n" + sharedPrefs.getBoolean("perform_updates", false));
		builder.append("\n" + sharedPrefs.getString("updates_interval", "-1"));
		builder.append("\n" + sharedPrefs.getBoolean("enable_sound", true));
		 
		TextView settingsTextView = (TextView) findViewById(R.id.settings_text_view);
		settingsTextView.setText(builder.toString());
	}
}
