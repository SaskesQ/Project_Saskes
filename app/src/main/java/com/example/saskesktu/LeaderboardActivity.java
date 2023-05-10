package com.example.saskesktu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    private PlayerScoreManager scoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        SharedPreferences prefs = getSharedPreferences("player_scores", MODE_PRIVATE);
        scoreManager = new PlayerScoreManager();

        List<PlayerScore> scores = scoreManager.getScores(prefs);
        if(!scores.isEmpty()){
            TableLayout tableLayout = findViewById(R.id.scores_table);
            for (PlayerScore score : scores) {
                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                TextView nameTextView = new TextView(this);
                nameTextView.setText(score.getName());
                nameTextView.setTextSize(25);
                nameTextView.setPadding(40, 10, 20, 10);
                tableRow.addView(nameTextView);

                TextView scoreTextView = new TextView(this);
                scoreTextView.setText(String.valueOf(score.getScore()));
                scoreTextView.setTextSize(25);
                scoreTextView.setPadding(115, 10, 20, 10);
                tableRow.addView(scoreTextView);

                tableLayout.addView(tableRow);
            }
        }else{
            TextView noDataText = findViewById(R.id.noDataScoreTable);
            noDataText.setText(R.string.noScoreData);
            noDataText.setTextSize(25);
        }

        //Paslepiamas ActionBar'as
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    public void deleteAllScoreTableData(View v){
        SharedPreferences prefs = getSharedPreferences("player_scores", MODE_PRIVATE);
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage(R.string.sureDelete);
        builder.setTitle(R.string.delData);
        builder.setNegativeButton((R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton((R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                scoreManager.clearAllScores(prefs);
                recreate();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void backButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
