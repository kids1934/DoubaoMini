package com.example.doubaomini.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doubaomini.R;

public class MyToastUtil {

    public static void show(@NonNull Context mContext, @NonNull CharSequence c,@Nullable Bitmap img) {
        View v = View.inflate(mContext, R.layout.my_toast, null);
        TextView textView = v.findViewById(R.id.diary_toast_tv);
        textView.setText(c);
        if(img != null){
            ImageView imageView = v.findViewById(R.id.diary_toast_img);
            imageView.setImageBitmap(img);
            imageView.setVisibility(View.VISIBLE);
        }
        Toast toast = new Toast(mContext);
        toast.setView(v);
        toast.show();
    }
}
