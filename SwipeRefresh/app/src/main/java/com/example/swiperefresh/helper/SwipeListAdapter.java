package com.example.swiperefresh.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.swiperefresh.R;

import java.util.List;

public class SwipeListAdapter extends BaseAdapter
{
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieList;
    private String[] bgColors;
    private View convertView;

    public SwipeListAdapter(Activity activity, List<Movie> movieList)
    {
        this.activity = activity;
        this.movieList = movieList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int location)
    {
        return movieList.get(location);
    }

    @Override
    public long getItemId(int location)
    {
        return location;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.list_row, null);

        TextView serial = (TextView) view.findViewById(R.id.serial);
        TextView title = (TextView) view.findViewById(R.id.title);

        serial.setText(String.valueOf(movieList.get(i).id));
        title.setText(movieList.get(i).title);
        
        String color = bgColors[i % bgColors.length];
        serial.setBackgroundColor(Color.parseColor(color));
        
        return convertView;
    }
}
