package com.example.clientup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.clientup.Adapter.ListMenuAdapter;
import com.example.clientup.Util.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.clientup.MainActivity.TOKEN;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.neewsss_list)
    ListView listView;

    @BindView(R.id.korzina)
    ImageButton korzina;

    ArrayList<FeedItem> feedItem;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        id = getIntent().getExtras().getInt("id");

        GetDataFromJSON();
    }

    private void GetDataFromJSON() {
        Log.d("myyLog", "dfs");
        //http://moidom.zhasai.kz/api/service
        feedItem = new ArrayList<FeedItem>();
        AndroidNetworking.get("http://admin07.pythonanywhere.com/client/restoran/1/table/1/category/"+id+"/")
                .addHeaders("content-type", "application/json")
                .addHeaders("Authorization", "Token "+TOKEN)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i=0;i<response.length();i++){
                                JSONObject object = (JSONObject) response.get(i);
                                FeedItem item = new FeedItem();
                                item.setId(object.getInt("id"));
                                item.setName(object.getString("name"));
                                item.setItem_id(object.getInt("unit"));
                                item.setAdvert_desc(object.getString("body"));
                                item.setItem_price(object.getInt("price"));
                                item.setImage_url(object.getString("image"));
                                feedItem.add(item);
                            }


                            listView.setAdapter(new ListMenuAdapter(feedItem, MenuActivity.this));






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("myyLog", "des");
                        Toast.makeText(MenuActivity.this, anError.getErrorBody(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.korzina)
    void korz(){
        //ShowDialog();
    }
    @OnClick(R.id.txt_menuNazad)
    void nazad(){
        finish();
    }
}
