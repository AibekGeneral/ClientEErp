package com.example.clientup.FragmentMain;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.clientup.Adapter.ListZakazAdapter;
import com.example.clientup.R;
import com.example.clientup.ScannerActivity;
import com.example.clientup.Util.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.clientup.MainActivity.TOKEN;


public class FragmentMenu2 extends Fragment {
    @BindView(R.id.list_shet)
    ListView listView;

    @BindView(R.id.korinbei)
    LinearLayout korinbei;
    @BindView(R.id.relll)
    RelativeLayout relll;

    ArrayList<FeedItem> feedItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu2, container, false);
        ButterKnife.bind(this, view);

        GetDataFromJSON();

        return view;
    }

    private void GetDataFromJSON() {
        feedItem = new ArrayList<FeedItem>();
        AndroidNetworking.get("http://admin07.pythonanywhere.com/client/restoran/1/table/1/list")
                .addHeaders("Authorization", "Token "+TOKEN)
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                        for (int i=0;i<response.length();i++){
                            JSONObject object = (JSONObject) response.get(i);
                            FeedItem item = new FeedItem();
                            item.setId(object.getInt("id"));
                            item.setName(object.getString("product"));
                            item.setItem_id(object.getInt("unit"));
                            //item.setImage_url(object.getString("image"));
                            feedItem.add(item);
                        }

                        listView.setAdapter(new ListZakazAdapter(feedItem, getActivity()));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(getActivity(), anError.getErrorBody(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick(R.id.schet)
    void btnsh(){
        relll.setVisibility(View.GONE);
        korinbei.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.btn_zakShet)
    void btnzak(){
        //startActivity(new Intent(KorzinaActivity.this, ParolActivity.class));

        AndroidNetworking.post("http://admin07.pythonanywhere.com/client/restoran/1/table/1/finished_order")
                .addBodyParameter("'status'","1")
                .addBodyParameter("user_id","1")
                .addHeaders("content-type", "application/json")
                .addHeaders("Authorization", "Token "+TOKEN)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                ShowDialog();
                //pd.dismiss();
                //Log.d("AAAAAAA",response.getString(""));

//                Intent intent = new Intent(ScannerActivity.this, MainActivity.class);
//                intent.putExtra("URRL", result.getText());
//                startActivity(intent);

//                Intent intent2 = new Intent();
//                intent2.putExtra("name2", result.getText());
//                setResult(RESULT_OK, intent2);
//
//                finish();

                Toast.makeText(getActivity(), "Успешно отправлен", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(ANError anError) {
                Log.d("AAAAAAA",anError.toString());
            }
        });

    }


    public void ShowDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_page_zakrit);
        //TextView change_img = (TextView) dialog.findViewById(R.id.change_img);
        TextView cancelpod = (TextView) dialog.findViewById(R.id.cancelpod);

        dialog.show();
        cancelpod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

    }


}
