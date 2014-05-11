package com.st.fubio_android;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.Session;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

////////////////////////////////////////////////////////////////////
//Must include jfeinstein's sliding menu lib and Facebook SDK 3.14//
////////////////////////////////////////////////////////////////////
public class MainActivity extends FragmentActivity {

	//Private variables and arrays for sliding menu.
	private ListView lv;
	private final String[] menuItems ={"Üye Ol", "Ödüllü Turnuva", "Antrenman", "Arkadaşlarım", "Fubio Shop", "Ayarlar", "Uygulama Hakkında"};
	private final String[] subMenuItems ={"Futbol bilgini herkese göster.","","","","","",""};
	public static int [] prgmImages={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
	
	private FB_Login mainFragment; 
	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setTitle(R.string.app_title);
	        setContentView(R.layout.layout_main);

	        
	        // Sets and configures the SlidingMenu
	        SlidingMenu menu = new SlidingMenu(this);
	        menu.setMode(SlidingMenu.RIGHT);
	        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	        menu.setShadowWidthRes(R.dimen.shadow_width);
	        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
	        menu.setFadeDegree(0.35f);
	        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
	        menu.setShadowDrawable(R.color.Black);
	        menu.bringToFront();
	        menu.setMenu(R.layout.layout_menu);
	        
	        if(Session.getActiveSession() != null){
	        	//TODO -> Sliding menu will be changed after login.
	        }
	        else if(Session.getActiveSession() == null){
	        	
	        	//Sets listView for sliding menu
		        lv=(ListView) findViewById(android.R.id.list);
		        lv.setAdapter(new CustomAdapter(this, menuItems, subMenuItems, prgmImages));
	        	
	        }
	        
	        if (savedInstanceState == null) {
	            // Add the fragment on initial activity setup
	            mainFragment = new FB_Login();
	            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainFragment).commit();
	        } else {
	            // Or set the fragment from restored state info
	            mainFragment = (FB_Login) getSupportFragmentManager().findFragmentById(android.R.id.content);
	        }

	    }


}
