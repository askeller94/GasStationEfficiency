package com.askapps.gasstationefficiency;

import android.widget.EditText;

public class Validation {
    public static boolean isAnyEditTextEmpty(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValueGreaterThanZero(EditText[] editTexts) {
        for (EditText editText : editTexts) {
            if (Double.parseDouble(editText.getText().toString().trim()) <= 0) {
                return false;
            }
        }
        return true;
    }

}