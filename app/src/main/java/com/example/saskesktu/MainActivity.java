package com.example.saskesktu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    public void help(View v)
    {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }


}