package com.st.fubio_android.Music;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;



public class MusicService extends Service {
	private static String TAG = "musicService";
	MediaPlayer mp = null;
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.e(TAG, "onBind");
		mp = MediaPlayer.create(getApplicationContext(), com.st.fubio_android.R.raw.bgsound);
		mp.setLooping(true);
		mp.start();
		return null;
	}

	public void play() {
		Log.e(TAG, "play");
		mp.start();
	}
	
	public void stop() {
		Log.e(TAG, "stop");
		mp.stop();
	}
}
