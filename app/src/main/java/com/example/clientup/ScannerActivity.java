package com.example.clientup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.clientup.Vhod.ZaregActivity;
import com.google.zxing.Result;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.clientup.MainActivity.TOKEN;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    //private ZXingScannerView mScannerView;
    static final Integer CAMERA = 0x1;
    ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        ButterKnife.bind(this);

        //ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = (ZXingScannerView)findViewById(R.id.scanner);
        //mScannerView = new ZXingScannerView(this);
        //contentFrame.addView(mScannerView);
        //mScannerView.setAutoFocus(true);
        askForPermission(Manifest.permission.CAMERA, CAMERA);
    }

    private void askForPermission(String permission, Integer requestCode) {

        if (ContextCompat.checkSelfPermission(ScannerActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ScannerActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(ScannerActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(ScannerActivity.this, new String[]{permission}, requestCode);
            }
        } else {

            // Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            mScannerView.setResultHandler(this);
//            Runnable task = new Runnable() {
//                public void run() {
//                    mScannerView.startCamera();
//                }
//            };
//            Thread thread = new Thread(task);
//            thread.start();
            mScannerView.startCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                //Camera
                case 1:
                    mScannerView.setResultHandler(this);

                    mScannerView.startCamera();
                    break;


            }

            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
    @Override
    public void onResume() {
        super.onResume();
        askForPermission(Manifest.permission.CAMERA, CAMERA);
    }

    @Override
    public void handleResult(Result result) {

        Toast.makeText(this, "Contents = " + result.getText() +
                ", Format = " + result.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        ProgressDialog pd = new ProgressDialog(ScannerActivity.this);
        pd.setMessage("loading");
        pd.show();

        AndroidNetworking.post("http://admin07.pythonanywhere.com/client/restoran/1/table/1/qr_code/activate")
                .addBodyParameter("'status'","1")
                .addBodyParameter("user_id","1")
                .addHeaders("content-type", "application/json")
                .addHeaders("Authorization", "Token "+TOKEN)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();
                //Log.d("AAAAAAA",response.getString(""));

//                Intent intent = new Intent(ScannerActivity.this, MainActivity.class);
//                intent.putExtra("URRL", result.getText());
//                startActivity(intent);

                Intent intent2 = new Intent();
                intent2.putExtra("name2", result.getText());
                setResult(RESULT_OK, intent2);

                finish();

                Toast.makeText(ScannerActivity.this, "Успешно активирован", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(ANError anError) {
                Log.d("AAAAAAA",anError.toString());
            }
        });



        // * Wait 3 seconds to resume the preview.
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(ScannerActivity.this);
            }
        }, 3000); */

    }

    @OnClick(R.id.bacckk)
    void bacckk(){
        finish();
        Log.d("togai", "gaeg");
        //startActivity(new Intent(ScannerActivity.this, ScannerActivity.class));
    }

}
