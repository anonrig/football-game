package com.st.fubio_android;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.st.fubio_android.ServerConnections.RequestManager;

public class PractiseCategoriesActivity extends MainFragment {

	private JSONArray jsonArray;
	private List<String> nameList, descriptionList, imageList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_practise_categories);
		RequestManager rm = new RequestManager(getApplicationContext());
		nameList = new ArrayList<String>();
		descriptionList = new ArrayList<String>();
		imageList = new ArrayList<String>();
		
		rm.get("practiseCategories", null, new AsyncHttpResponseHandler() {
            	
            	@Override
				public void onStart() {
            		System.out.println("Practise Categories onStart");
            		
				}

            	
				@Override
				public void onSuccess(String response) {
					System.out.println(response);
					try {
						JSONArray dummyArray = new JSONArray(response);
						jsonArray = dummyArray;
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}

				
				@Override
				public void onFailure(Throwable error, String content) {
					System.out.println(content);
				}

				
				@Override
				public void onFinish() {
					System.out.println("Practise Categories onFinish");
					try {
						int position = 0;
							while(!jsonArray.getJSONObject(position).isNull("name")){
							nameList.add(jsonArray.getJSONObject(position).getString("name"));
							descriptionList.add(jsonArray.getJSONObject(position).getString("description"));
							imageList.add(jsonArray.getJSONObject(position).getString("image"));
							System.out.println(descriptionList.get(position));
							position++;
							}
						
					} catch (JSONException e) {
						
						e.printStackTrace();
					}
				}
            });
		
		
		
	}

}
