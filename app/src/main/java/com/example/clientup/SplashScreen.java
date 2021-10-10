package com.example.clientup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.clientup.Vhod.WelcomeActivity;

public class SplashScreen extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(runn1, 1000);
    }

    Runnable runn1 = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashScreen.this, WelcomeActivity.class));
            finish();
        }
    };

}
