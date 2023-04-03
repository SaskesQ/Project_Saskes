package com.example.saskesktu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    //Kintamieji reikalingi background muzikai
    MediaPlayer backgroundSong;
    boolean isPlaying = false;
    String player1Name = "";
    public  String getPlayer2Name() { return player1Name;}
    public String getPlayer1Name(){return player2Name;}
    public  void setPlayer2Name(String a) { player1Name=a;}
    public void setPlayer1Name(String a){ player2Name=a;}
    String player2Name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Background dainos failas
        backgroundSong=MediaPlayer.create(MainActivity.this,R.raw.background_music);

        //Paslepiamas ActionBar'as
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    // Reaguoja į "Play" mygtuko paspaudimą, metodo pavadinimas sutampa su mygtuko argumentu "onClick"
    // Atidaro naują langą (naują "Activity")
    public void play(View v)
    {

        Intent intent = new Intent(this, PlayActivity.class);
        //AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
        /* builder2.setTitle("Player2 name");
        builder2.setMessage("Player2, please sign your name.\n");

        final EditText nameInput2 = new EditText(MainActivity.this);
        // Set the message and title of the popup window




        nameInput2.setInputType(InputType.TYPE_CLASS_TEXT);
        builder2.setView(nameInput2);
        //EditText nameInput2 = new EditText(MainActivity.this);
        //nameInput2.setInputType(InputType.TYPE_CLASS_TEXT);





        builder2.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                player2Name=nameInput2.getText().toString();
                Toast.makeText(MainActivity.this, "Player2 name is " + player2Name, Toast.LENGTH_LONG).show();
                if(player1Name!=null && player2Name != null){
                    setPlayer2Name(player2Name);
                    Log.d("Vardai",getPlayer1Name() + " " + getPlayer2Name());
                    startActivity(intent);
                }
                else if(player1Name == null && player2Name!=null)
                    Toast.makeText(MainActivity.this, "Player1 did not write his name ", Toast.LENGTH_LONG).show();
                else if(player2Name == null && player1Name!=null)
                    Toast.makeText(MainActivity.this, "Player2 did not write his name ", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Both players did not write their names ", Toast.LENGTH_LONG).show();



            }
        });
        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.cancel();
            }
        });

        //--------------------------------------------------------------------
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setTitle("Player1 name");
        builder1.setMessage("PLayer1, please sign your name.\n" );

        final EditText nameInput1 = new EditText(MainActivity.this);
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
                setPlayer1Name(player1Name);
                  Toast.makeText(MainActivity.this, "Player1 name is " + player1Name, Toast.LENGTH_LONG).show();
                  builder2.show();


            }
        });
        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.cancel();
            }
        });



        builder1.show();
        //--------------------------------------------------------------------


*/
//---------------------------------------------------------------------------------------------
        startActivity(intent);












    }

    public void exit_app(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    // Reaguoja į "Options" mygtuko paspaudimą, metodo pavadinimas sutampa su mygtuko argumentu "onClick"
    // Atidaro naują langą (naują "Activity")
    public void options(View v)
    {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }


    public void help(View v) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void credits(View v)
    {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

    //Reaguoja į graso icono mygtuko paspaudimą ir paleidžia foninę muziką
    public void backgroundMusic(View v)
    {
        if(isPlaying){
            isPlaying = false;
            backgroundSong.pause();
        }
        else {
            isPlaying = true;
            backgroundSong.start();
            backgroundSong.setLooping(true);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        backgroundSong.pause();
    }


}