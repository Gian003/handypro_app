package com.ucucite.handypro_app;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

public class PassVisibilityHandler {

    private static boolean isPasswordVisible = false;
    private static boolean isConfirmPasswordVisible = false;

    public static void setUpPasswordVisibilityToggle(CustomEditText passwordField, boolean isInitialPassword) {
        passwordField.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                Drawable[] drawables = passwordField.getCompoundDrawables();

                if (drawables[DRAWABLE_RIGHT] != null) {
                    if (event.getRawX() >= (passwordField.getRight() - drawables[DRAWABLE_RIGHT].getBounds().width())) {

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

        passwordField.setOnClickListener(View::performClick);
    }
}
