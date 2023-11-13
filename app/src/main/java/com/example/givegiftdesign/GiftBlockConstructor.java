package com.example.givegiftdesign;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

public class GiftBlockConstructor {
    public GiftBlockConstructor(CardView cardView, LinearLayout linearLayout, ImageView imageView, TextView textView, Button button, Resources resources) {
        this.cardView = cardView;
        this.linearLayout = linearLayout;
        this.imageView = imageView;
        this.textView = textView;
        this.button = button;
        this.resources = resources;

        setGiftBlock();
    }

    CardView cardView;
    LinearLayout linearLayout;
    ImageView imageView;
    TextView textView;
    Button button;
    Resources resources;

    public View giftViewParams(GiftBlock gb) {
        Picasso.get().load(gb.getImageUrl()).into(imageView);
        textView.setText(gb.getDescription());
        setUrlBtn(gb.getGiftUrl());

        return cardView;
    }

    private void setUrlBtn(String url) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            }
        });
    }

    private void setGiftBlock() {
        // Задание CardView для скругленных углов
        final int cardMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_cardview_margin_top),
                resources.getDisplayMetrics()
        );
        final int cardCornerRadius = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.cardview_cornerradius),
                resources.getDisplayMetrics()
        );
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, cardMargin, 0, cardMargin);
        cardView.setLayoutParams(cardParams);
        cardView.setRadius(cardCornerRadius);
        //

        // Внутренний слой дя позиционирования элементов
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(resources.getColor(R.color.white));
        linearLayout.setGravity(Gravity.CENTER);
        //

        final int imgSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_img_size),
                resources.getDisplayMetrics()
        );
        final int imgMarginStart = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_img_margin_start),
                resources.getDisplayMetrics()
        );
        final int imgMarginTop = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_img_margin_top),
                resources.getDisplayMetrics()
        );
        final int marginEnd = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_img_margin_end),
                resources.getDisplayMetrics()
        );
        imageView.setId(View.generateViewId());
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                imgSize,
                imgSize
        );
        imageParams.setMargins(
                imgMarginStart,
                imgMarginTop,
                marginEnd,
                0
        );
        imageView.setLayoutParams(imageParams);
        imageView.setContentDescription(String.valueOf(R.string.gift_img));
        imageView.setImageResource(R.mipmap.ic_launcher);
        //

        final int textMarginStart = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_text_margin_start),
                resources.getDisplayMetrics()
        );
        final int textMarginTop = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_text_margin_top),
                resources.getDisplayMetrics()
        );
        final int textMarginEnd = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_text_margin_end),
                resources.getDisplayMetrics()
        );
        textView.setId(View.generateViewId());
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textParams.setMargins(
                textMarginStart,
                textMarginTop,
                textMarginEnd,
                0
        );
        textView.setLayoutParams(textParams);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setText(resources.getText(R.string.gift_desc));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //

        final int buttonWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_button_width),
                resources.getDisplayMetrics()
        );
        final int buttonMarginTop = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_button_margin_top),
                resources.getDisplayMetrics()
        );
        final int buttonMarginBottom = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_button_margin_bottom),
                resources.getDisplayMetrics()
        );
        final int buttonPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_button_padding),
                resources.getDisplayMetrics()
        );
        button.setId(View.generateViewId());
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                buttonWidth,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        buttonParams.setMargins(
                0,
                buttonMarginTop,
                0,
                buttonMarginBottom
        );
        button.setLayoutParams(buttonParams);
        button.setBackground(resources.getDrawable(R.drawable.orange_button_background));
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.basket, 0, 0, 0);
        button.setPadding(buttonPadding, 0, 0, 0);
        button.setText(resources.getText(R.string.megamarket));
        button.setAllCaps(false);
        button.setTextColor(Color.WHITE);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        button.setTypeface(Typeface.create("sans", Typeface.BOLD));
        //

        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        linearLayout.addView(button);

        cardView.addView(linearLayout);

    }
}
