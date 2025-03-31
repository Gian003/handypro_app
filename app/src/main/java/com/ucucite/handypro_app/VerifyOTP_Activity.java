package com.ucucite.handypro_app;

import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerifyOTP_Activity extends AppCompatActivity {

    EditText OTPinput1;
    EditText OTPinput2;
    EditText OTPinput3;
    EditText OTPinput4;
    EditText OTPinput5;
    EditText OTPinput6;

    EditText[] InputTexts;
    Button VerifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verify_otp);

        OTPinput1 = findViewById(R.id.OTP1);
        OTPinput2 = findViewById(R.id.OTP2);
        OTPinput3 = findViewById(R.id.OTP3);
        OTPinput4 = findViewById(R.id.OTP4);
        OTPinput5 = findViewById(R.id.OTP5);
        OTPinput6 = findViewById(R.id.OTP6);
        InputTexts = new EditText[]{OTPinput1, OTPinput2, OTPinput3, OTPinput4, OTPinput5, OTPinput6};


        for (EditText InputText: InputTexts) {
            InputText.addTextChangedListener(new GenericTextWatcher(InputText, InputTexts));
        }
    }
}