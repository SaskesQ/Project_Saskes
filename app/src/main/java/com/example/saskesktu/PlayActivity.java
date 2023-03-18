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
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    BoardLogic boardLogic;
    TextView BlackCapturedTextView;
    TextView WhiteCapturedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Paslepiamas ActionBar'as
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        boardLogic = new BoardLogic();
        BlackCapturedTextView = findViewById(R.id.textView11);
        WhiteCapturedTextView = findViewById(R.id.textView12);
    }

    public void escape1 (View V){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    View returnBoardView()
    {
        return this.findViewById(android.R.id.content).getRootView();
    }
    public void onCheckerClick(View V){

        String viewIDFull = getResources().getResourceName(V.getId());
        int placeId = Integer.parseInt(viewIDFull.substring(viewIDFull.lastIndexOf("/") + 2));

        boardLogic.CheckerClicked(V, PlayActivity.this, returnBoardView(), placeId);
        BlackCapturedTextView.setText(String.valueOf(boardLogic.BlackCaptured));
        WhiteCapturedTextView.setText(String.valueOf(boardLogic.WhiteCaptured));


    }

}