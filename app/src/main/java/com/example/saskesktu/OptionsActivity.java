package com.example.saskesktu;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {
    public int bgVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        //Paslepiamas ActionBar'as
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        boardBackground();

    }
    //Metodas, kuris leidzia pasirinkti lentos modeli.
    //Jei niekas nepasirenkama, visada naudojamas v1 modelis, jei pasirenkama, modelis nustatomas
    //pagal pasirinkima (v1 arba v2)
    public void boardBackground(){

        TextView changed = findViewById(R.id.boardChTxt);
        SharedPreferences pref = getSharedPreferences("BG_VAL", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        ImageButton v1_button = (ImageButton) findViewById(R.id.board_v1);
        v1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgVal = 1;
                editor.putInt("BG_VAL", bgVal);
                editor.apply();
                changed.setText("Lentos modelis sėkmingai pakeistas!");
            }
        });
        ImageButton v2_button = (ImageButton) findViewById(R.id.board_v2);
        v2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgVal = 2;
                editor.putInt("BG_VAL", bgVal);
                editor.apply();
                changed.setText("Lentos modelis sėkmingai pakeistas!");

            }
        });
        editor.putInt("BG_VAL", bgVal);
        editor.apply();
    }
    public void backButton(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}