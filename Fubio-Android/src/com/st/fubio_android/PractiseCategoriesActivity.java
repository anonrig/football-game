package com.st.fubio_android;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.st.fubio_android.Adapters.CarouselAdapter;
import com.st.fubio_android.ServerConnections.RequestManager;

public class PractiseCategoriesActivity extends Activity {

	private JSONArray jsonArray;
	private List<String> nameList, descriptionList, imageList;
	private ViewPager mViewPager;

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
				//TO DO Log.
			}


			@Override
			public void onSuccess(String response) {
				//TO DO Log.
				try { 
					jsonArray = new JSONArray(response);
					int jsonSize = jsonArray.length();
					for(int position = 0;position < jsonSize; position++){
						nameList.add(jsonArray.getJSONObject(position).getString("name"));
						descriptionList.add(jsonArray.getJSONObject(position).getString("description"));
						imageList.add(jsonArray.getJSONObject(position).getString("image"));
					}
				} catch (JSONException e) {
					Toast.makeText(getApplicationContext(), "Error: Failed to initialize team lists.", 5).show();
					e.printStackTrace();
				}
			}


			@Override
			public void onFailure(Throwable error, String content) {
				//TO DO Log.
			}


			@Override
			public void onFinish() {
				//TO DO Log.
				try{
					mViewPager = (ViewPager) findViewById(R.id.pager);
					// set the adapter
					mViewPager.setAdapter(new CarouselAdapter(getApplicationContext(), nameList, imageList));
					mViewPager.setCurrentItem(0);
				} catch(Exception ex){
					Toast.makeText(getApplicationContext(), "Nope again.", Toast.LENGTH_SHORT).show();;
				}
			}
		});
	}
}
