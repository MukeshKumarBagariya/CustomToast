package com.example.customtoastlib;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class CustomToastMessage {
    public CustomToastMessage(Builder builder){
        LayoutInflater inflater = LayoutInflater.from(builder.context);
        View view = inflater.inflate(R.layout.m_custom_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        textView.setText(builder.msg);
        if (builder.textColor != 0) {
            textView.setTextColor(builder.textColor);
        }

        if (builder.backgroundColor == 0) {
            builder.backgroundColor = ContextCompat.getColor(builder.context, android.R.color.transparent);
        }
        Toast toast = new Toast(builder.context);
        toast.setView(view);
        toast.show();
    }

    public static class Builder{
        private Context context;
        private String msg;
        private EnumForDurartion myEnum;
        private int gravity = 0;

        private int backgroundColor = 0, textColor = 0, icon = 0, iconColor = 0, radius = 0;
        public Builder(Context context, String msg){
            this.context = context;
            this.msg = msg;
            gravity = Gravity.BOTTOM;
        }
        public Builder duration(EnumForDurartion myEnum) {
            this.myEnum = myEnum;
            return this;
        }
        public CustomToastMessage show() {
            return new CustomToastMessage(this);
        }
        public Builder backgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }
        public Builder iconColor(int iconColor) {
            this.iconColor = iconColor;
            return this;
        }
        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }
    }
    public static void success(Context context, String msg){
        new Builder(context, msg)
                .duration(EnumForDurartion.LONG)
                .backgroundColor(ContextCompat.getColor(context, R.color.green))
                .textColor(ContextCompat.getColor(context, R.color.black))
                .show();
    }
    public static void errors(Context context, String msg){
        new Builder(context, msg)
                .duration(EnumForDurartion.LONG)
                .backgroundColor(ContextCompat.getColor(context, R.color.red))
                .textColor(ContextCompat.getColor(context, R.color.white))
                .show();
    }
    public static void warnings(Context context, String msg){
        new Builder(context, msg)
                .duration(EnumForDurartion.LONG)
                .backgroundColor(ContextCompat.getColor(context, R.color.yellow))
                .textColor(ContextCompat.getColor(context, R.color.black))
                .show();
    }
}
