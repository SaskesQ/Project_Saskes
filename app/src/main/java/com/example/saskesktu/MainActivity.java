package com.example.saskesktu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    //Kintamieji reikalingi background muzikai
    MediaPlayer backgroundSong;
    boolean isPlaying = false;
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