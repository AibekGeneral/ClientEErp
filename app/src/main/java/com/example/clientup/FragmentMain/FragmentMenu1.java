package com.example.clientup.FragmentMain;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.clientup.Adapter.GridMenuAdapter;
import com.example.clientup.KorzinaActivity;
import com.example.clientup.R;
import com.example.clientup.ScannerActivity;
import com.example.clientup.Util.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.clientup.MainActivity.TOKEN;


public class FragmentMenu1 extends Fragment {

    @BindView(R.id.two_grid)
    GridView two_grid;

    @BindView(R.id.korzina)
    ImageButton korzina;
    @BindView(R.id.image_qr)
    ImageView image_qr;

    @BindView(R.id.korinbei)
    LinearLayout korinbei;

    @BindColor(R.color.colorPrimary)
    int colorpri;
    @BindColor(R.color.white)
    int menu;

    ArrayList<FeedItem> feedItem;
    int preSelectedIndex = -1;
    int podid = 0,s;
    GridMenuAdapter adapter;

    String name2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu1, container, false);
        ButterKnife.bind(this, view);

        //GetDataFromJSON();

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();

        Animation animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);

        image_qr.setBackgroundResource(R.drawable.rounded_qr);
        image_qr.startAnimation(animFadein);

//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (!isInterrupted()) {
//                    try {
//                        //image_qr.setBackgroundColor(menu);
//                        Thread.sleep(1000);
//
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Animation animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
//
//                                image_qr.setBackgroundResource(R.drawable.rounded_qr);
//                                image_qr.startAnimation(animFadein);
////                                if (minit==0) {
////                                    povtornoVh.setEnabled(true);
////                                    povtornoVh.setTextColor(Color.parseColor("#0D3F72"));
////                                }
//                            }
//                        });
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }; t.start();


        //settings = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        //swipe.setOnRefreshListener(this);
        //GetDataFromJSON();


    }

    private void adjustGridView() {
        two_grid.setNumColumns(2);
        two_grid.setVerticalSpacing(35);
        two_grid.setHorizontalSpacing(8);


    }

    private void GetDataFromJSON() {
        Log.d("MAINTOKEN2",TOKEN);
        feedItem = new ArrayList<FeedItem>();
        //http://192.168.0.101:5000/client/restoran/1/table_number/2/
        AndroidNetworking.get("http://admin07.pythonanywhere.com/client/restoran/1/table/1/")
                .addHeaders("Authorization", "Token "+TOKEN)
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i=0;i<response.length();i++){
                        JSONObject object = (JSONObject) response.get(i);
                        FeedItem item = new FeedItem();
                        item.setId(object.getInt("id"));
                        item.setName(object.getString("name"));
                        item.setItem_id(object.getInt("restoran"));
                        item.setImage_url(object.getString("image"));
                        feedItem.add(item);
                    }

                    adapter = new GridMenuAdapter(feedItem, getActivity());
                    two_grid.setAdapter(adapter);
                    //two_grid.setAdapter(new GridAdapter(feedItem, TwoPodpisActivity.this));
                    adjustGridView();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.d("MAINTOKEN1",anError.getErrorBody());
                Toast.makeText(getActivity(), anError.getErrorBody(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.image_qr)
    void image_qr(){
        Intent i = new Intent(getActivity(), ScannerActivity.class);
        startActivityForResult(i, 1);
        //startActivity(new Intent(getActivity(), ScannerActivity.class));
    }
    @OnClick(R.id.korzina)
    void korz(){
        startActivity(new Intent(getActivity(), KorzinaActivity.class));
    }
    @OnClick(R.id.basu)
    void basu(){
        two_grid.setVisibility(View.GONE);
        korinbei.setVisibility(View.VISIBLE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("wert", String.valueOf(resultCode));
        // Add your code here
        //Toast.makeText(getActivity(), "Fragment Got it: " + requestCode + ", " + resultCode, Toast.LENGTH_SHORT).show();


        if (resultCode == -1 && requestCode == 1) {
            two_grid.setVisibility(View.VISIBLE);
            korinbei.setVisibility(View.GONE);
            GetDataFromJSON();
            //name2 = data.getStringExtra("name2");
            //podid = "";
            //podid = data.getStringExtra("podid");
            //vibrat_poluch.setText(name2);
        }

//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.dualPane);
//        fragment.onActivityResult(requestCode, resultCode, data);

//        Fragment fragment = (Fragment) getChildFragmentManager().findFragmentByTag(childTag);
//        if (fragment != null) {
//            fragment.onActivityResult(requestCode, resultCode, intent);
//        }

    }

}
