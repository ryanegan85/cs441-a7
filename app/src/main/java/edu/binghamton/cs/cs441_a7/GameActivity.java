package edu.binghamton.cs.cs441_a7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private Button mBackButton;
    private ImageButton mEnemyButton;
    private ProgressBar mHealthBar;
    private TextView mHealthText;
    private TextView mFloorText;
    private int mMaxHealth;
    private int mCurrentHealth;
    private int mCurrentFloor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mBackButton = findViewById(R.id.button6);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        mEnemyButton = findViewById(R.id.enemyImageButton);
        mEnemyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHealthBar();
            }
        });

        mMaxHealth = 10;
        mCurrentHealth = 10;
        mHealthBar = findViewById(R.id.healthBar);
        mHealthBar.setMax(mCurrentHealth);
        mHealthBar.setProgress(mCurrentHealth);

        mHealthText = findViewById(R.id.healthTextView);
        setHealthText();

        mFloorText = findViewById(R.id.floorTextView);
        mCurrentFloor = 1;
    }

    public void openMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void updateHealthBar() {
        if(mCurrentHealth > 0) {
            mCurrentHealth--;
            mHealthBar.setProgress(mCurrentHealth);
            if(mCurrentHealth == 0) {
                mCurrentHealth = mMaxHealth;
                mHealthBar.setProgress(mCurrentHealth);
                advanceFloor();
            }
        } else {

        }

        setHealthText();
    }

    public void setHealthText() {
        mHealthText.setText(mCurrentHealth + " / " + mMaxHealth);
    }

    public void advanceFloor() {
        mCurrentFloor++;
        mFloorText.setText("Current Floor: " + mCurrentFloor);
    }

}
