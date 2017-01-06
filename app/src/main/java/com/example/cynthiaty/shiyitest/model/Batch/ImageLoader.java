package com.example.cynthiaty.shiyitest.model.Batch;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * 作者：尚萍萍
 * 功能：利用Picasso加载网络图片并显示
 * 时间：2017-1-6
 */
public class ImageLoader {
    public static void with(Context context, String imageUrl, ImageView imageView) {
        Picasso.with(context).load(imageUrl).into(imageView);
    }
}
