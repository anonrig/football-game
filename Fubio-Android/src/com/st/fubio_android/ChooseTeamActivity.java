package com.st.fubio_android;

import com.st.fubio_android.Adapters.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.st.fubio_android.Models.Team;
import com.st.fubio_android.ServerConnections.ImageLoader;
import com.st.fubio_android.ServerConnections.RequestManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ChooseTeamActivity extends MainFragment {
	ArrayList <Team> teamList = new ArrayList<>();
	MainFragment mf = this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_chooseteam);
		
		RequestManager req = new RequestManager(this);
		req.get("teams", null, new AsyncHttpResponseHandler() {
        	ProgressDialog progress = new ProgressDialog(ChooseTeamActivity.this);
        	
        	@Override
			public void onStart() {
				progress.setTitle("Loading");
				progress.setMessage("Thank you for your patience.");
				progress.show();
			}

        		
			@Override
			public void onSuccess(String response) {
				try {
					JSONArray jArray = new JSONArray(response);
					System.out.println(jArray.toString());
					System.out.println(jArray.length());
					JSONObject currentObject;
					for(int i = 0; i < jArray.length(); i++) {
						currentObject = jArray.getJSONObject(i);
						Team tmp = new Team(currentObject.getString("id"), currentObject.getString("name"), currentObject.getString("imagename"), currentObject.getInt("sort"));
						teamList.add(tmp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
			@Override
			public void onFailure(Throwable error, String content) {
				Log.e("TeamSelect" , "onFailure error : " + error.toString() + "content : " + content);
				Toast.makeText(getApplicationContext(), content, 5).show();
			}


			@Override
			public void onFinish() {
				progress.dismiss();

				ListView lv = (ListView) findViewById(R.id.chooseteam);
				lv.setAdapter(new ChooseTeamAdapter(mf, teamList));

				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Team chosenTeam = teamList.get(position);

						RequestManager rm = new RequestManager(getApplicationContext());
						RequestParams req = new RequestParams();

						req.put("teamId", chosenTeam.getId());

						rm.post("player/team", req, new AsyncHttpResponseHandler() {

							@Override
							public void onStart() {
								Log.v("TeamSelect", "onStart");
							}


							@Override
							public void onSuccess(String response) {
								Log.v("TeamSelect", "onSuccess");
								System.out.println(response);
							}


							@Override
							public void onFailure(Throwable error, String content) {
								Log.e("TeamSelect" , "onFailure error : " + error.toString() + "content : " + content);

								if (content == "Unauthorized") 
									content = "Please login first.";

								Toast.makeText(getApplicationContext(), content, 5).show();
							}


							@Override
							public void onFinish() {
								Log.v("TeamSelect" , "onFinish");
								startActivity(new Intent(getApplicationContext(), SettingsActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
								finish();
							}
						});
					}

				});
			}
		});
	}

}
