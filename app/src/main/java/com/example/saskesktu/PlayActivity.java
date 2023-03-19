package com.example.saskesktu;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    BoardLogic boardLogic;
    TextView BlackCapturedTextView;
    TextView WhiteCapturedTextView;

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
    public void onCheckerClick(View V){

        String viewIDFull = getResources().getResourceName(V.getId());
        int placeId = Integer.parseInt(viewIDFull.substring(viewIDFull.lastIndexOf("/") + 2));

        boardLogic.CheckerClicked(V, PlayActivity.this, returnBoardView(), placeId);
        BlackCapturedTextView.setText(String.valueOf(boardLogic.WhiteCaptured));
        WhiteCapturedTextView.setText(String.valueOf(boardLogic.BlackCaptured));
    }
    public void pauseMenu(View V){
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
    }
}