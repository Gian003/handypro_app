package com.ucucite.handypro_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class SignUp_Activity extends AppCompatActivity {

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView LogIn_btn = findViewById(R.id.LogIn_Btn);

        LogIn_btn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp_Activity.this, Login_Activity.class);
            startActivity(intent);
        });

        CustomEditText InitialPassword = findViewById(R.id.Password_Initial_Input);
        CustomEditText ConfirmPassword = findViewById(R.id.Password_Confirm_Input);

        setUpPasswordVisibilityToggle(InitialPassword, true);
        setUpPasswordVisibilityToggle(ConfirmPassword, false);
    }

    private void setUpPasswordVisibilityToggle(CustomEditText passwordField, boolean isInitialPassword) {
        passwordField.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
               if (passwordField.getCompoundDrawables()[DRAWABLE_RIGHT] != null) {
                   if (event.getRawX() >= (passwordField.getRight() - passwordField.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                       boolean isVisible = isInitialPassword ? isPasswordVisible : isConfirmPasswordVisible;

                       if (isVisible) {
                           passwordField.setTransformationMethod(new android.text.method.PasswordTransformationMethod());
                           passwordField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pass_visibility_off, 0);
                       } else {
                           passwordField.setTransformationMethod(null);
                           passwordField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pass_visibility_on, 0);
                       }

                       if (passwordField.getText() != null) {
                           passwordField.setSelection(passwordField.getText().length());
                       }

                       if (isInitialPassword) {
                           isPasswordVisible = !isPasswordVisible;
                       } else {
                           isConfirmPasswordVisible = !isConfirmPasswordVisible;
                       }

                       v.performClick();
                       return true;
                   }
               }
            }
            return false;
        });

        passwordField.setOnClickListener(v -> {

        });
    }
}