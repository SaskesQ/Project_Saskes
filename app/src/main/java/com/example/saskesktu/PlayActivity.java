package com.example.saskesktu;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    BoardLogic boardLogic;
    TextView BlackCapturedTextView;
    TextView WhiteCapturedTextView;

    boolean switch_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        BlackCapturedTextView = findViewById(R.id.textView12);
        WhiteCapturedTextView = findViewById(R.id.textView11);

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

        if(boardLogic.WhiteCaptured < 12 && boardLogic.BlackCaptured < 12)
        {
            BlackCapturedTextView.setText(String.valueOf(boardLogic.WhiteCaptured));
            WhiteCapturedTextView.setText(String.valueOf(boardLogic.BlackCaptured));
        } else if (boardLogic.WhiteCaptured == 12) {
            gameOver(V, "White");
        } else if (boardLogic.BlackCaptured == 12) {
            gameOver(V, "Black");
        }

    }
    public void pauseMenu(View V) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View pauseView = inflater.inflate(R.layout.pause_menu, null);
        final PopupWindow pauseMenu = new PopupWindow(pauseView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        pauseMenu.setBackgroundDrawable(new ColorDrawable());
        pauseMenu.showAtLocation(V, Gravity.CENTER, 0, 0);
        Button close = (Button) pauseView.findViewById(R.id.resume);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseMenu.dismiss();
            }
        });
        ImageButton home = (ImageButton) pauseView.findViewById(R.id.homeButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton restart = (ImageButton) pauseView.findViewById(R.id.restartButton);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });
    }
    public void gameOver(View V, String winner) {
        // Create a new AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the message and title of the popup window
        builder.setMessage("The winner is " + winner + "!")
                .setTitle("Game Over");

        // Add a button to the popup window
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                escape1(V);
            }
        });

        // Create and show the popup window
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void SurrenderWhite(View V){
        if (switch_status == false)
        gameOver(V, "Black");
        else
            gameOver(V, "White");

    }

    public void SurrenderBlack(View V){
        if (switch_status == false)
        gameOver(V, "White");
        else
            gameOver(V, "Black");
    }
}