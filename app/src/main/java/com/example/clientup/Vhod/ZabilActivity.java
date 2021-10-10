package com.example.clientup.Vhod;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.clientup.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZabilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zabil);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.bacck)
    void back(){
        finish();
    }

    @OnClick(R.id.btn_vhPochta)
    void otp(){
        ShowDialog();
    }

    public void ShowDialog(){
        final Dialog dialog = new Dialog(ZabilActivity.this);
        dialog.setContentView(R.layout.dialog_page_zabil);
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
