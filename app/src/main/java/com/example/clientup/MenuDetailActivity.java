package com.example.clientup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.clientup.Adapter.ImagePagerAdapter;
import com.example.clientup.Util.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

import static com.example.clientup.MainActivity.TOKEN;

public class MenuDetailActivity extends AppCompatActivity {

//    @BindView(R.id.my_detimage)
//    ImageView image;
    @BindView(R.id.texter)
    TextView texter;
    @BindView(R.id.my_name)
    TextView my_name;
    @BindView(R.id.minus1)
    TextView minus1;
    @BindView(R.id.minus2)
    TextView minus2;
        @BindView(R.id.view_pager)
        ViewPager viewPager;

    private CircleIndicator circleIndicator;

    String []image_url = {"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMk5qkfe7-JJHMoDK5B0YA5y2yXN70owLhI43uv5_ept13Ws7bkw",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMk5qkfe7-JJHMoDK5B0YA5y2yXN70owLhI43uv5_ept13Ws7bkw",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMk5qkfe7-JJHMoDK5B0YA5y2yXN70owLhI43uv5_ept13Ws7bkw"};
    TextView[] dots;

    ArrayList<FeedItem> feedItem;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        ButterKnife.bind(this);

        id = getIntent().getExtras().getInt("id");
        circleIndicator = findViewById(R.id.circle);

        GetDataFromJSON();
    }

    private void GetDataFromJSON() {
        Log.d("myyyyLog", "dfs "+id);
        //http://moidom.zhasai.kz/api/service
        feedItem = new ArrayList<FeedItem>();
        AndroidNetworking.get("http://admin07.pythonanywhere.com/client/restoran/1/table/1/category/"+2+"/product/"+id+"/")
                .addHeaders("content-type", "application/json")
                .addHeaders("Authorization", "Token "+TOKEN)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                                //JSONArray data = response.getJSONArray("data");
                                for (int i=0;i<response.length();i++){
                                    JSONObject object = (JSONObject) response.get(i);
                                    FeedItem item = new FeedItem();
                                    item.setId(object.getInt("id"));
                                    item.setName(object.getString("name"));
                                    item.setItem_id(object.getInt("unit"));
                                    item.setAdvert_desc(object.getString("body"));
                                    item.setItem_price(object.getInt("price"));
                                    item.setImage_url(object.getString("image"));

                                    texter.setText(object.getString("body"));
                                    my_name.setText(object.getString("name"));

                                    image_url[0]=object.getString("image");

                                    minus1.setText(object.getInt("unit")+" штук");
                                    minus2.setText(object.getInt("price")+" тг");

                                    //item.setDesc(object.getString("service_desc"));
                                    //item.setImage_url(object.getString("service_image"));
                                    feedItem.add(item);
                                }
                                viewPager.setAdapter(new ImagePagerAdapter(MenuDetailActivity.this,image_url));
                                circleIndicator.setViewPager(viewPager);
                                //viewPager.setOnPageChangeListener(viewPagerPageChangeListener);

                                //listView.setAdapter(new ListMenuAdapter(feedItem, MenuDetailActivity.this));






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("myyLog", "des");
                        Toast.makeText(MenuDetailActivity.this, anError.getErrorBody(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


//    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
//
//        @Override
//        public void onPageSelected(int position) {
//            for (int i = 0; i < dots.length; i++) {
//                dots[i].setTextColor(getResources().getColor(R.color.whiter));
//            }
//            dots[position].setTextColor(getResources().getColor(R.color.white));
//        }
//
//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//
//        }
//    };

    @OnClick(R.id.korzina)
    void korz(){
        //ShowDialog();
    }
}
