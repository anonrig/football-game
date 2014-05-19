package com.st.fubio_android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class CustomAdapter extends BaseAdapter {
    static String [] menuItem;
    static String [] subMenuItem;
    Context context;
	int [] imageId;
 	private static LayoutInflater inflater=null;
 	
 	
    public CustomAdapter(MainActivity mainActivity, String[] itemList, String[] subItem, int[] itemIcon) {
        menuItem=itemList;
        subMenuItem = subItem;
        context=mainActivity;
        imageId=itemIcon;
        inflater = ( LayoutInflater )context.
                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    
    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = 50;
        int targetHeight = 50;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, 
                            targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
            ((float) targetHeight - 1) / 2,
            (Math.min(((float) targetWidth), 
            ((float) targetHeight)) / 2),
            Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap, 
            new Rect(0, 0, sourceBitmap.getWidth(),
            sourceBitmap.getHeight()), 
            new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }
    
    
    @Override
    public int getCount() {
        return menuItem.length;
    }
 
    
    @Override
    public Object getItem(int position) {
        return position;
    }
 
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    
    public class Holder {
        TextView mainItem, subItem;
        ImageView img;
    }
    
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;       
        rowView = inflater.inflate(R.layout.menu_item, null);
        holder.mainItem=(TextView) rowView.findViewById(R.id.textView1);
        holder.subItem=(TextView) rowView.findViewById(R.id.textView2);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);     
             
        holder.mainItem.setText(menuItem[position]);
        holder.subItem.setText(subMenuItem[position]);
        holder.img.setImageResource(imageId[position]);     
  
        return rowView;
    }
}
