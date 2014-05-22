package com.st.fubio_android;


import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.st.fubio_android.ServerConnections.RequestManager;
import com.st.fubio_android.ServerConnections.TinyDB;



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
	    authButton.setReadPermissions(Arrays.asList("user_email", "user_friends"));
	    
	    return view;
	}
	
	
	private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
	    if (session.isOpened()) {
	    	RequestParams params = new RequestParams();
	    	RequestManager manager = new RequestManager(getActivity());
	    	String accessToken = session.getAccessToken();
	    	
            params.put("access_token", accessToken);
            
            manager.get("loginWithFBAC", params, new AsyncHttpResponseHandler() {
            	ProgressDialog progress = new ProgressDialog(getActivity());
            	
            	@Override
				public void onStart() {
					Log.v(TAG , "onStart");
					progress.setTitle("Loading");
					progress.setMessage("Thank you for your patience.");
					progress.show();
				}

            	
				@Override
				public void onSuccess(String response) {
					Log.v(TAG , "onSuccess");
					System.out.println(response);
			
					try {
						JSONObject jObject = new JSONObject(response);
						
						TinyDB tinydb = new TinyDB(getActivity().getApplicationContext());
						tinydb.putString("name", jObject.getString("name"));
						tinydb.putString("teamImageName", jObject.getString("teamImageName"));
						tinydb.putInt("id", jObject.getInt("id"));
						tinydb.putInt("facebookId", jObject.getInt("facebookId"));
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				
				@Override
				public void onFailure(Throwable error, String content) {
					Log.e(TAG , "onFailure error : " + error.toString() + "content : " + content);
					Toast.makeText(getActivity(), content, 5).show();
				}

				
				@Override
				public void onFinish() {
					progress.dismiss();
					Log.v(TAG , "onFinish");
				}
            });
	        
	    	Toast.makeText(getActivity(), "Logged in.", 5).show();
	    	return;
	    } 
	    
	    Toast.makeText(getActivity(), "Logged out.", 5).show();
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
