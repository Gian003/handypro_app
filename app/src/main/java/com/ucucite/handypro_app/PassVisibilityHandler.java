package com.ucucite.handypro_app;

import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class PassVisibilityHandler extends AppCompatActivity {

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    public void setUpPasswordVisibilityToggle(CustomEditText passwordField, boolean isInitialPassword) {
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
