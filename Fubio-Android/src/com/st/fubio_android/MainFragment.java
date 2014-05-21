package com.st.fubio_android;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.st.fubio_android.Music.MusicService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainFragment extends FragmentActivity {
	ListView lv;
	final String[] menuItems ={"Hoşgeldin", "Ödüllü Turnuva", "Antrenman", "Arkadaşlarım", "Fubio Shop", "Ayarlar", "Uygulama Hakkında"};
	final String[] subMenuItems ={"Futbol bilgini herkese göster.","","","","","",""};
	int [] prgmImages={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
	
	SlidingMenu menu;
	
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
	}
	
	 public void onMenuItemClick(View v){
    	TextView tView = (TextView) v.findViewById(R.id.textView1);
    	
    	switch(tView.getText().toString()) {
    		case "Hoşgeldin":
    			startActivity(new Intent(this, MainActivity.class));
    			menu.toggle();
    			break;
    		case "Uygulama Hakkında":
    			//setContentView(R.layout.layout_about);
    			startActivity(new Intent(this, AboutActivity.class));
        		menu.toggle();
        		break;
    		case "Ayarlar":
    			startActivity(new Intent(this, SettingsActivity.class));
    			menu.toggle();
    			break;
        	default:
        		System.out.println(tView.getText().toString());
        		break;
    	}
    }
}
