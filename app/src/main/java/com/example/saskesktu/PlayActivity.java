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

    boolean switch_status = false; // jeigu 0, tai naudoja viena logika, jeigu 1, tai kita
    BoardLogic boardLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Paslepiamas ActionBar'as
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        boardLogic = new BoardLogic();
    }

    public void escape1 (View V){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    View returnBoardView()
    {
        return this.findViewById(android.R.id.content).getRootView();
    }

    public void switch_sides(View V){


        String viewIDFull = getResources().getResourceName(V.getId());
        boardLogic.SwitchWasPressed(PlayActivity.this,returnBoardView());
        if (!switch_status){
            switch_status=true;
        }
        else
            switch_status=false;
    }



    public void onCheckerClick(View V){


        String viewIDFull = getResources().getResourceName(V.getId());
        int placeId = Integer.parseInt(viewIDFull.substring(viewIDFull.lastIndexOf("/") + 2));
        if(!switch_status)
            boardLogic.CheckerClicked(V, PlayActivity.this, returnBoardView(), placeId);
        else
            boardLogic.CheckerClicked2(V, PlayActivity.this, returnBoardView(), placeId);

    }

}