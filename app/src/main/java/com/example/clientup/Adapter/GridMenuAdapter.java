package com.example.clientup.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.clientup.MenuActivity;
import com.example.clientup.R;
import com.example.clientup.Util.FeedItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridMenuAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<FeedItem> feed_item;
    FeedItem item;
    ListView listView1;

    ArrayList<FeedItem> feedItem;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public GridMenuAdapter(ArrayList<FeedItem> feedItem, Activity activity) {
        this.activity = activity;
        this.feed_item = feedItem;
    }

    @Override
    public int getCount() {
        return feed_item.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        @BindView(R.id.cardTwo)
        CardView cardTwo;
        @BindView(R.id.linearTwo)
        LinearLayout linearTwo;

        @BindView(R.id.imageview)
        ImageView imageView;
        @BindView(R.id.txt_name)
        TextView txt_name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gridmenuitem, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        item = feed_item.get(position);

        viewHolder.txt_name.setText(String.valueOf(item.getName()));
        imageLoader.displayImage(item.getImage_url(),viewHolder.imageView);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = feed_item.get(position);
                activity.startActivity(new Intent(activity, MenuActivity.class).putExtra("id",item.getId()));


            }
        });




        return convertView;
    }


}
