package com.ucucite.handypro_app;

import android.content.Intent;
import android.os.Bundle;
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
    }

}