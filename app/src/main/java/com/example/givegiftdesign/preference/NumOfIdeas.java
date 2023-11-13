package com.example.givegiftdesign.preference;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

public class NumOfIdeas {
    public void handle(SeekBar seekBarCount, EditText countOf) {
        // Изменяет значение в EditText
        seekBarCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countOf.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Изменяет ползунок в зависимости от значения в count в EditText
        countOf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                seekBarCount.setProgress(start);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    int progress = Integer.parseInt(s.toString());
                    seekBarCount.setProgress(progress);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
