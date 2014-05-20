package com.st.fubio_android;

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
public class MainActivity extends FragmentActivity {
	//Private variables and arrays for sliding menu.
	private ListView lv;
	private final String[] menuItems ={"Üye Ol", "Ödüllü Turnuva", "Antrenman", "Arkadaşlarım", "Fubio Shop", "Ayarlar", "Uygulama Hakkında"};
	private final String[] subMenuItems ={"Futbol bilgini herkese göster.","","","","","",""};
	public static int [] prgmImages={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
	
	private FB_Login mainFragment; 
	private SlidingMenu menu;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                MusicService mp = new MusicService(getApplicationContext());
                while(true) {
                	mp.play();
                }
				
            }
        };

        thread.start();
        
        setTitle(R.string.app_title);
        setContentView(R.layout.layout_main);

        // Sets and configures the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setShadowDrawable(R.color.Black);
        menu.bringToFront();
        menu.setMenu(R.layout.layout_menu);
        
    	//Sets listView for sliding menu
        lv=(ListView) findViewById(android.R.id.list);
        lv.setAdapter(new CustomAdapter(this, menuItems, subMenuItems, prgmImages));
        
        // Add the fragment on initial activity setup
        if (savedInstanceState == null) {
            mainFragment = new FB_Login();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainFragment).commit();
        } else {
            mainFragment = (FB_Login) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }
    }
    
    
    public void onMenuItemClick(View v){
    	TextView tView = (TextView) v.findViewById(R.id.textView1);
    	
    	switch(tView.getText().toString()) {
    		case "Uygulama Hakkında":
    			setContentView(R.layout.layout_about);
        		menu.toggle();
        		break;
        	default:
        		System.out.println(tView.getText().toString());
        		break;
    	}
    }
}
