package com.example.clientup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.clientup.Util.FeedItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryDetailActivity extends AppCompatActivity {

    @BindView(R.id.listview_history)
    ListView listView;

    @BindView(R.id.udalit_his)
    ImageButton udalit;


    ArrayList<FeedItem> feedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        ButterKnife.bind(this);

        GetDataFromJSON();
    }

    private void GetDataFromJSON() {


        feedItem = new ArrayList<FeedItem>();
        AndroidNetworking.get("http://moidom.zhasai.kz/api/city").build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("myyLog1", "dfs");
                            if(response.getString("status").equals("true")){
                                JSONArray data = response.getJSONArray("data");
                                for (int i=0;i<data.length();i++){
                                    JSONObject object = (JSONObject) data.get(i);
                                    FeedItem item = new FeedItem();
                                    item.setId(object.getInt("city_id"));
                                    item.setName(object.getString("city_name"));
                                    //item.setDesc(object.getString("service_desc"));
                                    //item.setImage_url(object.getString("service_image"));
                                    feedItem.add(item);
                                }


                                listView.setAdapter(new ListHistoryDetailAdapter(feedItem, HistoryDetailActivity.this));

                            }else {
                                Toast.makeText(HistoryDetailActivity.this, response+" ", Toast.LENGTH_SHORT).show();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("myyLog", anError+"des");
                        Toast.makeText(HistoryDetailActivity.this, anError.getErrorBody(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.text_nazHis)
    void txtnaz(){
        finish();
    }

    @OnClick(R.id.udalit_his)
    void btnud(){

    }



    public class ListHistoryDetailAdapter extends BaseAdapter {
        Activity activity;
        ArrayList<FeedItem> feed_item;
        FeedItem item;
        ListView listView1;
        int sani=0;

        ArrayList<FeedItem> feedItem;
        ImageLoader imageLoader = ImageLoader.getInstance();

        public ListHistoryDetailAdapter(ArrayList<FeedItem> feedItem, Activity activity) {
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



            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }



        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listhistdetailitem, parent, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            item = feed_item.get(position);

            //viewHolder.textTwoitem.setText(String.valueOf(item.getId()));
            imageLoader.displayImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMk5qkfe7-JJHMoDK5B0YA5y2yXN70owLhI43uv5_ept13Ws7bkw",viewHolder.imageView);




//        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                activity.startActivity(new Intent(activity, MenuDetailActivity.class).putExtra("id",item.getId()));
//
//
//            }
//        });




            return convertView;
        }
    }
}
