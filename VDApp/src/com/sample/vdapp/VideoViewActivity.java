package com.sample.vdapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {
	 
		// Declare variables
		ProgressDialog pDialog;
//		VideoView videoview;
	 
		// Insert your Video URL
		String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
	 
		int position= 0;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// Get the layout from video_main.xml
			setContentView(R.layout.videoview_main);
//			// Find your VideoView in your video_main.xml layout
//			videoview = (VideoView) findViewById(R.id.VideoView);
//			// Execute StreamVideo AsyncTask
	 
			final CustomVideoView cVideoView = (CustomVideoView) findViewById(R.id.custom_videoview);
			cVideoView.setPlayPauseListener(new CustomVideoView.PlayPauseListener() {

			    @Override
			    public void onPlay() {
			        System.out.println("Play!");
			    }

			    @Override
			    public void onPause() {
			        System.out.println("Pause!");
			        
			        position = cVideoView.getCurrentPosition();
			        Log.d("TAG",""+position);
			        Log.d("TAG_DUR",""+cVideoView.getDuration());
			        Log.d("TAG_buf",""+cVideoView.getBufferPercentage());
			        
			    }
			});

			
			
			// Create a progressbar
			pDialog = new ProgressDialog(VideoViewActivity.this);
			// Set progressbar title
			pDialog.setTitle("Android Video Streaming Tutorial");
			// Set progressbar message
			pDialog.setMessage("Buffering...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			// Show progressbar
			pDialog.show();
	 
			try {
				// Start the MediaController
				MediaController mediacontroller = new MediaController(
						VideoViewActivity.this);
				mediacontroller.setAnchorView(cVideoView);
				// Get the URL from String VideoURL
				Uri video = Uri.parse(VideoURL);
				cVideoView.setMediaController(mediacontroller);
				cVideoView.setVideoURI(video);
	 
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
	 
			cVideoView.requestFocus();
			cVideoView.setOnPreparedListener(new OnPreparedListener() {
				// Close the progress bar and play the video
				public void onPrepared(MediaPlayer mp) {
					pDialog.dismiss();
					cVideoView.start();
				}
			});
			
//			videoview.setOnTouchListener(new View.OnTouchListener()
//		        {
//		            @Override
//		            public boolean onTouch(View v, MotionEvent motionEvent)
//		            {
//		                if (videoview.isPlaying())
//		                {
//		                	videoview.pause();
////		                    if (!getActivity().getActionBar().isShowing())
////		                    {
////		                        getActivity().getActionBar().show();
////		                        mMediaController.show(0);
////		                    }
//		                    position = videoview.getCurrentPosition();
//		                    return false;
//		                }
//		                else
//		                {
////		                    if (getActivity().getActionBar().isShowing())
////		                    {
////		                        getActivity().getActionBar().hide();
////		                        mMediaController.hide();
////		                    }
//		                	videoview.seekTo(position);
//		                    videoview.start();
//		                    return false;
//		                }
//		            }
//		        });
	 
		}
	 
	}
