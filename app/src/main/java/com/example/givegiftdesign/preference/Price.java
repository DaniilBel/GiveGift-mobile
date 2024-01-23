package com.example.givegiftdesign.preference;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Price {
    private List<Float> values = new ArrayList<>();

    public Price(float v1, float v2) {
        values.add(v1);
        values.add(v2);
    }

    public void handle(RangeSlider rangeSlider, EditText minPrice, EditText maxPrice) {

        // Изменяет значение в EditText
        rangeSlider.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
                                                 @Override
                                                 public void onStartTrackingTouch(@NonNull RangeSlider slider) {
                                                     values = slider.getValues();
                                                     minPrice.setText(String.valueOf(Math.floor(values.get(0))));
                                                     maxPrice.setText(String.valueOf(Math.floor(values.get(1))));
                                                 }

                                                 @Override
                                                 public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                                                     values = slider.getValues();
                                                     minPrice.setText(String.valueOf(Math.floor(values.get(0))));
                                                     maxPrice.setText(String.valueOf(Math.floor(values.get(1))));
                                                 }
                                             }
        );

        // Изменяет ползунок в зависимости от значения в EditText
        minPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                values.set(0, Float.valueOf(String.valueOf(minPrice.getText())));
                rangeSlider.setValues(values);
            }
        });

        // Изменяет ползунок в зависимости от значения в EditText
        maxPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                values.set(1, Float.valueOf(String.valueOf(maxPrice.getText())));
                rangeSlider.setValues(values);
            }
        });
    }
}
