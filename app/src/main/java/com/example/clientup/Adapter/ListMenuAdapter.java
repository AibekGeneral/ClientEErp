package com.example.clientup.Adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.cardview.widget.CardView;

import com.example.clientup.MenuDetailActivity;
import com.example.clientup.R;
import com.example.clientup.Util.FeedItem;
import com.example.clientup.data.DBHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMenuAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<FeedItem> feed_item;
    FeedItem item;
    ListView listView1;

    ArrayList<FeedItem> feedItem;
    ImageLoader imageLoader = ImageLoader.getInstance();

    DBHelper dbHelper;

    public ListMenuAdapter(ArrayList<FeedItem> feedItem, Activity activity) {
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
        @BindView(R.id.my_name)
        TextView textName;
        @BindView(R.id.txt1)
        TextView txt1;

        @BindView(R.id.my_podrob)
        Button btn_pod;
        @BindView(R.id.kosu_kor)
        ToggleButton kosu_kor;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        dbHelper = new DBHelper(activity);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listmenuitem, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        item = feed_item.get(position);

        viewHolder.textName.setText(item.getName());
        viewHolder.txt1.setText(item.getItem_price()+" тг - "+item.getItem_id()+" штук");
        imageLoader.displayImage(item.getImage_url(),viewHolder.imageView);



        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            int id10 = item.getId();
            int idColIndex = c.getColumnIndex("id");
            int idColIndex1 = c.getColumnIndex("id1");
            int nameColIndex = c.getColumnIndex("name1");
            int numColIndex = c.getColumnIndex("num");
            int emailColIndex = c.getColumnIndex("imageurl");
            Log.d("id1="+idColIndex1+" num="+numColIndex, "name="+nameColIndex);
            do {
                Log.d("rrrr",
                        "ID = " + c.getInt(idColIndex) + ", name1 = "
                                + c.getString(nameColIndex) + ", num = "
                                + c.getString(numColIndex) + ", imageurl = "
                                + c.getString(emailColIndex)+ ", IIIIDD1 = "
                                + c.getInt(idColIndex1));
                if (id10 == c.getInt(idColIndex1)){
                    viewHolder.kosu_kor.setChecked(true);
                }

            } while (c.moveToNext());
        } else
            Log.d("rrrr", "0 rows");
        c.close();
        dbHelper.close();

        viewHolder.kosu_kor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = feed_item.get(position);
                if (viewHolder.kosu_kor.isChecked()) {
                    //  tbFavourite.setBackgroundResource(R.mipmap.yellowstar);
                    Log.d("rrrr1", "1");
                }
            }
        });

        viewHolder.kosu_kor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item = feed_item.get(position);
                if (isChecked) {
                    viewHolder.kosu_kor.setBackgroundResource(R.drawable.button_cart_round_background_gradient2);

                    ContentValues cv = new ContentValues();
                    String name1 = item.getName();
                    String num = "1";
                    String imageurl = item.getImage_url();
                    int id1 = item.getId(); //item da

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    cv.put("id1", id1);
                    cv.put("name1", name1);
                    cv.put("num", "1");
                    cv.put("imageurl", imageurl);
                    long rowID = db.insert("mytable", null, cv);
                    dbHelper.close();
                    Log.d("rrrr2", "2");
                } else {
                    viewHolder.kosu_kor.setBackgroundResource(R.drawable.button_cart_round_background_gradient);

                    ContentValues cv = new ContentValues();
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    int id1 = item.getId();
                    int delCount = db.delete("mytable", "id1 = " + id1, null);
                    dbHelper.close();
                    Log.d("rrrr2", "3");
                }
            }
        });



        viewHolder.btn_pod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = feed_item.get(position);

                activity.startActivity(new Intent(activity, MenuDetailActivity.class).putExtra("id",item.getId()));


            }
        });







        return convertView;
    }


}
