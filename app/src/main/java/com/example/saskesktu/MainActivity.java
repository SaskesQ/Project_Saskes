package com.example.saskesktu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Kintamieji reikalingi background muzikai
    MediaPlayer backgroundSong;
    //Kintamieji kalbos keitimui
    Resources resources;
    Context context;
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

        //Kalbos pakeitimas
        ImageButton btnEN = findViewById(R.id.btnEN);
        ImageButton btnLT = findViewById(R.id.btnLT);
        btnEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLanguage("en");
                recreate();
            }
        });
        btnLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLanguage("lt");
                recreate();
            }
        });
    }
    //metodas pakeisti kalbai
    private void updateLanguage(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    // Reaguoja į "Play" mygtuko paspaudimą, metodo pavadinimas sutampa su mygtuko argumentu "onClick"
    // Atidaro naują langą (naują "Activity")
    public void play(View v)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
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