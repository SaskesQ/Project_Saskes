package com.example.saskesktu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Help2Activity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);
    }

    public void escape2 (View V){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
