package com.example.clientup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.clientup.FragmentMain.FragmentMenu1;
import com.example.clientup.FragmentMain.FragmentMenu2;
import com.example.clientup.FragmentMain.FragmentMenu3;
import com.example.clientup.FragmentMain.FragmentMenu4;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.clientup.Util.MyConstants.PREFS_NAME;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.menu_img1) ImageView menu_img1;
    @BindView(R.id.menu_img2) ImageView menu_img2;
    @BindView(R.id.menu_img3) ImageView menu_img3;
    @BindView(R.id.menu_img4) ImageView menu_img4;

    @BindColor(R.color.menu)
    int menu_color;
    @BindColor(R.color.gray)
    int gray;
    @BindColor(R.color.colorPrimary)
    int colorpri;
    SharedPreferences settings;
    public static String TOKEN;

    //String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        TOKEN = settings.getString("token","");
        Log.d("MAINTOKEN",TOKEN);

        SetColor();
        menu_img1.setImageResource(R.mipmap.iconbiractive);
        replaceFragment(new FragmentMenu1(),"Menu1");

        Permissions.check(this, new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                " ", new Permissions.Options(),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                    }
                });
        /*TextView textView = (TextView) findViewById(R.id.text);
        try {
            JSONObject jsonObject = new JSONObject(readJSONFromAsset());
            Log.d("myLog", jsonObject+" 234");
            JSONArray jArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jArray.length(); ++i) {
                name = jArray.getJSONObject(i).getString("name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        textView.setText(name); */
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>1) {

            String fragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 2).getName();
            Log.d("fragmentTag",fragmentTag);
            switch (fragmentTag){
                case "Menu1": SetColor();  menu_img1.setImageResource(R.mipmap.iconbiractive); break;
                case "Menu2": SetColor();  menu_img2.setImageResource(R.mipmap.iconekiactive); break;
                case "Menu3": SetColor();  menu_img3.setImageResource(R.mipmap.iconuwactive); break;
                case "Menu4": SetColor();  menu_img4.setImageResource(R.mipmap.icontortactive); break;

            }
            super.onBackPressed();

        }else {
            if (doubleBackToExitPressedOnce) {
                finish();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }

    }




    //  Menu click
    public void MenuClick(View view) {


//        FragmentManager fragments = getSupportFragmentManager();
//        Fragment homeFrag = fragments.findFragmentByTag("0");
//
//        if (fragments.getBackStackEntryCount() > 1) {
//            // We have fragments on the backstack that are poppable
//            fragments.popBackStackImmediate();
//        } else if (homeFrag == null || !homeFrag.isVisible()) {
//            // We aren't showing the home screen, so that is the next stop on the back journey
//            nav.setCurrentItem(0);
//        } else {
//            // We are already showing the home screen, so the next stop is out of the app.
//            supportFinishAfterTransition();
//        }


        SetColor();
        Fragment fragment = null;
        String title="";
        switch (view.getId()) {
            case R.id.menu1:   menu_img1.setImageResource(R.mipmap.iconbiractive);
                fragment = new FragmentMenu1(); title = "Menu1";
                break;
            case R.id.menu2:  menu_img2.setImageResource(R.mipmap.iconekiactive);
                fragment = new FragmentMenu2(); title = "Menu2";
                break;
            case R.id.menu3:  menu_img3.setImageResource(R.mipmap.iconuwactive);
                fragment = new FragmentMenu3(); title = "Menu3";
                break;
            case R.id.menu4:   menu_img4.setImageResource(R.mipmap.icontortactive);
                fragment = new FragmentMenu4(); title = "Menu4";
                break;
        }
        if(fragment!=null){
            replaceFragment(fragment,title);
        }


    }


    public  void replaceFragment(Fragment fragment, String title){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main, fragment, title);
        //fragmentTransaction.addToBackStack(title);
        fragmentTransaction.commit();
    }

    public void addFragmentOnTop(Fragment fragment) {

        //dialogPlus.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, fragment)
//                .addToBackStack(null)
//                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==1){

        }

    }

    private void SetColor() {
        menu_img1.setImageResource(R.mipmap.iconbirpassive);
        menu_img2.setImageResource(R.mipmap.iconekipassive);
        menu_img3.setImageResource(R.mipmap.iconuwpassive);
        menu_img4.setImageResource(R.mipmap.icontortpassive);
    }

}
