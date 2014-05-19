package com.st.fubio_android.Music;

import android.content.Context;
import android.media.MediaPlayer;



public class MusicService {
	MediaPlayer mp = null;
	
	public MusicService(Context context) {
		mp = MediaPlayer.create(context, com.st.fubio_android.R.raw.bgsound);
	}

	public void play() {
		mp.setLooping(true);
		mp.start();
	}
	
	public void stop() {
		mp.stop();
	}
}
