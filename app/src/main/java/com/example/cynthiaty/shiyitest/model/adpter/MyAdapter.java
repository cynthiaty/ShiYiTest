package com.example.cynthiaty.shiyitest.model.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cynthiaty.shiyitest.R;
import com.example.cynthiaty.shiyitest.model.Batch.ImageLoader;
import com.example.cynthiaty.shiyitest.model.entity.Magazine;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * 作者：尚萍萍
 * 功能：自定义适配器adapter
 * 时间：2017-1-5
 */
public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Magazine> mData;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<Magazine> data) {
        mContext = context;
        mData = data;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    /**
     * convertView: 缓存第一屏数据（复用convertView提高ListView性能）
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView title = null;
        TextView content_text = null;
        TextView content_text_ = null;
        ImageView content_image = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);
            title = (TextView) convertView.findViewById(R.id.title);
            content_text = (TextView) convertView.findViewById(R.id.content_text);
            content_text_ = (TextView) convertView.findViewById(R.id.content_text_);
            content_image = (ImageView) convertView.findViewById(R.id.content_image);
            convertView.setTag(new ViewHolder(title, content_text, content_text_, content_image));
        }
        else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            title = viewHolder.title;
            content_text = viewHolder.content_text;
            content_text_ = viewHolder.content_text_;
            content_image = viewHolder.content_image;
        }

        Magazine magazine = mData.get(position);
        title.setText(magazine.getTitle());
        if (magazine.getText().equals("null")) {
            magazine.setText("");
        }
        if (magazine.getImage().equals("null") || magazine.getImage().equals("")) {
            magazine.setImage("");
            content_image.setVisibility(View.GONE);
            content_text.setVisibility(View.GONE);
            content_text_.setVisibility(View.VISIBLE);
            content_text_.setText(magazine.getText());
        }
        else {
            ImageLoader.with(mContext, magazine.getImage(), content_image);
            if (content_image.getDrawable() == null) {
                content_image.setVisibility(View.GONE);
                content_text.setVisibility(View.GONE);
                content_text_.setVisibility(View.VISIBLE);
                content_text_.setText(magazine.getText());
            }
            else {
                content_image.setVisibility(View.VISIBLE);
                content_text.setVisibility(View.VISIBLE);
                content_text_.setVisibility(View.GONE);
                content_text.setText(magazine.getText());
            }
        }
        return convertView;
    }

    /**
     * ViewHolder:静态类提高ListView性能
     */
    private static final class ViewHolder {
        private TextView title;
        private TextView content_text;
        private TextView content_text_;
        private ImageView content_image;

        public ViewHolder(TextView title, TextView content_text, TextView content_text_, ImageView content_image) {
            this.title = title;
            this.content_text = content_text;
            this.content_text_ = content_text_;
            this.content_image = content_image;
        }
    }
}