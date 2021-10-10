package com.example.clientup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.clientup.Util.Book2;
import com.example.clientup.Util.FeedItem;
import com.example.clientup.data.DBHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.clientup.MainActivity.TOKEN;

public class KorzinaActivity extends AppCompatActivity {
    @BindView(R.id.list_kor)
    SwipeMenuListView listView;

    @BindView(R.id.udalit_kor)
    ImageButton udalit;

    @BindView(R.id.korinbei)
    LinearLayout korinbei;
    @BindView(R.id.relll)
    RelativeLayout relll;

    ArrayList<FeedItem> feedItem;
    ArrayList<Book2> recentFamous;

    DBHelper dbHelper;

    ListKorzinaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korzina);
        ButterKnife.bind(this);

        dbHelper = new DBHelper(KorzinaActivity.this);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Отмена");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_white);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        // set creator
        listView.setMenuCreator(creator);

        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //ApplicationInfo item = mAppList.get(position);
                Book2 item = recentFamous.get(position);
                switch (index) {
                    case 0:
                        // open
                        //open(item);
                        break;
                    case 1:
                        // delete

                        ContentValues cv = new ContentValues();
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        int id1 = item.getIdka();
                        Log.d("armani", item.getName());
                        int delCount = db.delete("mytable", "id1 = " + id1, null);
                        dbHelper.close();

                        recentFamous.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // set MenuStateChangeListener
        listView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
            }

            @Override
            public void onMenuClose(int position) {
            }
        });

        // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());


        getTestData();
        //GetDataFromJSON();
    }

    public void getTestData() {
        recentFamous = new ArrayList<Book2>();
        ContentValues cv = new ContentValues();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);

        if (c.moveToFirst()) {

            int idColIndex = c.getColumnIndex("id");
            int idColIndex1 = c.getColumnIndex("id1");
            int nameColIndex = c.getColumnIndex("name1");
            int numColIndex = c.getColumnIndex("num");
            int emailColIndex = c.getColumnIndex("imageurl");

            do {
                Log.d("rrrr",
                        "ID1 = " + c.getInt(idColIndex) + ", name1 = "
                                + c.getString(nameColIndex) + ", num = "
                                + c.getString(numColIndex) + ", imageurl = "
                                + c.getString(emailColIndex)+ ", IIIIDD = "
                                + c.getInt(idColIndex1));

                recentFamous.add(new Book2(c.getString(nameColIndex), c.getString(numColIndex),
                        c.getString(emailColIndex), c.getInt(idColIndex), c.getInt(idColIndex1)));
            } while (c.moveToNext());
//            FamousAdapter mAdapter = new FamousAdapter(getActivity(),recentFamous );
//            famousRecyclerView.setAdapter(mAdapter);
            mAdapter = new ListKorzinaAdapter(recentFamous,KorzinaActivity.this);
            listView.setAdapter(mAdapter);
            //listView.setAdapter(new ListKorzinaAdapter(recentFamous, KorzinaActivity.this));

        } else
            Log.d("rrrr", "0 rows");
        c.close();
        dbHelper.close();
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


                                //listView.setAdapter(new ListKorzinaAdapter(feedItem, KorzinaActivity.this));

                            }else {
                                Toast.makeText(KorzinaActivity.this, response+" ", Toast.LENGTH_SHORT).show();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("myyLog", anError+"des");
                        Toast.makeText(KorzinaActivity.this, anError.getErrorBody(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.udalit_kor)
    void btnud(){

        ShowDialog();
    }
    public void ShowDialog(){
        final Dialog dialog = new Dialog(KorzinaActivity.this);
        dialog.setContentView(R.layout.dialog_udalit);
        Button otmenaProf = (Button) dialog.findViewById(R.id.otmena_profile);
        Button vitiProf = (Button) dialog.findViewById(R.id.viti_profile);

        dialog.show();
        otmenaProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        vitiProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("mytable", null, null);
                dbHelper.close();

                //mAdapter.updateItems(recentFamous);

                if (!(recentFamous.isEmpty())) {
                    recentFamous.clear();
                    mAdapter.notifyDataSetChanged();
                }
                //getTestData();
                relll.setVisibility(View.GONE);
                korinbei.setVisibility(View.VISIBLE);


            }
        });

    }

    @OnClick(R.id.text_nazKor)
    void txtnaz(){
        finish();
    }


    @OnClick(R.id.btn_otpKor)
    void btnotp(){
        //startActivity(new Intent(KorzinaActivity.this, ParolActivity.class));

//        relll.setVisibility(View.GONE);
//        korinbei.setVisibility(View.VISIBLE);

        //ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query("mytable", null, null, null, null, null, null);

        String ULKENSOZ = "";

        JSONObject js = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int k = 0;
        if (c.moveToFirst()) {

            int idColIndex = c.getColumnIndex("id");
            int idColIndex1 = c.getColumnIndex("id1");
            int nameColIndex = c.getColumnIndex("name1");
            int numColIndex = c.getColumnIndex("num");
            int emailColIndex = c.getColumnIndex("imageurl");

            ULKENSOZ = "";
            Log.d("id1="+idColIndex+" num="+numColIndex, "name="+nameColIndex);
            do {
                Log.d("rrrrka",
                        "ID = " + c.getInt(idColIndex) + ",\n name1 = "
                                + c.getString(nameColIndex) + ",\n num = "
                                + c.getString(numColIndex) + ",\n imageurl = "
                                + c.getString(emailColIndex)+ ", IIIIDD1 = "
                                + c.getInt(idColIndex1));
                ULKENSOZ = ULKENSOZ + "{\"client\":\"1\",\"product\":"+"\""+c.getInt(idColIndex1)+"\""+",\"unit\":"+"\""+c.getString(numColIndex)+"\""+"},";

                JSONObject img = new JSONObject();

                try {
                    img.put("client", "1");
                    img.put("product", String.valueOf(c.getInt(idColIndex1)));
                    img.put("unit", c.getString(numColIndex));
                    jsonArray.put(img);
                    k++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } while (c.moveToNext());

            try {
                js.put("", jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Log.d("RORORAAA", js + " ***");
            Log.d("RORORAAATT", jsonArray + " ***");

        } else
            Log.d("rrrr", "0 rows");

        //Log.d("ROROR", ULKENSOZ);
        String kesilgen = ULKENSOZ.substring(0, ULKENSOZ.length()-1);
        Log.d("RORORkes", String.valueOf(jsonArray));
        Log.d("RORORkes", TOKEN);
        c.close();
        dbHelper.close();

        String ss ="[{\"client\":\"1\",\"product\":\"2\",\"unit\":\"1\"},{\"client\":\"1\",\"product\":\"3\",\"unit\":\"1\"}]";

        //http://admin07.pythonanywhere.com/client/create/
        AndroidNetworking.post("http://admin07.pythonanywhere.com/client/restoran/1/table/1/add_product")
                .addJSONArrayBody(jsonArray)
                .addHeaders("content-type", "application/json")
                .addHeaders("Authorization", "Token "+TOKEN)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ROROR"," mam");

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("mytable", null, null);
                dbHelper.close();

                relll.setVisibility(View.GONE);
                korinbei.setVisibility(View.VISIBLE);

//                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                    startActivity(intent);
                //Toast.makeText(ZaregActivity.this, "Успешно зарегистрировань", Toast.LENGTH_SHORT).show();
                //finish();


            }

            @Override
            public void onError(ANError anError) {
                Log.d("ROROR",anError.toString()+" pap");
            }
        });
    }



    public class ListKorzinaAdapter extends BaseAdapter {
        Activity activity;
        ArrayList<FeedItem> feed_item;
        ArrayList<Book2> book2;
        //FeedItem item;
        Book2 book2item;
        ListView listView1;
        int sani=0;


        DBHelper dbHelper;

        ArrayList<FeedItem> feedItem;
        ImageLoader imageLoader = ImageLoader.getInstance();

        public ListKorzinaAdapter(ArrayList<Book2> book2, Activity activity) {
            this.activity = activity;
            //this.feed_item = feedItem;
            this.book2 = book2;
        }
//        public void updateItems(ArrayList<Book2> newList) {
//            if (!newList.equals(null)) {
//                newList.clear();
//                newList.addAll(newList);
//                this.notifyDataSetChanged();
//            }
//        }
        @Override
        public int getCount() {
            return book2.size();
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


            @BindView(R.id.quantity)
            TextView quantity;
            @BindView(R.id.my_name)
            TextView my_name;
            @BindView(R.id.my_price)
            TextView my_price;

            @BindView(R.id.minus)
            Button minus;
            @BindView(R.id.plus)
            Button plus;

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
                convertView = inflater.inflate(R.layout.listkorzinaitem, parent, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            book2item = book2.get(position);

            viewHolder.my_name.setText(String.valueOf(book2item.getName()));
            viewHolder.quantity.setText(book2item.getNum());
            imageLoader.displayImage(book2item.getImageUrl(),viewHolder.imageView);


//            holder.buttonFam.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    ContentValues cv = new ContentValues();
//                    SQLiteDatabase db = dbHelper.getWritableDatabase();
//                    int id1 = books.getId();
//                    int delCount = db.delete("mytable", "id1 = " + id1, null);
//                    dbHelper.close();
//
//                    allBooks.remove(position);
//                    notifyItemRemoved(position);
//                    notifyItemRangeChanged(position,allBooks.size());
//                    Log.d("myLogss", "1");
//
//                }
//            });


            viewHolder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    book2item = book2.get(position);
                    sani=Integer.parseInt((String) viewHolder.quantity.getText())-1;
                    if (sani==0){
                        ContentValues cv = new ContentValues();
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        int id1 = book2item.getIdka();
                        Log.d("armani", book2item.getName());
                        int delCount = db.delete("mytable", "id1 = " + id1, null);
                        dbHelper.close();

                        book2.remove(position);
                        notifyDataSetChanged();
                        sani=0;
                    }
                    //viewHolder.quantity.setText(String.valueOf(sani));
                }
            });
            viewHolder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    book2item = book2.get(position);
                    sani=Integer.parseInt((String) viewHolder.quantity.getText())+1;

                    ContentValues cv = new ContentValues();
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    int id = book2item.getId();
                    int idka = book2item.getIdka();
                    Log.d("rrrrrrrupddat", "updated1 rows count = " + book2item.getId());
                    //int delCount = db.delete("mytable", "id1 = " + id1, null);

                    cv.put("id1", idka);
                    cv.put("name1", book2item.getName());
                    cv.put("num", sani+"");
                    cv.put("imageurl", book2item.getImageUrl());
                    int updCount = db.update("mytable", cv, "id = ?",
                            new String[] {String.valueOf(id)});
                    //db.update("mytable", cv, "_id="+idka, null);
                    //Log.d("rrrrupddat", "updated rows count = " + updCount);

                    Cursor c = db.query("mytable", null, null, null, null, null, null);

                    if (c.moveToFirst()) {

                        int idColIndex = c.getColumnIndex("id");
                        int idColIndex1 = c.getColumnIndex("id1");
                        int nameColIndex = c.getColumnIndex("name1");
                        int numColIndex = c.getColumnIndex("num");
                        int emailColIndex = c.getColumnIndex("imageurl");
                        Log.d("id1="+idColIndex+" num="+numColIndex, "name="+nameColIndex);
                        do {
                            Log.d("rrrrka",
                                    "ID = " + c.getInt(idColIndex) + ",\n name1 = "
                                            + c.getString(nameColIndex) + ",\n num = "
                                            + c.getString(numColIndex) + ",\n imageurl = "
                                            + c.getString(emailColIndex)+ ", IIIIDD1 = "
                                            + c.getInt(idColIndex1));

                        } while (c.moveToNext());
                    } else
                        Log.d("rrrr", "0 rows");

                    dbHelper.close();

                    viewHolder.quantity.setText(String.valueOf(sani));
                }
            });


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
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


}
