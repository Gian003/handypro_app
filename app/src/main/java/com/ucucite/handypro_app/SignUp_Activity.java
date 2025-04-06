package com.ucucite.handypro_app;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView LogIn_btn = findViewById(R.id.LogIn_Btn);

        //intent to go to login activity
        LogIn_btn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp_Activity.this, Login_Activity.class);
            startActivity(intent);
        });


        //passing xml id CustomEditText to java object to then be handled by PassVisibilityHandler
        CustomEditText InitialPassword = findViewById(R.id.Password_Initial_Input);
        CustomEditText ConfirmPassword = findViewById(R.id.Password_Confirm_Input);

        //password visibility toggle from PassVisibilityHandler class
        PassVisibilityHandler.setUpPasswordVisibilityToggle(InitialPassword, true);
        PassVisibilityHandler.setUpPasswordVisibilityToggle(ConfirmPassword, false);

        //input field if pressed "OK" or "Enter" moves to another input field
        EditText NextInput_Field1 = findViewById(R.id.SignUp_FNameInput);
        EditText NextInput_Field2 = findViewById(R.id.SignUp_LNameInput);
        EditText NextInput_Field3 = findViewById(R.id.SignUp_EmailInput);
        EditText NextInput_Field4 = findViewById(R.id.SignUp_NumberInput);
        //Initial and Confirm Password are not written here again to avoid redundancy


        //input field if pressed "OK" or "Enter" moves to another input field
        NextInput_Field1.setOnEditorActionListener((v, actionId, event) -> {
           if (actionId == EditorInfo.IME_ACTION_NEXT) {
               NextInput_Field2.requestFocus();
               return true;
           }
           return false;
        });

        NextInput_Field2.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                NextInput_Field3.requestFocus();
                return true;
            }
            return false;
        });

        NextInput_Field3.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                NextInput_Field4.requestFocus();
                return true;

            }
            return false;
        });

        NextInput_Field4.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                ConfirmPassword.requestFocus();
                return true;
            }
            return false;
        });

        ConfirmPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                InitialPassword.requestFocus();
                return true;
            }
            return false;
        });

        //Hides the keyboard when pressed "OK" or "Enter" to confirm password
        ConfirmPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                ConfirmPassword.clearFocus();;
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ConfirmPassword.getWindowToken(), 0);
                return true;
            }
            return false;
        });

        //Create button for create account to got to home activity
        Button CreateAccount_Btn = findViewById(R.id.button_CreateAccount);
        CreateAccount_Btn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp_Activity.this, Home_Activity.class);
            startActivity(intent);
        });
    }
}