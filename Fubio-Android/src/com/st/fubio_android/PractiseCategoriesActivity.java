package com.st.fubio_android;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.st.fubio_android.Adapters.CarouselAdapter;
import com.st.fubio_android.Models.PracticeCategoryObject;
import com.st.fubio_android.ServerConnections.RequestManager;

public class PractiseCategoriesActivity extends Activity {

	private static String TAG = "PracticeCarousel";
	private JSONArray jsonArray;
	private List<PracticeCategoryObject> Categories;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_practise_categories);

		RequestManager rm = new RequestManager(getApplicationContext());
		Categories = new ArrayList<PracticeCategoryObject>();

		rm.get("practiseCategories", null, new AsyncHttpResponseHandler() {

			@Override
			public void onStart() {
				Log.d(TAG, "onStart");
			}


			@Override
			public void onSuccess(String response) {
				Log.d(TAG, "onSuccess");
				try { 
					jsonArray = new JSONArray(response);
					int jsonSize = jsonArray.length();
					JSONObject currentObject = new JSONObject();
					for(int position = 0;position < jsonSize; position++){
						currentObject = jsonArray.getJSONObject(position);
						
						PracticeCategoryObject pCatObj = new PracticeCategoryObject(currentObject.getString("id"), 
								currentObject.getString("name"), currentObject.getString("description"),
								currentObject.getString("image"), currentObject.getString("token"), 
								currentObject.getString("sort"), currentObject.getBoolean("isPrivate"));
						Categories.add(pCatObj);
					}
				} catch (JSONException e) {
					Toast.makeText(getApplicationContext(), "Error: Failed to initialize team lists.", 5).show();
					e.printStackTrace();
				}
			}


			@Override
			public void onFailure(Throwable error, String content) {
				Log.e(TAG, "onFailure");
				Log.e(TAG, content);
			}


			@Override
			public void onFinish() {
				Log.d(TAG, "onFinish");
				
				try{
					ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
					mViewPager.setAdapter(new CarouselAdapter(getApplicationContext(), Categories));
					mViewPager.setCurrentItem(0);
				} catch(Exception ex){
					Toast.makeText(getApplicationContext(), "Please try again.", Toast.LENGTH_SHORT).show();;
				}
			}
		});
	}
}
