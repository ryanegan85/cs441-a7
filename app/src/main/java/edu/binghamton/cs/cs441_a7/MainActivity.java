package edu.binghamton.cs.cs441_a7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private Button mPlayButton;
    private Button mHowToPlayButton;
    private Button mHiScoreButton;
    private ImageView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayButton = findViewById(R.id.button);
        mHowToPlayButton = findViewById(R.id.button2);
        mHiScoreButton = findViewById(R.id.button3);
        mTitle = findViewById(R.id.imageView);

        //String gifURL = "https://images.cooltext.com/5312412.gif";
        Glide.with(this).load(R.drawable.game_title).into(mTitle);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });
        mHowToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstructionsActivity();
            }
        });
        mHiScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHiScoresActivity();
            }
        });
    }

    public void openGameActivity() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    public void openInstructionsActivity() {
        Intent i = new Intent(this, InstructionsActivity.class);
        startActivity(i);
    }

    public void openHiScoresActivity() {
        Intent i = new Intent(this, HiScoresActivity.class);
        startActivity(i);
    }
}
