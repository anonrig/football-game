package com.st.fubio_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.st.fubio_android.Music.MusicService;



/**
 * Must import Jfeninstein's sliding menu and Facebook SDK 3.14.1.
 * 
 * Sliding menu: https://github.com/jfeinstein10/SlidingMenu
 * Facebook SDK: https://github.com/facebook/facebook-android-sdk
 * @author ynizipli
 */
public class MainActivity extends MainFragment {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTitle(R.string.app_title);
        setContentView(R.layout.layout_main);
        
		FB_Login mainFragment; 
		
        // Add the fragment on initial activity setup
        if (savedInstanceState == null) {
            mainFragment = new FB_Login();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainFragment).commit();
        } else {
            mainFragment = (FB_Login) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }
    }
}
