package com.example.clientup.Vhod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.clientup.MainActivity;
import com.example.clientup.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZaregActivity extends AppCompatActivity {

    @BindView(R.id.edit_vhName1)
    EditText edit_vhName1;
    @BindView(R.id.edit_vhLogin1)
    EditText edit_vhLogin1;
    @BindView(R.id.edit_vhPochta1)
    EditText edit_vhPochta1;
    @BindView(R.id.edit_vhTel1)
    EditText edit_vhTel1;
    @BindView(R.id.edit_vhParol1)
    EditText edit_vhParol1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zareg);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.bacck)
    void back(){
        finish();
    }

    @OnClick(R.id.btn_vhVoiti1)
    void voi(){

        String name = edit_vhName1.getText().toString();
        String login = edit_vhLogin1.getText().toString();
        String pochta = edit_vhPochta1.getText().toString();
        String tele = edit_vhTel1.getText().toString();
        String par = edit_vhParol1.getText().toString();


        AndroidNetworking.post("http://dalasoft.pythonanywhere.com/client/create/")
                .addBodyParameter("username",name)
                .addBodyParameter("email",pochta)
                .addBodyParameter("last_name",login)
                .addBodyParameter("password",par)
                .addBodyParameter("mobile",tele)
                .addBodyParameter("avatar","")
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {

                    //Log.d("AAAAAAA",response.getString(""));

//                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                    startActivity(intent);
                    Toast.makeText(ZaregActivity.this, "Успешно зарегистрировань", Toast.LENGTH_SHORT).show();
                    finish();


            }

            @Override
            public void onError(ANError anError) {
                Log.d("AAAAAAA",anError.toString());
            }
        });

    }

}
