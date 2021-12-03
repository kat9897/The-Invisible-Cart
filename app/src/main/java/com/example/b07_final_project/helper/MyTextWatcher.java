package com.example.b07_final_project.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MyTextWatcher  implements TextWatcher {
    EditText et;

    public MyTextWatcher(EditText editText) {
        this.et = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et.getTag() != null) {
            // This is required to ensure EditText is edited by user and not through program
            if (et.hasFocus()) {
                int position = (int) et.getTag();
                String newText = et.getText() + "";
                //Implement your actions here........
                //you can get require things/ views from listView.getChildAt(position)..
            }

        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}