package com.example.clientup.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.clientup.MenuDetailActivity;
import com.example.clientup.R;
import com.example.clientup.Util.FeedItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListZakazAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<FeedItem> feed_item;
    FeedItem item;
    ListView listView1;

    ArrayList<FeedItem> feedItem;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public ListZakazAdapter(ArrayList<FeedItem> feedItem, Activity activity) {
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

        @BindView(R.id.my_image)
        ImageView imageView;

        @BindView(R.id.my_name)
        TextView my_name;
        @BindView(R.id.my_price)
        TextView my_price;
        @BindView(R.id.btn_sani)
        Button btn_san;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listzakazitem, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        item = feed_item.get(position);

        viewHolder.my_name.setText(item.getName());
        viewHolder.btn_san.setText(String.valueOf(item.getItem_id()));
        imageLoader.displayImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMk5qkfe7-JJHMoDK5B0YA5y2yXN70owLhI43uv5_ept13Ws7bkw",viewHolder.imageView);







        return convertView;
    }
}
