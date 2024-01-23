package com.example.givegiftdesign.giftidea;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.givegiftdesign.MainActivity;
import com.example.givegiftdesign.R;
import com.example.givegiftdesign.giftidea.GiftBlock;
import com.squareup.picasso.Picasso;

public class NewGiftConstructor {

    View includedGiftBlock;
    ImageView imageView;
    TextView textView;
    Button button;
    MainActivity m;

    /**
     * Помимо присвоения переменных в конструкторе вызывается шаблон блока
     * @param view - шаблон блока
     */
    public NewGiftConstructor(View view, MainActivity mainActivity) {
        includedGiftBlock = view;
        m = mainActivity;

        imageView = includedGiftBlock.findViewById(R.id.gift_image);
        textView = includedGiftBlock.findViewById(R.id.gift_desc_two);
        button = includedGiftBlock.findViewById(R.id.gift_search_two);
    }

    /**
     * Берет картинку из сети и присваивает imageView
     * @param gb - куда присвоить картинку
     * @return - CardView с присвоенной картинкой
     */
    public View giftViewParams(GiftBlock gb) {
        Picasso.get().load(gb.getImageUrl()).into(imageView);
        textView.setText(gb.getDescription());
        setUrlBtn(gb.getGiftUrl());

        return includedGiftBlock;
    }

    /**
     * По нажатии на кнопку переходит по ссылке
     * @param url - ссылка
     */
    private void setUrlBtn(String url) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            m.startActivity(intent);
        });
    }
}
