package com.example.clientup.FragmentMain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.clientup.Adapter.ListHistoryAdapter;
import com.example.clientup.R;
import com.example.clientup.Util.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentMenu3 extends Fragment {

    @BindView(R.id.histori_list)
    ListView histori_list;

    ArrayList<FeedItem> feedItem;
    ListHistoryAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu3, container, false);
        ButterKnife.bind(this, view);

        GetDataFromJSON();

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();


        //settings = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        //swipe.setOnRefreshListener(this);
        //GetDataFromJSON();


    }


    private void GetDataFromJSON() {
        feedItem = new ArrayList<FeedItem>();
        AndroidNetworking.get("http://admin07.pythonanywhere.com/client/1/history")
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
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

                        adapter = new ListHistoryAdapter(feedItem, getActivity());
                        histori_list.setAdapter(adapter);
                        //two_grid.setAdapter(new GridAdapter(feedItem, TwoPodpisActivity.this));

                    }else {
                        Toast.makeText(getActivity(), response+" ", Toast.LENGTH_SHORT).show();
                    }
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


}
