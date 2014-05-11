package com.st.fubio_android;

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
	    //authButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));
	    
	    return view;
	}
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) { //On state changes logs the info.
	    if (session.isOpened()) {
	    	Toast.makeText(getActivity(), "Logged in...", 5).show();
	    } 
	    else if (session.isClosed()) {
	    	Toast.makeText(getActivity(), "Logged out...", 5).show();
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
