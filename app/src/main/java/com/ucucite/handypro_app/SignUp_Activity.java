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

    PassVisibilityHandler passVisibilityHandler;

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

        passVisibilityHandler.setUpPasswordVisibilityToggle(InitialPassword, true);
        passVisibilityHandler.setUpPasswordVisibilityToggle(ConfirmPassword, false);
    }

}