package com.st.fubio_android.ServerConnections;

import java.io.InputStream;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class ImageFetcher {
	HashMap<String, Bitmap> cache = new HashMap<String, Bitmap>();
	private static ImageFetcher instance = null;

	protected ImageFetcher() {
		// Exists only to defeat instantiation.
	}

	public static ImageFetcher getInstance() {
		if(instance == null) {
			instance = new ImageFetcher();
		}
		return instance;
	}

	public Bitmap getImage(String url) {
		if (cache.get(url) == null) {
			DownloadImageTask task = new DownloadImageTask();
			task.execute(url);
			return cache.get(url);
		} else {
			return cache.get(url);
		}
	}

	class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		String urldisplay;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		protected Bitmap doInBackground(String... urls) {
			urldisplay = urls[0];
			Bitmap mIcon11 = null;

			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("imageFetcher", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		@Override 
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			cache.put(urldisplay, result);
		}
	}
}
