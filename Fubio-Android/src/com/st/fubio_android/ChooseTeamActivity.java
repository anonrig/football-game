package com.st.fubio_android;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.st.fubio_android.Models.Team;
import com.st.fubio_android.ServerConnections.RequestManager;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class ChooseTeamActivity extends MainFragment {
	ArrayList <Team> teamList = new ArrayList<>();
	MainFragment mf = this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_chooseteam);
		
		RequestManager req = new RequestManager(this);
		req.get("teams", null, new AsyncHttpResponseHandler() {
//        	ProgressDialog progress = new ProgressDialog(getApplicationContext());
        	
        	@Override
			public void onStart() {
//				progress.setTitle("Loading");
//				progress.setMessage("Thank you for your patience.");
//				progress.show();
        		System.out.println("basladi");
			}

        		
			@Override
			public void onSuccess(String response) {
				try {
					JSONArray jArray = new JSONArray(response);
					System.out.println(jArray.toString());
					System.out.println(jArray.length());
					
					for(int i = 0; i < jArray.length(); i++) {
						JSONObject currentObject = jArray.getJSONObject(i);
						if (currentObject != null) {
							Team tmp = new Team(currentObject.getString("id"), currentObject.getString("name"), currentObject.getString("imagename"), currentObject.getInt("sort"));
							teamList.add(tmp);
							System.out.println(tmp.getName());
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
			@Override
			public void onFailure(Throwable error, String content) {
				System.out.println(content);
				Toast.makeText(getApplicationContext(), content, 5).show();
			}

			
			@Override
			public void onFinish() {
				System.out.println("bitti");
//				progress.dismiss();
				ListView lv = (ListView) findViewById(R.id.chooseteam);
				
				ArrayList<String> deneme = new ArrayList<String>();
				
				for (Team tmp : teamList) {
					deneme.add(tmp.getName());
				}

				String[] titles = deneme.toArray(new String[deneme.size()]);
				
		        lv.setAdapter(new ArrayAdapter<String>(mf, android.R.layout.simple_list_item_1, titles));
			}
        });
	}

}
