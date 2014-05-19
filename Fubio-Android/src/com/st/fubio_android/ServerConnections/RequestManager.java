package com.st.fubio_android.ServerConnections;

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
	
	public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsolutePath(url), params, responseHandler);
	}
	
	
	public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsolutePath(url), params, responseHandler);
	}
	
	
	public String getAbsolutePath(String url) {
		return API_PATH + url;
	}
}
