package com.example.saskesktu;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saskesktu.MainActivity;
public class PlayActivity extends AppCompatActivity {

    BoardLogic boardLogic;
    TextView BlackCapturedTextView;
    TextView WhiteCapturedTextView;
    TextView Player1TextView;
    TextView Player2TextView;

    String player1Name;

    String player2Name;
    MainActivity PLayers;
    boolean switch_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        BlackCapturedTextView = findViewById(R.id.textView12);
        WhiteCapturedTextView = findViewById(R.id.textView11);
        Player1TextView= findViewById(R.id.textView13);
        Player2TextView= findViewById(R.id.textView14);
        Intent intent = new Intent(this, MainActivity.class);

        //------------------------------------------------------------------------------------------------------------

        AlertDialog.Builder builder2 = new AlertDialog.Builder(PlayActivity.this);
        builder2.setTitle("Player2 name");
        builder2.setMessage("Player2, please sign your name.\n");

        final EditText nameInput2 = new EditText(PlayActivity.this);
        // Set the message and title of the popup window




        nameInput2.setInputType(InputType.TYPE_CLASS_TEXT);
        builder2.setView(nameInput2);
        //EditText nameInput2 = new EditText(MainActivity.this);
        //nameInput2.setInputType(InputType.TYPE_CLASS_TEXT);





        builder2.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                player2Name=nameInput2.getText().toString();



                Toast.makeText(PlayActivity.this, "Player2 name is " + player2Name, Toast.LENGTH_LONG).show();
                Player2TextView.setText(String.valueOf(player2Name));


            }
        });
        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                startActivity(intent);
                dialogInterface.cancel();
            }
        });

        //--------------------------------------------------------------------
        AlertDialog.Builder builder1 = new AlertDialog.Builder(PlayActivity.this);
        builder1.setTitle("Player1 name");
        builder1.setMessage("PLayer1, please sign your name.\n" );

        final EditText nameInput1 = new EditText(PlayActivity.this);
        // Set the message and title of the popup window

        //Intent intent = new Intent(this, PlayActivity.class);
        // startActivity(intent);


        nameInput1.setInputType(InputType.TYPE_CLASS_TEXT);
        builder1.setView(nameInput1);
        //EditText nameInput2 = new EditText(MainActivity.this);
        //nameInput2.setInputType(InputType.TYPE_CLASS_TEXT);





        builder1.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                player1Name=nameInput1.getText().toString();
                Player1TextView.setText(String.valueOf(player1Name));
                Toast.makeText(PlayActivity.this, "Player1 name is " + player1Name, Toast.LENGTH_LONG).show();
                builder2.show();


            }
        });
        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.cancel();
                startActivity(intent);
            }
        });



        builder1.show();




        //---------------------------------------------------------------------------------------------------------------


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
            Player1TextView.setText(String.valueOf(player2Name));
            Player2TextView.setText(String.valueOf(player1Name));
        }
        else {
            switch_status = false;
            Player1TextView.setText(String.valueOf(player1Name));
            Player2TextView.setText(String.valueOf(player2Name));

        }
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
                Intent intent =new Intent(PlayActivity.this, PlayActivity.class);
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