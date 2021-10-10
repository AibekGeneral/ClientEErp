package com.example.clientup.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.cardview.widget.CardView;

import com.example.clientup.HistoryDetailActivity;
import com.example.clientup.MenuDetailActivity;
import com.example.clientup.R;
import com.example.clientup.Util.FeedItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListHistoryAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<FeedItem> feed_item;
    FeedItem item;
    ListView listView1;

    ArrayList<FeedItem> feedItem;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public ListHistoryAdapter(ArrayList<FeedItem> feedItem, Activity activity) {
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
        @BindView(R.id.cardView_menu)
        CardView cardView;

        @BindView(R.id.my_image)
        ImageView imageView;

        @BindView(R.id.my_podrob)
        Button btn_pod;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listhistoryitem, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        item = feed_item.get(position);

        //viewHolder.textTwoitem.setText(String.valueOf(item.getId()));
        imageLoader.displayImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMk5qkfe7-JJHMoDK5B0YA5y2yXN70owLhI43uv5_ept13Ws7bkw",viewHolder.imageView);



        viewHolder.btn_pod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.startActivity(new Intent(activity, HistoryDetailActivity.class).putExtra("id",item.getId()));


            }
        });

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //activity.startActivity(new Intent(activity, RabotaActivity.class).putExtra("id",item.getId()));
//
//
//            }
//        });




        return convertView;
    }

}
