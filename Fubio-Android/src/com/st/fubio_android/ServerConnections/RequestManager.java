package com.st.fubio_android.ServerConnections;

import android.content.Context;

import com.loopj.android.http.*;



/**
 * Class used to deliver information to/from server.
 * Must use Android Async HTTP library.
 * 
 * @author ynizipli
 */
public class RequestManager {
	AsyncHttpClient client = new AsyncHttpClient();
	String API_PATH = "http://api.fub.io/";
	PersistentCookieStore myCookieStore;
	Context context;
	
	public RequestManager(Context cntx) {
		context = cntx;
		myCookieStore = new PersistentCookieStore(cntx);
		client.setCookieStore(myCookieStore);
	}
	
	public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.setCookieStore(myCookieStore);
		client.get(getAbsolutePath(url), params, responseHandler);
	}
	
	
	public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.setCookieStore(myCookieStore);
		client.post(getAbsolutePath(url), params, responseHandler);
	}
	
	
	public String getAbsolutePath(String url) {
		return API_PATH + url;
	}
}
