package com.example.saskesktu;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PlayerScoreManager{

   // private SharedPreferences prefs;

    public PlayerScoreManager() {
    }

    public void addScore(String name, SharedPreferences prefs) {

        if(!addToExistingScore(prefs, name) && name!=null)
        {
            PlayerScore score =new PlayerScore(name, 1);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(score.getName(), String.valueOf(score.getScore()));
            editor.apply();
        }

    }

    public void clearAllScores(SharedPreferences pref){

        pref.edit().clear().commit();
    }

    public List<PlayerScore> getScores(SharedPreferences prefs) {
        Map<String, ?> allData = prefs.getAll();
        List<PlayerScore> scores = new ArrayList<>();
        for (Map.Entry<String, ?> entry : allData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                String name = key;
                int score = Integer.parseInt((String) value);
                PlayerScore playerScore = new PlayerScore(name, score);
                scores.add(playerScore);
            }
        }
        Collections.sort(scores);
        return scores;
    }
    public boolean addToExistingScore(SharedPreferences prefs, String pName){

        if(prefs.getString(pName,null) != null && pName!= null)
        {
            int newScore = Integer.parseInt(prefs.getString(pName, null)) + 1;
            prefs.edit().putString(pName, String.valueOf(newScore)).apply();
            return true;
        }else{
            return false;
        }
    }
}
