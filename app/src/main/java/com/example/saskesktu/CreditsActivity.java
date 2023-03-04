package com.example.saskesktu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }



    public void grįžimas(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}