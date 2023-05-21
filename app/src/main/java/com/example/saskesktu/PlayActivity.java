package com.example.saskesktu;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class PlayActivity extends AppCompatActivity {

    BoardLogic boardLogic;
    private CountDownTimer WhiteTimer;
    private CountDownTimer BlackTimer;
    TextView WhiteTimerTextView;
    TextView BlackTimerTextView;
    TextView BlackCapturedTextView;
    TextView WhiteCapturedTextView;
    TextView Player1TextView;
    TextView Player2TextView;
    long mačoLaikas=0;
    long LongLaikas;
    String player1Name;
    String player2Name;
    boolean switch_status = false;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        BlackCapturedTextView = findViewById(R.id.textView12);
        WhiteCapturedTextView = findViewById(R.id.textView11);
        Player1TextView= findViewById(R.id.leadb);
        Player2TextView= findViewById(R.id.textView14);
        WhiteTimerTextView = findViewById(R.id.whitetimer);
        BlackTimerTextView = findViewById(R.id.blacktimer);
        Intent intent = new Intent(this, MainActivity.class);


        //Iskvieciamas metodas, parenkantis atitinkama lentos modeli
        boardBackgroundV();
        // Langas mačo laikui-------------------------------------------------------
        AlertDialog.Builder builder4 = new AlertDialog.Builder(PlayActivity.this);
        builder4.setTitle(getResources().getString(R.string.TimeTittle));
        builder4.setMessage(getResources().getString(R.string.Timemessage));
        final EditText nameInput4 = new EditText(PlayActivity.this);

        // Set the message and title of the popup window
        nameInput4.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder4.setView(nameInput4);
        builder4.setPositiveButton(getResources().getString(R.string.done), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                    mačoLaikas = 60000*Integer.parseInt(nameInput4.getText().toString());
                    LongLaikas = 60000*Long.parseLong(nameInput4.getText().toString());
                    if(mačoLaikas <60000 || mačoLaikas>300000) {
                        Toast.makeText(PlayActivity.this, getResources().getString(R.string.WrongNumber), Toast.LENGTH_LONG).show();
                        builder4.show();
                    }

                WhiteTimer = new CountDownTimer(mačoLaikas, 1000) {
                    @Override
                    public void onTick(long l) {
                        WhiteTimerTextView.setText(String.valueOf((l/1000)));
                    }

                    @Override
                    public void onFinish() {
                        gameOver(v, "Black");
                    }
                };
                BlackTimer = new CountDownTimer(mačoLaikas, 1000) {
                    @Override
                    public void onTick(long l) {
                        BlackTimerTextView.setText(String.valueOf((l/1000)));
                    }

                    @Override
                    public void onFinish() {
                        gameOver(v, "White");
                    }
                };

                         boardLogic = new BoardLogic(WhiteTimer, BlackTimer);
                        WhiteTimerTextView.setText(String.valueOf(mačoLaikas/1000));
                        BlackTimerTextView.setText(String.valueOf(mačoLaikas/1000));
                        WhiteTimer.start();
            }
        });

        builder4.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                startActivity(intent);
                dialogInterface.cancel();
            }
        });







        //Langas parašantis žaidėjų vardus------------------------------------------------------------------------------------------------------------
        AlertDialog.Builder builder2 = new AlertDialog.Builder(PlayActivity.this);
        builder2.setTitle(getResources().getString(R.string.player) + "2");
        builder2.setMessage(getResources().getString(R.string.player) + "2," + getResources().getString(R.string.name));
        final EditText nameInput2 = new EditText(PlayActivity.this);
        // Set the message and title of the popup window
        nameInput2.setInputType(InputType.TYPE_CLASS_TEXT);
        builder2.setView(nameInput2);
        //EditText nameInput2 = new EditText(MainActivity.this);
        //nameInput2.setInputType(InputType.TYPE_CLASS_TEXT);
        builder2.setPositiveButton(getResources().getString(R.string.done), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                player2Name=nameInput2.getText().toString();
                Toast.makeText(PlayActivity.this, getResources().getString(R.string.player)
                        + "2" + getResources().getString(R.string.nameIs)+ " " + player2Name, Toast.LENGTH_LONG).show();
                Player2TextView.setText(String.valueOf(player2Name));
                builder4.show();
            }
        });
        builder2.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                startActivity(intent);
                dialogInterface.cancel();
            }
        });

        //--------------------------------------------------------------------
        AlertDialog.Builder builder1 = new AlertDialog.Builder(PlayActivity.this);
        builder1.setTitle(getResources().getString(R.string.player) + "1");
        builder1.setMessage(getResources().getString(R.string.player)+"1," + getResources().getString(R.string.name));

        final EditText nameInput1 = new EditText(PlayActivity.this);
        // Set the message and title of the popup window
        //Intent intent = new Intent(this, PlayActivity.class);
        // startActivity(intent);
        nameInput1.setInputType(InputType.TYPE_CLASS_TEXT);
        builder1.setView(nameInput1);
        //EditText nameInput2 = new EditText(MainActivity.this);
        //nameInput2.setInputType(InputType.TYPE_CLASS_TEXT);
        builder1.setPositiveButton(getResources().getString(R.string.done), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                player1Name=nameInput1.getText().toString();
                Player1TextView.setText(String.valueOf(player1Name));
                Toast.makeText(PlayActivity.this, getResources().getString(R.string.player)
                        + "1" + getResources().getString(R.string.nameIs) + " " + player1Name, Toast.LENGTH_LONG).show();
                builder2.show();
            }
        });
        builder1.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
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




    }

    public void boardBackgroundV(){
        SharedPreferences pref = getSharedPreferences("BG_VAL", MODE_PRIVATE);
        int bg_val = pref.getInt("BG_VAL", 1);
        View board = findViewById(R.id.cboardImageView);
        switch (bg_val) {
            case 2:
                board.setBackgroundResource(R.drawable.checkers_board_background_v2);
                break;
            default:
                board.setBackgroundResource(R.drawable.checkers_board_background);
        }
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
        builder.setMessage(getResources().getString(R.string.winner) + " " + winner + "!")
                .setTitle(getResources().getString(R.string.gameover));

        //For score table
        SharedPreferences prefs = getSharedPreferences("player_scores", MODE_PRIVATE);
        PlayerScoreManager scoreManager = new PlayerScoreManager();

        if(winner == "White"){
            Log.d("Winner", "White" + player2Name);
            scoreManager.addScore(player2Name, prefs);
        }else{
            Log.d("Winner", "Black" + player1Name);
            scoreManager.addScore(player1Name, prefs);
        }
        //--------------------
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
    public void surrenderAysWhite(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.surrender)).setTitle(getResources().getString(R.string.surr));
        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SurrenderWhite(v);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void surrenderAysBlack(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.surrender)).setTitle(getResources().getString(R.string.surr));
        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SurrenderBlack(v);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}