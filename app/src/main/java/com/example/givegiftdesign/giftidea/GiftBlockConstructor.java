package com.example.givegiftdesign.giftidea;

import android.annotation.SuppressLint;
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

import com.example.givegiftdesign.R;
import com.example.givegiftdesign.giftidea.GiftBlock;
import com.squareup.picasso.Picasso;

public class GiftBlockConstructor {

    /**
     * Помимо присвоения переменных в конструкторе конструируется блок
     * @param cardView - внешний контейнер
     * @param linearLayout - внутренний контейнер
     * @param imageView - картинка из инета
     * @param textView - описание товара
     * @param button - кнопка 'перейти по ссылке'
     * @param resources - размерности
     */
    public GiftBlockConstructor(CardView cardView, LinearLayout innerLinearLayout,
                                LinearLayout linearLayout, ImageView imageView,
                                TextView textView, Button button, Resources resources) {
        this.cardView = cardView;
        this.innerLinearLayout = innerLinearLayout;
        this.linearLayout = linearLayout;
        this.imageView = imageView;
        this.textView = textView;
        this.button = button;
        this.resources = resources;

        setGiftBlock();
    }

    CardView cardView;
    LinearLayout linearLayout;
    LinearLayout innerLinearLayout;
    ImageView imageView;
    TextView textView;
    Button button;
    Resources resources;

    /**
     * Берет картинку из сети и присваивает imageView
     * @param gb - куда присвоить картинку
     * @return - CardView с присвоенной картинкой
     */
    public View giftViewParams(GiftBlock gb) {
        Picasso.get().load(gb.getImageUrl()).into(imageView);
        textView.setText(gb.getDescription());
        setUrlBtn(gb.getGiftUrl());

        return cardView;
    }

    /**
     * По нажатии на кнопку переходит по ссылке
     * @param url - ссылка
     */
    private void setUrlBtn(String url) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            }
        });
    }

    /**
     * Устанавливаются CardView -> LinearLayout -> innerLinearLayout -> ImageView -> TextView -> Button ->
     * добавляем в LinearLayout -> LinearLayout добавляем в CardView
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setGiftBlock() {
        // Задание CardView для скругленных углов
        final int cardMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_cardview_margin),
                resources.getDisplayMetrics()
        );
        final int cardPaddingBottom = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.main_cardview_padding_bottom),
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
        cardParams.gravity = Gravity.CENTER;
        cardParams.setMargins(cardMargin, cardMargin, cardMargin, cardMargin);
        cardView.setLayoutParams(cardParams);
        cardView.setPadding(0, 0, 0, cardPaddingBottom);
        cardView.setRadius(cardCornerRadius);
        //

        // внешний слой для позиционирования элементов
        final int linearLayoutPaddingBottom = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.linearlayout_padding_bottom),
                resources.getDisplayMetrics()
        );
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
//        linearLayout.setTranscriptMode(LinearLayout.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        linearLayout.setPadding(
                0,
                0,
                0,
                linearLayoutPaddingBottom
        );
        linearLayout.setGravity(Gravity.BOTTOM);
//        linearLayout.setStackFromBottom(true);

        //

        // внутренний слой для позиционирования элементов
        final int innerLinearLayoutPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.inner_linearlayout_padding),
                resources.getDisplayMetrics()
        );
        innerLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        innerLinearLayout.setBackgroundColor(resources.getColor(R.color.white));
        innerLinearLayout.setPadding(
                0,
                0,
                0,
                innerLinearLayoutPadding
        );
        innerLinearLayout.setOrientation(LinearLayout.VERTICAL);
        innerLinearLayout.setGravity(Gravity.CENTER);
        //

        // Картинка из инета
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

        // Описание товара
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
        textView.setGravity(Gravity.CENTER);
        textParams.setMargins(
                textMarginStart,
                textMarginTop,
                textMarginEnd,
                0
        );
        textView.setLayoutParams(textParams);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setText(resources.getText(R.string.gift_desc));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //

        // Кнопка для перехода по ссылке
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
        button.setText(resources.getText(R.string.wildberries));
        button.setAllCaps(false);
        button.setTextColor(Color.WHITE);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        button.setTypeface(Typeface.create("sans", Typeface.BOLD));
        //

        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        linearLayout.addView(button);

        innerLinearLayout.addView(linearLayout);

        cardView.addView(innerLinearLayout);
    }
}
