package com.farmer.ttstest;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import android.os.Build;
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
	    Boolean helpTrigger = false;
	 
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

	    public String getDeviceName() {
	    	  //String manufacturer = Build.MANUFACTURER;
	    	  String model = Build.MODEL;
	    	  return model;
	    	}
	    public String getDeviceCodeName() {
	    	  //String manufacturer = Build.MANUFACTURER;
	    	  String codeName = Build.BOARD;
	    	  return codeName;
	    	}
	    public String getDeviceSerial() {
	    	  //String manufacturer = Build.MANUFACTURER;
	    	  String serial = Build.SERIAL;
	    	  return serial;
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
		        else if((wordStr.equals("help") && helpTrigger == false))
		        {
		        	helpTrigger = true;
		        	txtText.setText("I can help!");
		        	String text = "Say the number of a command you want to know more about.";
		        	tts.speak(text,TextToSpeech.QUEUE_FLUSH, null);
		        }
		        else if(wordStr.equals("1") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("RGB means red, green, and blue. Say, turn, followed by a color and I will set the background to your chosen color. Say change back to reset my background color.",TextToSpeech.QUEUE_FLUSH, null);
		        }  
		        else if(wordStr.equals("2") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will close myself upon hearing this command.",TextToSpeech.QUEUE_FLUSH, null);
		        }  
		        else if(wordStr.equals("3") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will close myself upon hearing this command.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("4") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will close myself upon hearing this command. P S. You Suck.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("5") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I refuse to copy that word for word.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("6") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will ask or tell you your name depending on whether or not I know it.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("7") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("Say this to change your name.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("8") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will tell you about the mowdis instrument.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("9") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will tell you what model phone I am.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("10") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will tell you my serial number and board name.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("11") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will tell you my serial number and board name.",TextToSpeech.QUEUE_FLUSH, null);
		        }
		        else if(wordStr.equals("12") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will tell you today's date.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("13") && helpTrigger == true)
		        {
		        	helpTrigger = false;
		        	tts.speak("I will tell you the current time.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("14") && helpTrigger == true)
		        {
		        	helpTrigger = false;
			        tts.speak("I can do math for you! Say an integer number, followed by an operator, followed by another integer number. I understand the following operators. Plus, Minus, Times, and Over, Over is my division command.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("15") && helpTrigger == true)
		        {
		        	helpTrigger = false;
			        tts.speak("I'll show you a YouTube video if you say develop and video in the same sentence.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		        else if(wordStr.equals("16") && helpTrigger == true)
		        {
		        	helpTrigger = false;
			        tts.speak("Do you really need this one explained? I will tell you what each command does based on its number.",TextToSpeech.QUEUE_FLUSH, null);
		        } 
		       
		        else if(wordStr.equals("what is today")){
		        	String currentDateString = DateFormat.getDateInstance().format(new Date());
		        	tts.speak(currentDateString, TextToSpeech.QUEUE_FLUSH, null);
		        }
		        else if(wordStr.equals("what are you")){
		        	String model = getDeviceName();
		        	txtText.setText(model);
		        	tts.speak("I am a " + model, TextToSpeech.QUEUE_FLUSH, null);
		        }
		        else if(wordStr.equals("what is your name")||wordStr.equals("who are you")){
		        	String model = getDeviceCodeName();
		        	String serial = getDeviceSerial();
		        	txtText.setText(serial + ", a.k.a., " +model);
		        	if(model.equals("MAKO"))
		        	{
		        		model = "mayko";
		        	}
		        	else
		        	{
		        		
		        	}
		        	tts.speak("My name is " + serial +", but my nickname among developers is " + model, TextToSpeech.QUEUE_FLUSH, null);
		        }
		        else if(wordStr.equals("what time is it")){
		        	String currentTimeString = DateFormat.getTimeInstance().format(new Date());
		        	String[] tokens = currentTimeString.split(":");
			        	String hour = tokens[0];
			        	String minutes = tokens[1];
			        	String seconds = tokens[2];
			        String[] tokens2 = seconds.split(" ");
			        	String seconds2 = tokens2[0];
			        	String amPm = tokens2[1];
		        	txtText.setText(hour +":"+ minutes + " " + amPm);
		        	tts.speak(hour +":"+ minutes +  " " + amPm, TextToSpeech.QUEUE_FLUSH, null);
		        	
		        }
		        else if(Character.isDigit(wordStr.charAt(0))){
		        	//Do math
		        	int firstNum = 0;
		        	int secondNum = 0;
		        	
		        	String[] tokens = wordStr.split(" ");
		        	if(tokens.length!=3){
		        		speakOut();
		        	}
		        	else{
			        	String firstNumStr = tokens[0];
			        	String operatorStr = tokens[1];
			        	String secondNumStr = tokens[2];
			        	firstNumStr = firstNumStr.replace(",", "");
			        	secondNumStr = secondNumStr.replace(",", "");
			        	firstNum = Integer.parseInt(firstNumStr);
			        	secondNum = Integer.parseInt(secondNumStr);
			        	
			        	if(operatorStr.equals("+")||operatorStr.equals("plus"))
			        	{
			        		int result = firstNum + secondNum;
			        		String resultStr = ""+result;
			        		txtText.setText(resultStr);
			        		tts.speak(resultStr, TextToSpeech.QUEUE_FLUSH, null);
			        	}
			        	if(operatorStr.equals("-")||operatorStr.equals("minus"))
			        	{
			        		int result = firstNum - secondNum;
			        		String resultStr = ""+result;
			        		txtText.setText(resultStr);
			        		tts.speak(resultStr, TextToSpeech.QUEUE_FLUSH, null);
			        	}
			        	if(operatorStr.equals("*")||operatorStr.equals("times"))
			        	{
			        		int result = firstNum * secondNum;
			        		String resultStr = ""+result;
			        		txtText.setText(resultStr);
			        		tts.speak(resultStr, TextToSpeech.QUEUE_FLUSH, null);
			        	}
			        	if(operatorStr.equals("over"))
			        	{
			        		int result = firstNum / secondNum;
			        		String resultStr = ""+result;
			        		txtText.setText(resultStr);
			        		tts.speak(resultStr, TextToSpeech.QUEUE_FLUSH, null);
			        	}
		        	}
		        
		        }
		        else if(wordStr.contains("develop")&&wordStr.contains("video"))
		        {
		        	Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:faGrUaVgeGk"));
			    	startActivity(youtubeIntent);
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
