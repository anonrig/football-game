package com.st.fubio_android.Adapters;

import java.io.InputStream;
import java.util.ArrayList;

import com.st.fubio_android.R;
import com.st.fubio_android.Models.Team;
import com.st.fubio_android.ServerConnections.ImageFetcher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseTeamAdapter extends ArrayAdapter<Team> {
	private static String API_PATH = "http://api.fub.io";
	private final Context context;
    private final ArrayList<Team> itemsArrayList;

    public ChooseTeamAdapter(Context context, ArrayList<Team> itemsArrayList) {
        super(context, R.layout.layout_chooseteam_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder = null;

    	if (convertView == null) {
    		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		
    		convertView = inflater.inflate(R.layout.layout_chooseteam_item, parent, false);
    		
    		holder = new ViewHolder(convertView);
    		
    		convertView.setTag(holder);
    	} else {
    		holder = (ViewHolder)convertView.getTag();
    	}
    	
    	Team currentTeam = itemsArrayList.get(position);
    	
    	holder.setTitle((TextView) convertView.findViewById(R.id.choose_team_text));
    	holder.setImage((ImageView) convertView.findViewById(R.id.choose_team_image));

        holder.getTitle().setText(currentTeam.getName());
        holder.getTitle().setTextColor(Color.WHITE);
        holder.getImage().setImageBitmap(currentTeam.getImage());

        return convertView;
    }
    
    class ViewHolder {
    	private TextView title;
    	private ImageView image;
    	
    	private View base;
    	
    	public ViewHolder(View base) {
    		this.base = base;
    	}

		public TextView getTitle() {
			return title;
		}

		public void setTitle(TextView title) {
			this.title = title;
		}

		public ImageView getImage() {
			return image;
		}

		public void setImage(ImageView image) {
			this.image = image;
		}
    }
}