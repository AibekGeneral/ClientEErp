package com.example.clientup.Vhod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.clientup.KorzinaActivity;
import com.example.clientup.MainActivity;
import com.example.clientup.R;
import com.example.clientup.ScannerActivity;
import com.example.clientup.data.DBHelper;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.clientup.Util.MyConstants.PREFS_NAME;

public class WelcomeActivity extends AppCompatActivity {

    DBHelper dbHelper;
    @BindView(R.id.edit_vhNomer1)
    EditText edit_vhNomer1;
    @BindView(R.id.edit_vhParol1)
    EditText edit_vhParol1;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        dbHelper = new DBHelper(WelcomeActivity.this);
    }

    @OnClick(R.id.text_vhZabil1)
    void zab(){
        Intent intent = new Intent(this, ZabilActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.text_vhZaregistr1)
    void reg(){
        Intent intent = new Intent(this, ZaregActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_vhVoiti1)
    void voi(){
        String login = edit_vhNomer1.getText().toString();
        String par = edit_vhParol1.getText().toString();


        AndroidNetworking.post("http://admin07.pythonanywhere.com/user/login")
                .addBodyParameter("username","Client_1")
                .addBodyParameter("password","client")
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Log.d("AAAAAAA",response.getString("token"));

                    settings.edit().clear().commit();
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("token", response.getString("token"));
                    //editor.putInt( "userId", 1);
                    editor.commit();

                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.delete("mytable", null, null);
                    dbHelper.close();

                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.d("AAAAAAA",anError.toString());
            }
        });

    }

}
