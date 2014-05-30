package com.st.fubio_android.Adapters;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.st.fubio_android.R;
import com.st.fubio_android.Models.PracticeCategory;


public class CarouselAdapter extends PagerAdapter {

	private List<PracticeCategory> Categories;
	private LayoutInflater mInflater;

	public CarouselAdapter(Context context, List<PracticeCategory> Categories) {
		mInflater = LayoutInflater.from(context);
		this.Categories = Categories;
	}


	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// remove the current page as it no longer needed
		container.removeView((View) object);
	}


	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		// using the position parameter, inflate the proper layout, also add
		// it to the container parameter
		PracticeCategory currentCategory = Categories.get(position);
		ViewGroup pageView = (ViewGroup) mInflater.inflate(R.layout.layout_carouselitem, container, false);
		TextView txMain = (TextView) pageView.findViewById(R.id.textView1);
		TextView txSub = (TextView) pageView.findViewById(R.id.textView2);
		ImageView teamImage = (ImageView) pageView.findViewById(R.id.imageView1);

		txMain.setText(currentCategory.getItemTitle());
		txSub.setText(currentCategory.getItemDescription());

		teamImage.setImageBitmap(currentCategory.getBitmap());
		txMain.setText(Categories.get(position).getItemTitle());
		txSub.setText(Categories.get(position).getItemDescription());

		container.addView(pageView);
		return pageView;
	}


	@Override
	public int getCount() {
		return Categories.size();
	}


	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == obj;
	}
}