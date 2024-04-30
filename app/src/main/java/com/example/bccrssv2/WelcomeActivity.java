package com.example.bccrssv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button getStartedButton = findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start WeatherActivity when the button is clicked
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent); // Launch the new activity
                finish(); // Optional: close WelcomeActivity to prevent back-navigation
            }
        });
    }
}