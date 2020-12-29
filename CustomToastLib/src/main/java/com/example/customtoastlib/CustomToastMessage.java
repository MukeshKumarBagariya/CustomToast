package com.example.customtoastlib;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class CustomToastMessage {

    public CustomToastMessage(Builder builder) {
        final float scale = builder.context.getResources().getDisplayMetrics().density;

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

//        if (builder.icon == 0) {
//            imageView.setVisibility(View.GONE);
//            if (TextUtils.isEmpty(builder.faString) || builder.faString.length() == 0) {
//                faTextView.setVisibility(View.GONE);
//            } else {
//                faTextView.setVisibility(View.VISIBLE);
//                faTextView.setText(builder.faString);
//            }
//        } else {
//            imageView.setImageResource(builder.icon);
//            faTextView.setVisibility(View.GONE);
//        }

        if (builder.icon != 0 && builder.iconColor != 0) {
            imageView.setColorFilter(builder.iconColor, PorterDuff.Mode.SRC_ATOP);
        }

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(builder.radius);
        shape.setStroke(builder.strokewidth, builder.strokeColor);
        shape.setColor(builder.backgroundColor);
        view.setBackground(shape);

        if (builder.typeface != null) {
            textView.setTypeface(builder.typeface);
        }

        if (builder.isBold) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        }

        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, builder.size);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                ((int) (builder.width * scale + 0.5f), (int) (builder.height * scale + 0.5f));
        imageView.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        params.setMargins((int) (scale * builder.spacing), 0, 0, 0);
        textView.setLayoutParams(params);

        view.setPadding(builder.padding, builder.padding, builder.padding, builder.padding);

        // Create Toast
        Toast toast = new Toast(builder.context);
        toast.setView(view);

        if (builder.myEnum != null) {
            if (builder.myEnum.getId() == EnumForDurartion.LONG.getId()) {
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast.setDuration(Toast.LENGTH_SHORT);
            }
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        if (builder.gravity == Gravity.TOP || builder.gravity == Gravity.BOTTOM) {
            toast.setGravity(builder.gravity, 0, builder.margin);
        } else if (builder.gravity == Gravity.START || builder.gravity == Gravity.END ||
                builder.gravity == Gravity.LEFT || builder.gravity == Gravity.RIGHT) {
            toast.setGravity(builder.gravity, builder.margin, 0);
        } else {
            toast.setGravity(builder.gravity, 0, 0);
        }

        toast.show();
    }

    public static class Builder {

        private Context context;
        private String msg;
        private EnumForDurartion myEnum;
        private int backgroundColor = 0, textColor = 0, icon = 0, iconColor = 0, radius = 0;
        private Typeface typeface;
        private boolean isBold;
        private int gravity = 0;
        private int margin = 0;
        private int size = 0;
        private int width = 0, height = 0;
        private int spacing = 0;
        private int strokewidth = 0;
        private int strokeColor = 0;
        private int padding = 0;
        private String faString = "";

        public Builder(Context context, String msg) {
            this.context = context;
            this.msg = msg;

            // default values
            backgroundColor = ContextCompat.getColor(context, android.R.color.transparent);
            textColor = ContextCompat.getColor(context, android.R.color.black);
            radius = 4;
            gravity = Gravity.BOTTOM;
            margin = 36;
            size = 20;
            width = 24;
            height = 24;
            spacing = 8;
            padding = 4;
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

        public Builder icon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder icon(String faString) {
            this.faString = faString;
            return this;
        }

        public Builder iconColor(int iconColor) {
            this.iconColor = iconColor;
            return this;
        }

        public Builder corner(int radius) {
            this.radius = radius;
            return this;
        }

        public Builder typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        public Builder isBold(boolean isBold) {
            this.isBold = isBold;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder margin(int position) {
            this.margin = position;
            return this;
        }

        public Builder padding(int padding) {
            this.padding = padding;
            return this;
        }

        public Builder textSize(int size) {
            this.size = size;
            return this;
        }

        public Builder iconSize(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder spacing(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public Builder stroke(int strokewidth, int strokeColor) {
            this.strokewidth = strokewidth;
            this.strokeColor = strokeColor;
            return this;
        }
    }

    public static void success(Context context, String msg, Typeface typeface) {
        new Builder(context, msg)
                .duration(EnumForDurartion.LONG)
                .backgroundColor(ContextCompat.getColor(context, R.color.green))
                .textColor(ContextCompat.getColor(context, R.color.black))
                .typeface(typeface)
                .stroke(2, ContextCompat.getColor(context, R.color.dark_green))
                .icon(R.drawable.ic_round_check_circle_24)
                .isBold(true)
                .textSize(18)
                .corner(16)
                .margin(56)
                .spacing(8)
                .padding(36)
                .show();
    }

    public static void error(Context context, String msg, Typeface typeface) {
        new Builder(context, msg)
                .duration(EnumForDurartion.LONG)
                .backgroundColor(ContextCompat.getColor(context, R.color.red))
                .textColor(ContextCompat.getColor(context, R.color.white))
                .typeface(typeface)
                .isBold(true)
                .stroke(2, ContextCompat.getColor(context, R.color.dark_red))
                .textSize(18)
                .corner(16)
                .margin(56)
                .spacing(8)
                .padding(36)
                .show();
    }

    public static void progress(Context context, String msg, Typeface typeface) {
        new Builder(context, msg)
                .duration(EnumForDurartion.LONG)
                .backgroundColor(ContextCompat.getColor(context, R.color.red))
                .textColor(ContextCompat.getColor(context, R.color.white))
                .typeface(typeface)
                .stroke(2, ContextCompat.getColor(context, R.color.dark_red))
                .isBold(true)
                .textSize(18)
                .corner(16)
                .margin(56)
                .spacing(8)
                .padding(36)
                .show();
    }
}