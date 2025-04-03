//class OTP input where if you type on input it will move to next input

package com.ucucite.handypro_app;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class GenericTextWatcher implements TextWatcher {

    private EditText[] InputTexts;
    private EditText currentInput;


    //Constructor to take current input and array of all inputs
    public GenericTextWatcher (EditText currentInput, EditText[] InputText){
        this.currentInput = currentInput;
        this.InputTexts = InputText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    //adding digit and deleting a digit
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (s.length() == 1) { //if you typed digit in will then move to another input
            for (int i = 0; i < InputTexts.length; i++) {
                if (InputTexts[i] == currentInput && i < InputTexts.length - 1) {
                    InputTexts[i + 1].requestFocus();
                    break;
                }
            }
        } else if (s.length() == 0) { //else if you delete a digit will move to previous input
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
