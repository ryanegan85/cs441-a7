package edu.binghamton.cs.cs441_a7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class HiScoresActivity extends AppCompatActivity {
    private Button mBackButton;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private HiScoreEntry hiScores;
    private Button mEraseScores;
    private TextView mEraseConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscores);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mBackButton = findViewById(R.id.button5);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        hiScores = new HiScoreEntry();
        load();
        hiScores.sortScores();

        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this, hiScores);
        mRecyclerView.setAdapter(mAdapter);

        mEraseConfirmation = findViewById(R.id.textView2);

        mEraseScores = findViewById(R.id.button8);
        mEraseScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiScores = new HiScoreEntry();
                mAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdapter);

                SharedPreferences.Editor editor = getSharedPreferences("SCORES", MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();
                mEraseConfirmation.setText("Scores cleared! Go back to menu to refresh.");
            }
        });
    }

    public void openMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void load() {
        SharedPreferences prefs = getSharedPreferences("SCORES", MODE_PRIVATE);
        int numScores = prefs.getInt("numScores", 0);
        String tempName;
        String tempScore;
        String currentScoreString;
        for(int i=1; i<=numScores; i++) {
            currentScoreString = Integer.toString(i);
            tempName = prefs.getString("NAME_" + currentScoreString, "N/A");
            tempScore = prefs.getString("SCORE_" + currentScoreString, "N/A");
            hiScores.putNewHiScore(tempName, tempScore);
        }
    }
}
