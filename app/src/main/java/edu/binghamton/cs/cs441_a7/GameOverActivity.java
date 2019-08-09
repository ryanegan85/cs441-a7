package edu.binghamton.cs.cs441_a7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameOverActivity extends AppCompatActivity {
    private TextView mScoreText;
    private EditText mName;
    private Button mSubmitButton;
    private Button mMenuButton;
    private TextView mConfirmationText;
    private static final String FILE_NAME = "highscores.txt";
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        score = getIntent().getStringExtra("SCORE");

        mScoreText = findViewById(R.id.textViewScore);
        mScoreText.setText("You made it to floor " + score + "!");

        mName = findViewById(R.id.nameEditText);
        mConfirmationText = findViewById(R.id.confirmationText);

        mSubmitButton = findViewById(R.id.button6);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                if(name.matches("")) {
                    mConfirmationText.setText("Please enter a name before submitting.");
                } else {
                    mConfirmationText.setText("Score submitted!");
                    saveHiScore(score);
                }
            }
        });

        mMenuButton = findViewById(R.id.button7);
        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenu();
            }
        });

    }

    public void goToMenu() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

    public void saveHiScore(String newScore) {
        SharedPreferences prefs = getSharedPreferences("SCORES", MODE_PRIVATE);
        int numScores = prefs.getInt("numScores", 0);
        String currentScoresString = Integer.toString(numScores+1);

        String name = mName.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences("SCORES", MODE_PRIVATE).edit();
        editor.putString("NAME_" + currentScoresString, name);
        editor.putString("SCORE_" + currentScoresString, newScore);
        editor.putInt("numScores", numScores+1);
        editor.commit();
    }

}
