package com.example.clientup.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.example.clientup.R;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by nurbaqyt on 10.01.2018.
 */

public class ImagePagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    String[] images;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public ImagePagerAdapter(Context context,String[] images) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.image_pager_item, container, false);

        final ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

        //  Picasso.with(mContext).load(images[position]).placeholder(R.drawable.ic_image)
        //     .error(R.drawable.ic_image).into(imageView);

        imageLoader.displayImage(images[position],imageView);
        container.addView(itemView);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //mContext.startActivity(new Intent(mContext, ImageViewGalerey.class).putExtra("arrayImage",images).putExtra("position",position));
//            }
//        });
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}