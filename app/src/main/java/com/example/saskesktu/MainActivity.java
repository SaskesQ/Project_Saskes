package com.example.saskesktu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

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
    }

    // Reaguoja į "Play" mygtuko paspaudimą, metodo pavadinimas sutampa su mygtuko argumentu "onClick"
    // Atidaro naują langą (naują "Activity")
    public void play(View v)
    {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
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