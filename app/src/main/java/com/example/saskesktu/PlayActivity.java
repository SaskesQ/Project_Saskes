package com.example.saskesktu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ImageButton;

public class PlayActivity extends AppCompatActivity {

    String[] statusArray = {
            "Black", "NA", "Black", "NA", "Black", "NA", "Black", "NA",
            "NA", "Black", "NA", "Black", "NA", "Black", "NA", "Black",
            "Black", "NA", "Black", "NA", "Black", "NA", "Black", "NA",
            "NA", "0", "NA", "0", "NA", "0", "NA", "0",
            "0", "NA", "0", "NA", "0", "NA", "0", "NA",
            "NA", "Light", "NA", "Light", "NA", "Light", "NA", "Light",
            "Light", "NA", "Light", "NA", "Light", "NA", "Light", "NA",
            "NA", "Light", "NA", "Light", "NA", "Light", "NA", "Light"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Paslepiamas ActionBar'as
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


    }

    public void escape1 (View V){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onCheckerClick(View V){
        //Gaunama paspaustos šaškės vieta
        String viewIDFull = getResources().getResourceName(V.getId());
        int placeId = Integer.parseInt(viewIDFull.substring(viewIDFull.lastIndexOf("/") + 2));

        //Tikrinamas jos statusas ir pakeičiamas statusas atitinkamai
        switch(statusArray[placeId])
        {
            case "Black":
                V.setBackground(ContextCompat.getDrawable(PlayActivity.this, R.drawable.dark_piece_pressed));
                statusArray[placeId] = "BlackPressed";
                break;

            case "Light":
                V.setBackground(ContextCompat.getDrawable(PlayActivity.this, R.drawable.light_piece_pressed));
                statusArray[placeId] = "LightPressed";
                break;

            case "BlackPressed":
                V.setBackground(ContextCompat.getDrawable(PlayActivity.this, R.drawable.dark_piece));
                statusArray[placeId] = "Black";
                break;

            case "LightPressed":
                V.setBackground(ContextCompat.getDrawable(PlayActivity.this, R.drawable.light_piece));
                statusArray[placeId] = "Light";
                break;
        }

    }

}