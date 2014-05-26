package com.st.fubio_android.Adapters;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.st.fubio_android.R;


public class CarouselAdapter extends PagerAdapter {

	private List<String> MainItems, subItems;//Will be replaced by object.
	private LayoutInflater mInflater;

	public CarouselAdapter(Context context, List<String> MainItems, List<String> imgUrls) {
		mInflater = LayoutInflater.from(context);
		this.MainItems = MainItems;
		this.subItems = imgUrls;
	}


	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// remove the current page as it no longer needed
		container.removeView((View) object);
	}


	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// using the position parameter, inflate the proper layout, also add
		// it to the container parameter

		ViewGroup pageView = (ViewGroup) mInflater.inflate(R.layout.layout_carouselitem, container, false);
		TextView txMain = (TextView) pageView.findViewById(R.id.textView1);
		TextView txSub = (TextView) pageView.findViewById(R.id.textView2);
		//ImageView imgView = (ImageView) pageView.findViewById(R.id.imageView1);
		txMain.setText(MainItems.get(position));
		txSub.setText(subItems.get(position));
		//TO DO get images from api.
		return pageView;
	}


	@Override
	public int getCount() {
		return MainItems.size();
	}


	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == obj;
	}
}