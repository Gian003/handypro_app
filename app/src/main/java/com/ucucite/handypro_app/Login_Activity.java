package com.ucucite.handypro_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        TextView SignUp_btn = findViewById(R.id.SignUp_Btn);

        SignUp_btn.setOnClickListener(v -> {
            Intent intent = new Intent(Login_Activity.this, SignUp_Activity.class);
            startActivity(intent);
        });

        CustomEditText LogIN_Pass = findViewById(R.id.Login_PassInput);

        PassVisibilityHandler.setUpPasswordVisibilityToggle(LogIN_Pass, true);
    }
}
