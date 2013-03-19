package com.farmer.ttstest;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener; 

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

	    private TextToSpeech tts;
	    private Button copyMe;
	    private Button repeatMe;
	    private Button modisT;
	    private Button clearIt;
	    private EditText txtText;
	    private RelativeLayout reL;
	    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	    Dialog settingsDialog;
	    String name = "";
	    Boolean nameTrigger = false;
	    Boolean commandViewed = false;
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	 
	        tts = new TextToSpeech(this, this);
	 
	        copyMe = (Button) findViewById(R.id.button1);
	        
	        clearIt = (Button) findViewById(R.id.button4);
	        
	        repeatMe = (Button) findViewById(R.id.button2);
	        
	        modisT = (Button) findViewById(R.id.button3);
	 
	        txtText = (EditText) findViewById(R.id.txtText);
	        
	        reL = (RelativeLayout) findViewById(R.id.relLay1);
	 
	        final CheckBox cb = (CheckBox) findViewById(R.id.CheckBox01);
	        cb.setOnCheckedChangeListener(new OnCheckedChangeListener() { 

	        	@Override 
	        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { 
	        	if (buttonView.isChecked()) { 
	        	Toast.makeText(getBaseContext(), "Checked", 
	        	Toast.LENGTH_SHORT).show(); 
	        	txtText.setVisibility(View.GONE);
	        	cb.setText("Uncheck to show text field");
	        	} 
	        	else 
	        	{ 
	        	Toast.makeText(getBaseContext(), "UnChecked", 
	        	Toast.LENGTH_SHORT).show();
	        	txtText.setVisibility(View.VISIBLE);
	        	cb.setText("Check to hide text field");
	        	} 

	        	} 
	        	}); 
	    }
	 
	    @Override
	    public void onDestroy() {
	        // Don't forget to shutdown tts!
	        if (tts != null) {
	            tts.stop();
	            tts.shutdown();
	        }
	        super.onDestroy();
	    }
	 
	    @Override
	    public void onInit(int status) {
	 
	        if (status == TextToSpeech.SUCCESS) {
	 
	            int result = tts.setLanguage(Locale.US);
	 
	            if (result == TextToSpeech.LANG_MISSING_DATA
	                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
	                Log.e("TTS", "This Language is not supported");
	            } else {
	            	tts.speak("App Initialized", TextToSpeech.QUEUE_FLUSH, null);
	            }
	 
	        } else {
	            Log.e("TTS", "Initilization Failed!");
	        }
	 
	    }
	 
	    private void speakOut() {
	 
	        String text = txtText.getText().toString();
	        if(text.equals(""))
	        {
	        	tts.speak("You know maybe you should type or say something first", TextToSpeech.QUEUE_FLUSH, null);
	        }
	        else{
	        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);}
	    }
	    
	    public void repeat(View v) {
	    	speakOut();
	    }
	    
	    public void clear(View v) {	   	 
	    	txtText.setText("");
	    }
	    
	    public void commands(View v) {
	    	txtText.setText("Command List Opened");
	    	if(commandViewed == true)
	    	{
	    		final Random myRandom = new Random();
	    		int select = myRandom.nextInt(3);
	    		if(select == 0)
	    		{
	    			tts.speak("Commands", TextToSpeech.QUEUE_FLUSH, null);
		        	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	settingsDialog = new Dialog(this);
		        	settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		        	settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.commands
		        	        , null));
		        	settingsDialog.show();
	    		}
	    		if(select == 1)
	    		{
	    			tts.speak("Try the mowdis command", TextToSpeech.QUEUE_FLUSH, null);
		        	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	settingsDialog = new Dialog(this);
		        	settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		        	settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.commands
		        	        , null));
		        	settingsDialog.show();
	    		}
	    		if(select == 2)
	    		{
	    			tts.speak("Try asking me your name", TextToSpeech.QUEUE_FLUSH, null);
		        	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	settingsDialog = new Dialog(this);
		        	settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		        	settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.commands
		        	        , null));
		        	settingsDialog.show();
	    		}
	    		
	    	}
	    	else{
	        	tts.speak("The following are commands I recognize", TextToSpeech.QUEUE_FLUSH, null);
	        	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	settingsDialog = new Dialog(this);
	        	settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	        	settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.commands
	        	        , null));
	        	settingsDialog.show();
	        	commandViewed = true;
	    	}
	    }
	    
	    public void listenUp(View v){
	    	switch (v.getId()) {
            case R.id.button1:
                startVoiceRecognitionActivity();
              break;}
	    }
	    public void startVoiceRecognitionActivity() {
	        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
	                .getPackage().getName());
	        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
	                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
	        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
	        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
		}
	    public void dismissListener(View v){
	    	settingsDialog.dismiss();
	    	tts.stop();
	    }
	    public void satNetModis(View v){
	    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://develop.larc.nasa.gov/Boiler/Manta/SatNet3/modis.html"));
	    	startActivity(browserIntent);
	    }
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	
	    	String wordStr = null;
	        
	    	if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
	       
	            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	            wordStr = matches.get(0);
	            txtText.setText(wordStr);
	            
		        if(wordStr.equals("I'm an idiot")||wordStr.equals("I am an idiot"))
		        {
		        	tts.speak("You're an idiot", TextToSpeech.QUEUE_FLUSH, null);
		        }
		        else if(wordStr.equals("turn blue"))
		        {
		        	txtText.setText("Command Recognized");
		        	tts.speak("Blue. Set.", TextToSpeech.QUEUE_FLUSH, null);
		        	reL.setBackgroundColor(Color.argb(255, 0, 0, 255));
		        	txtText.setTextColor(Color.argb(255, 255, 255, 255));
		        	
		        }
		        else if(wordStr.equals("turn red"))
		        {
		        	txtText.setText("Command Recognized");
		        	tts.speak("Red. Set.", TextToSpeech.QUEUE_FLUSH, null);
		        	reL.setBackgroundColor(Color.argb(255, 255, 0, 0));
		        	txtText.setTextColor(Color.argb(255, 255, 255, 255));
		        	
		        }
		        else if(wordStr.equals("turn green"))
		        {
		        	txtText.setText("Command Recognized");
		        	tts.speak("Green. Set.", TextToSpeech.QUEUE_FLUSH, null);
		        	reL.setBackgroundColor(Color.argb(255, 0, 255, 0));
		        	txtText.setTextColor(Color.argb(255, 255, 255, 255));
		        	
		        }
		        else if(wordStr.equals("change back"))
		        {
		        	txtText.setText("Command Recognized");
		        	tts.speak("Color returned to normal", TextToSpeech.QUEUE_FLUSH, null);
		        	reL.setBackgroundColor(Color.argb(255, 255, 255, 255));
		        	txtText.setTextColor(Color.argb(255, 0, 0, 0));
		        }
		        else if(wordStr.equals("I hate you"))
		        {
		        	tts.speak("I hate you too. Goodbye.", TextToSpeech.QUEUE_FLUSH, null);
		        	try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	finish();
		        }
		        else if(wordStr.equals("exit application")||wordStr.equals("close"))
		        {
		        	tts.speak("OK. Goodbye.", TextToSpeech.QUEUE_FLUSH, null);
		        	try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	finish();
		        }
		        else if(wordStr.contains("modis")||wordStr.contains("modis")||wordStr.contains("notice")){
		        	txtText.setText("Request Received");
		        	String text1 = txtText.getText().toString();
		        	tts.speak(text1, TextToSpeech.QUEUE_FLUSH, null);
		        	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	String text = "mowdis is a key instrument aboard the Terra and ockwa satellites.It views the entire Earth's surface every 1 to 2 days, acquiring data in 36 spectral bands, or groups of wavelengths. Touch Sat Net to learn more, otherwise, Dismiss.";
		        	settingsDialog = new Dialog(this);
		        	settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		        	settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout
		        	        , null));
		        	settingsDialog.show();
		        	tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		        }
		        else if(nameTrigger == true)
		        {
		        	String text = txtText.getText().toString();
		        	name = text;
			        tts.speak("Hello, " + name, TextToSpeech.QUEUE_FLUSH, null);
			        nameTrigger = false;
		        }
		        else if(wordStr.equals("that's not my name") && !name.equals(""))
		        {
		        	tts.speak("Oh! I thought I was still talking to " + name +". What is your name?",TextToSpeech.QUEUE_FLUSH, null);
		        	name ="";
		        	nameTrigger=true;
		        }
		        else if((wordStr.equals("what is my name")||wordStr.equals("what's my name"))  && !name.equals(""))
		        {
		        	txtText.setText("!!!");
		        	if(name.equals("David")){
		        		String text = "Your name is " + name +", better known as Doctor Game";
			        	tts.speak(text,TextToSpeech.QUEUE_FLUSH, null);
		        	}
		        	else{
		        	String text = "Your name is " + name;
		        	tts.speak(text,TextToSpeech.QUEUE_FLUSH, null);
		        	}
		        }
		        else if((wordStr.equals("what is my name")||wordStr.equals("what's my name")) && nameTrigger == false)
		        {
		        	nameTrigger = true;
		        	txtText.setText("???");
		        	String text = "I don't know. Tell me your name.";
		        	tts.speak(text,TextToSpeech.QUEUE_FLUSH, null);
		        }
		        else if(nameTrigger == true)
		        {
		        	String text = txtText.getText().toString();
		        	name = text;
			        tts.speak("Hello, " + name, TextToSpeech.QUEUE_FLUSH, null);
		        }
		        else{	
		        			speakOut();
		        }  
	        }
	    	else if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_CANCELED) {
	    		tts.speak("Y U No Speak?", TextToSpeech.QUEUE_FLUSH, null);
	    	}

	    }
	}
