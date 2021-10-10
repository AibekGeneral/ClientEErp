package com.example.clientup.FragmentMain;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.fragment.app.Fragment;

import com.example.clientup.R;
import com.example.clientup.RedaktirovatActivity;
import com.example.clientup.SmenitActivity;
import com.example.clientup.SplashScreen;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentMenu4 extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu4, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btn_smn)
    void btnSmn() {
        startActivity(new Intent(getActivity(), SmenitActivity.class));
    }

    @OnClick(R.id.redaktir)
    void btnRed() {
        startActivity(new Intent(getActivity(), RedaktirovatActivity.class));
    }


    @OnClick(R.id.exit)
    void exit_btn() {
        ShowDialog();
    }

    public void ShowDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_exit);
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

                Intent newIntent = new Intent(getActivity(), SplashScreen.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(newIntent);

                //finish();


            }
        });

    }

}
