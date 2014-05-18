package com.st.fubio_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.R.integer;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;



public class FB_Login extends Fragment {
	private static final String TAG = "FBLogin";
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {	
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//Inflates fragment view + sets permissions !DOESNT'T REQUEST!
	    View view = inflater.inflate(R.layout.layout_main, container, false);

	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    authButton.setReadPermissions(Arrays.asList("user_likes", "user_status", "user_email"));
	    
	    return view;
	}
	
	
	private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
	    if (session.isOpened()) {
	        Request getFacebookInformation = Request.newMeRequest(session, new GraphUserCallback() {
				@Override
				public void onCompleted(GraphUser user, Response response) {
					if (user != null) {
	                    String userId = user.getId(),
	                    		accessToken = session.getAccessToken(),
	                    		name = user.getName(),
	                    		email = (String) user.asMap().get("email");
	                    
	                    Toast.makeText(getActivity(), "Successfully gathered Facebook information.", 5).show();

	                    HashMap<String, String> data = new HashMap<String, String>();
	                    data.put("facebookId", userId);
	                    data.put("accessToken", accessToken);
	                    data.put("email", email);
	                    data.put("name", name);
	                    
	                    ServerRequest request = new ServerRequest("http://api.fub.io/register", data);
	                    request.execute();
	                }
				}
	        });
	        getFacebookInformation.executeAsync();
	        
	    	Toast.makeText(getActivity(), "Logged in.", 5).show();
	    	return;
	    } 
	    
	    Toast.makeText(getActivity(), "Logged out.", 5).show();
	}
	
	private class ServerRequest extends AsyncTask<String, String, String> {
		private String requestUrl = null;
		private HashMap<String, String> requestParams = null;
		
		
		public ServerRequest(String URL, HashMap<String, String> data) {
			requestParams = data;
			requestUrl = URL;
		}
		
		
		@Override
		protected String doInBackground(String... params) {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(requestUrl);
			HttpResponse response = null;
			ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			Iterator<String> it = requestParams.keySet().iterator();
			
			while (it.hasNext()) {
				String key = it.next();
				nameValuePair.add(new BasicNameValuePair(key, requestParams.get(key)));
			}
			
			try {
				post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
				response = client.execute(post);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return String.valueOf(response.getStatusLine().getStatusCode());
		}
		
		
		@Override
		protected void onPostExecute(String responseCode) {
			if (Integer.parseInt(responseCode) == 500) {
				Toast.makeText(getActivity(), "Failed to connect to Fubio server.", 5).show();
			} 
		}	
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) { //UI lifecycle helper manages facebook session on pause,resume,destroy.
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	
	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	
	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
}
