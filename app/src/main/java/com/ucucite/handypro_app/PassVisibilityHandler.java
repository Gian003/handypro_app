//A static class to handle password visibility toggle

package com.ucucite.handypro_app;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

public class PassVisibilityHandler {

    //declared condition for password visibility where is password visible is false
    private static boolean isPasswordVisible = false;
    private static boolean isConfirmPasswordVisible = false;


    //method to handle password visibility where it takes two parameters CustomEditText and boolean
    public static void setUpPasswordVisibilityToggle(CustomEditText passwordField, boolean isInitialPassword) {
        passwordField.setOnTouchListener((v, event) -> { //method where it listens for touch event i.e. password icon to determine the visibility of the text
            final int DRAWABLE_RIGHT = 2; //index of password icon (right side)

            //checks if the touch event is up
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Drawable[] drawables = passwordField.getCompoundDrawables();

                //checks if the password icon is not null
                if (drawables[DRAWABLE_RIGHT] != null) {
                    //checks if the touch event is within the password icon
                    if (event.getRawX() >= (passwordField.getRight() - drawables[DRAWABLE_RIGHT].getBounds().width())) {
                        //checks if the visibility of the password or the touch event occurred whether it is initial password or confirm password
                        boolean isVisible = isInitialPassword ? isPasswordVisible : isConfirmPasswordVisible;

                        if (isVisible) {//if it is not concealed, it transforms to visibility off icon and turns the text to invisible(with asterisk)
                            passwordField.setTransformationMethod(new android.text.method.PasswordTransformationMethod());
                            passwordField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pass_visibility_off, 0);
                        } else {//if it is, it transforms to visibility on icon and turns the text to visible
                            passwordField.setTransformationMethod(null);
                            passwordField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pass_visibility_on, 0);
                        }
                        //ensures that the cursor still stays at the end of the text even after the event happened
                        if (passwordField.getText() != null) {
                            passwordField.setSelection(passwordField.getText().length());
                        }
                        //updates the visibility condition
                        if (isInitialPassword) {
                            isPasswordVisible = !isPasswordVisible;
                        } else {
                            isConfirmPasswordVisible = !isConfirmPasswordVisible;
                        }
                        //performs click event and returns true to indicate the event had been handled
                        v.performClick();
                        return true;
                    }
                }
            }
            return false;//returns false if the event was not handled
        });
        //sets onClickListener for the password field to perform a click event
        passwordField.setOnClickListener(View::performClick);
    }
}
