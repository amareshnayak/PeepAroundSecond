package com.peeparound.wizventure.peeparoundsecond;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    public HashMap<Integer,List<String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    
    public LazyAdapter(Activity a, HashMap<Integer,List<String>> d) {
     activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist);
        /*TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        */
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        List<String> arrayList=new ArrayList<>();
        System.out.println("positionposition"+position);
        arrayList=data.get(position);
        System.out.println("+++++++++++++++"+arrayList);
        String song = "";
        song = arrayList.get(0);
        title.setText(song);
        artist.setText(arrayList.get(1));
        imageLoader.DisplayImage(arrayList.get(2), thumb_image);
        // Setting all values in listview
        /*title.setText(song.get(ListActivity.KEY_TITLE));
        artist.setText(song.get(ListActivity.KEY_ARTIST));
        duration.setText(song.get(ListActivity.KEY_DURATION));
        imageLoader.DisplayImage(song.get(ListActivity.KEY_THUMB_URL), thumb_image);*/
        return vi;
    }
}