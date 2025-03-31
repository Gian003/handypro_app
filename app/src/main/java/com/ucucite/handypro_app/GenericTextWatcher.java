package com.ucucite.handypro_app;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class GenericTextWatcher implements TextWatcher {

    private EditText[] InputTexts;
    private EditText currentInput;

    public GenericTextWatcher (EditText currentInput, EditText[] InputText){
        this.currentInput = currentInput;
        this.InputTexts = InputText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 1) {
            for (int i = 0; i < InputTexts.length; i++) {
                if (InputTexts[i] == currentInput && i < InputTexts.length - 1) {
                    InputTexts[i + 1].requestFocus();
                    break;
                }
            }
        } else if (s.length() == 0) {
            for (int i = 0; i < InputTexts.length; i++) {
                if (InputTexts[i] == currentInput && i > 0) {
                    InputTexts[i -1].requestFocus();
                    break;
                }
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
