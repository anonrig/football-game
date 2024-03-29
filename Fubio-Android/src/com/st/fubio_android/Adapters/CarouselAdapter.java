package com.st.fubio_android.Adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.st.fubio_android.GameLoadingActivity;
import com.st.fubio_android.R;
import com.st.fubio_android.R.color;
import com.st.fubio_android.ServerConnections.ImageLoader;
import com.st.fubio_android.Models.PracticeCategory;


public class CarouselAdapter extends PagerAdapter {

	private List<PracticeCategory> Categories;
	private LayoutInflater mInflater;
	private Context context;
	private String imageUrl = "http://api.fub.io/img/practise/";
	ImageLoader imageLoader; 


	public CarouselAdapter(Context context, List<PracticeCategory> Categories) {
		mInflater = LayoutInflater.from(context);
		this.Categories = Categories;
		this.context = context;
		imageLoader = new ImageLoader(context);
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
		final PracticeCategory currentCategory = Categories.get(position);
		ViewGroup pageView = (ViewGroup) mInflater.inflate(R.layout.layout_carouselitem, container, false);
		TextView txMain = (TextView) pageView.findViewById(R.id.textView1);
		TextView txSub = (TextView) pageView.findViewById(R.id.textView2);
		ImageView teamImage = (ImageView) pageView.findViewById(R.id.imageView1);
		Button btn = (Button) pageView.findViewById(R.id.button1);

		OnClickListener onCarouselItemClick = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, GameLoadingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("PracticeCategory", currentCategory);
				context.startActivity(intent);
			}
		};


		if(currentCategory.getPrice() != "null" && !currentCategory.getPrice().equals("0.00")){
			btn.setText("Satın Al " + currentCategory.getPrice());
			btn.setBackgroundResource(R.drawable.roundedpricebutton);
		}
		else
			btn.setOnClickListener(onCarouselItemClick);
		
		

		txMain.setText(currentCategory.getTitle());
		txSub.setText(currentCategory.getDescription());
		imageLoader.DisplayImage(imageUrl+currentCategory.getImageUrl(), teamImage);

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