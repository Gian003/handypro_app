package com.ucucite.handypro_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class SignUp_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        TextView LogIn_btn = findViewById(R.id.LogIn_Btn);

        LogIn_btn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp_Activity.this, Login_Activity.class);
            startActivity(intent);
        });
    }
}