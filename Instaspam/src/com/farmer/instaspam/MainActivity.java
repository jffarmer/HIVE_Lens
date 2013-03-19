package com.farmer.instaspam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
//import android.widget.RelativeLayout;
import android.widget.Toast;
import android.content.pm.PackageManager;

public class MainActivity extends Activity {

	private static int TAKE_PICTURE = 1;
	private Uri outputFileUri;
	Context contextActivity;
	PackageManager pm;
	String bg_path = "";
	Boolean wasRotated = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contextActivity = this;
		pm = contextActivity.getPackageManager();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// CAMERA ACTION BUTTON
	public void doTakePicture(View v) {
		// Check system has rear facing camera
		if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			try {
				File directory = new File(Environment.getExternalStorageDirectory()+"/InstaSpam/");
				if (!directory.exists()) {
					directory.mkdir();
					//Toast.makeText(this,"InstaSpam Folder Created", Toast.LENGTH_SHORT).show();
				} else {
					//Toast.makeText(this,"InstaSpam Folder Already Exists",Toast.LENGTH_SHORT).show();
				}
				long currentTime = System.currentTimeMillis();
				bg_path = Environment.getExternalStorageDirectory()+"/InstaSpam/"+"img"+currentTime+".jpg";
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File file = new File(directory,"img"+currentTime+".jpg");
				outputFileUri = Uri.fromFile(file);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
				startActivityForResult(intent, TAKE_PICTURE);
			} catch (Exception e) {
				Toast.makeText(this, "Unable to create InstaSpam directory",Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "Rear camera couldn't be started",Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(bg_path, options);
			int width = options.outWidth;
			int height = options.outHeight;
			DisplayMetrics metrics = this.getResources().getDisplayMetrics();
			int screenheight = metrics.heightPixels;
			int screenwidth = metrics.widthPixels;
			// Locked in portrait mode, therefore, rotate landscape
			// images to fill screen without distortion.
			if (width > height) {
				Bitmap bmp = BitmapFactory.decodeFile(bg_path);
				Matrix matrix = new Matrix();
				matrix.postRotate(90);
				bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),bmp.getHeight(),matrix, true);
				FileOutputStream fOut;
				try {
					fOut = new FileOutputStream(bg_path);
					bmp.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
					fOut.flush();
					fOut.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					Toast.makeText(this, "Whoops, couldn't find the picture...", Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					e.printStackTrace();
					Toast.makeText(this, "Whoops, couldn't handle the file...", Toast.LENGTH_SHORT).show();
				}
				wasRotated = true;
			}
			/**Was setting background using setBackground of Relative Layout.
			 * setBackground, however, was not introduced until API level 16.
			 * RelativeLayout rl = (RelativeLayout) findViewById(R.id.RelativeLayout1);
			Drawable d = new BitmapDrawable(getResources(),resized);
			rl.setBackground(d);**/
			
			Bitmap resized = resizeBitMapImage(bg_path, screenwidth,screenheight);
			
			Matrix matrix = new Matrix();
			resized = Bitmap.createBitmap(resized, 0, 0, resized.getWidth(),resized.getHeight(),matrix, true);
			FileOutputStream fOut;
			try {
				fOut = new FileOutputStream(bg_path);
				resized.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
				fOut.flush();
				fOut.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				Toast.makeText(this, "Whoops, couldn't find the picture...", Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(this, "Whoops, couldn't handle the file...", Toast.LENGTH_SHORT).show();
			}
			
			ImageView imgV = (ImageView) findViewById(R.id.imageView1);
			imgV.setImageBitmap(BitmapFactory.decodeFile(bg_path));
			
			//Toast.makeText(this, outputFileUri.toString(),Toast.LENGTH_SHORT).show();	
		} else if (requestCode == TAKE_PICTURE && resultCode == RESULT_CANCELED) {
			Toast.makeText(this, "Canceled, no picture taken.",Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Something went wrong",Toast.LENGTH_SHORT).show();
		}
	}

	// MESSAGING BUTTON ACTION
	public void doSendPicture(View v) {
		if (bg_path.equals("")) {
			Toast.makeText(this, "Take a picture first!",Toast.LENGTH_SHORT).show();
		} else {
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setType("image/jpg"+"text/plain");
			// Rotate landscape images back to their original orientation
			if (wasRotated == true) {
				Bitmap bmp = BitmapFactory.decodeFile(bg_path);
				Matrix matrix = new Matrix();
				matrix.postRotate(-90);
				bmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
				FileOutputStream fOut;
				try {
					fOut = new FileOutputStream(bg_path);
					bmp.compress(Bitmap.CompressFormat.JPEG,85,fOut);
					fOut.flush();
					fOut.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					Toast.makeText(this, "Whoops, couldn't find the picture...", Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					e.printStackTrace();
					Toast.makeText(this, "Whoops, couldn't handle the file...", Toast.LENGTH_SHORT).show();
				}
			}
			bg_path = "file://" + bg_path;
			i.putExtra(Intent.EXTRA_STREAM, Uri.parse(bg_path));
			i.putExtra(Intent.EXTRA_TEXT, "I viewed an augmented world, through HIVE LENS.");
			i.putExtra("sms_body", "I viewed an augmented world, through HIVE LENS.");
			i.putExtra("subject", "The view through HIVE LENS.");
			startActivity(i);
		}
	}
	
	// RESIZE IMAGE TO SCREEN DIMENSIONS TO REDUCE RAM USAGE
	public static Bitmap resizeBitMapImage(String filePath,int targetWidth,int targetHeight) {
		Bitmap bitMapImage = null;
		// First, get the dimensions of the image
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath,options);
		double sampleSize = 0;
		// Only scale if we need to
		// (16384 buffer for img processing)
		Boolean scaleByHeight = Math.abs(options.outHeight-targetHeight)>=Math.abs(options.outWidth-targetWidth);
		if (options.outHeight*options.outWidth*2>=1638) {
			// Load, scaling to smallest power of 2 that'll get it <= desired
			// dimensions
			sampleSize = scaleByHeight ? options.outHeight / targetHeight : options.outWidth / targetWidth;
			sampleSize = (int) Math.pow(2d,Math.floor(Math.log(sampleSize)/Math.log(2d)));
		}
		// Do the actual decoding
		options.inJustDecodeBounds = false;
		options.inTempStorage = new byte[128];
		while (true) {
			try {
				options.inSampleSize = (int) sampleSize;
				bitMapImage = BitmapFactory.decodeFile(filePath, options);
				break;
			} catch (Exception ex) {
				try {
					sampleSize = sampleSize * 2;
				} catch (Exception ex1) {
				}
			}
		}
		return bitMapImage;
	}
}
