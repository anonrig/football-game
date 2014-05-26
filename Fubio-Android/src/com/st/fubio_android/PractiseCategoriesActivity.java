package com.st.fubio_android;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.st.fubio_android.Adapters.CarouselAdapter;
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

			}

			
			@Override
			public void onSuccess(String response) {
				try { 
					jsonArray = new JSONArray(response);
				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Error: No response.", 5).show();
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
					for(int position = 0;!jsonArray.getJSONObject(position).isNull("name"); position++){
						nameList.add(jsonArray.getJSONObject(position).getString("name"));
						descriptionList.add(jsonArray.getJSONObject(position).getString("description"));
						imageList.add(jsonArray.getJSONObject(position).getString("image"));
					}
				} catch (JSONException e) {
					Toast.makeText(getApplicationContext(), "Error: Failed to initialize team lists for warm up.", 5).show();
					e.printStackTrace();
				}
			}
		});
	}

}
