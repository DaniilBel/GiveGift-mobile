package com.example.givegiftdesign.preference;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.res.Resources;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.givegiftdesign.R;

import java.util.ArrayList;

public class PreferenceBlock {
    public PreferenceBlock(CardView cardViewBlock,
                           ConstraintLayout innerBlock,
                           TextView pref,
                           Button closeBtn,
                           Resources resources,
                           ArrayList<String> prefs) {
        this.cardViewBlock = cardViewBlock;
        this.innerBlock = innerBlock;
        this.pref = pref;
        this.closeBtn = closeBtn;
        this.resources = resources;

        setPrefBlock();
        setDeleteBtn(prefs);
    }

    CardView cardViewBlock;
    ConstraintLayout innerBlock;
    TextView pref;
    Button closeBtn;
    Resources resources;

    public View prefViewParam(String selectedItem) {
        pref.setText(selectedItem);

        return cardViewBlock;
    }

    public int getId() {
        return cardViewBlock.getId();
    }

    private void setDeleteBtn(ArrayList<String> prefs) {
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup parent = (ViewGroup) cardViewBlock.getParent();
                parent.removeView(cardViewBlock);
                prefs.remove(String.valueOf(pref.getText()));
            }
        });
    }

    private void setPrefBlock() {
        // Самый внешний элемент, который может задать border radius
        final int cardMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.pref_cardview_margin),
                resources.getDisplayMetrics()
                );
        final int cardCornerRadius = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.cardview_cornerradius),
                resources.getDisplayMetrics()
        );
        ViewGroup.MarginLayoutParams cardViewParams = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardViewBlock.setId(View.generateViewId());
        cardViewParams.setMargins(cardMargin, cardMargin, cardMargin, cardMargin);
        cardViewBlock.setLayoutParams(cardViewParams);
        cardViewBlock.setRadius(cardCornerRadius);
        //

        // В этом слое можно правильно расположить элементы
        innerBlock.setLayoutParams(new CardView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        innerBlock.setBackgroundColor(resources.getColor(R.color.orange));
        //

        // Название предпочтения
        final int textPaddingStart = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.pref_textview_paddingstart),
                resources.getDisplayMetrics()
        );
        final int textSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.pref_textview_textsize),
                resources.getDisplayMetrics()
        );
        pref.setId(View.generateViewId());
        pref.setPaddingRelative(textPaddingStart, 5, 0, 5);
        pref.setTextColor(Color.WHITE);
        pref.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        pref.setTypeface(Typeface.create("serif", Typeface.NORMAL));
        //

        // Кнопка для удаления предпочтения из внешнего layout
        final int btnSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.pref_button_width),
                resources.getDisplayMetrics()
        );
        closeBtn.setId(View.generateViewId());
        closeBtn.setLayoutParams(new ConstraintLayout.LayoutParams(
                btnSize,
                btnSize
        ));
        closeBtn.setBackground(resources.getDrawable(R.color.orange));
        closeBtn.setOutlineProvider(null);
        closeBtn.setStateListAnimator(null);
        closeBtn.setText(R.string.delete);
        closeBtn.setTextColor(resources.getColor(R.color.white));
        //

        // Сразу добавляем элементы в ContraintLayout, чтобы можно было
        // применить ограничения на расположение
        innerBlock.addView(pref);
        innerBlock.addView(closeBtn);

        // Установка ограничений в ConstraintLayout
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(innerBlock);
        constraintSet.connect(pref.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraintSet.connect(pref.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(pref.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

        constraintSet.connect(closeBtn.getId(), ConstraintSet.LEFT, pref.getId(), ConstraintSet.RIGHT);
        constraintSet.connect(closeBtn.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(closeBtn.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

        constraintSet.applyTo(innerBlock);
        cardViewBlock.addView(innerBlock);
    }
}
