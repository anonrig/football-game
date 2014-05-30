package com.st.fubio_android;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.st.fubio_android.Adapters.CarouselAdapter;
import com.st.fubio_android.Models.PracticeCategory;
import com.st.fubio_android.ServerConnections.ImageFetcher;
import com.st.fubio_android.ServerConnections.RequestManager;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class PractiseCategoriesActivity extends Activity {

	private static String TAG = "PracticeCarousel";
	private JSONArray jsonArray;
	private List<PracticeCategory> Categories;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_practise_categories);
		
		RequestManager rm = new RequestManager(getApplicationContext());
		Categories = new ArrayList<PracticeCategory>();

		rm.get("practiseCategories", null, new AsyncHttpResponseHandler() {
			
			ProgressDialog progress = new ProgressDialog(PractiseCategoriesActivity.this);
			@Override
			public void onStart() {
				Log.d(TAG, "onStart");
				progress.setTitle("Loading");
				progress.setMessage("Thank you for your patience.");
				progress.show();
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

						PracticeCategory pCatObj = new PracticeCategory(currentObject.getString("id"), 
								currentObject.getString("name"), currentObject.getString("description"),
								currentObject.getString("image"), currentObject.getString("token"), 
								currentObject.getString("sort"), currentObject.getBoolean("isPrivate"));
						pCatObj.setBitmap(ImageFetcher.getInstance().getImage("http://api.fub.io/img/practise/" + pCatObj.getItemImageUrl()));//Uploads all images, but adapter initializes faster so images 
						Categories.add(pCatObj); 									//can't be seen at first button hit.
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
				Toast.makeText(PractiseCategoriesActivity.this, "Failed to get information from server.", 5).show();
			}


			@Override
			public void onFinish() {
				Log.d(TAG, "onFinish");		
				try{
					progress.dismiss();
					ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
					mViewPager.setAdapter(new CarouselAdapter(getApplicationContext(), Categories));
					mViewPager.setCurrentItem(0);
					
					PageIndicator mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
					mIndicator.setViewPager(mViewPager);
				} catch(Exception ex){
					ex.printStackTrace();
					Toast.makeText(getApplicationContext(), "Please try again.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		finish();
		return super.onMenuItemSelected(featureId, item);
	}
}
