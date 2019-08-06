package edu.binghamton.cs.cs441_a7;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {
    private Button mBackButton;
    private ImageButton mEnemyButton;
    private ProgressBar mHealthBar;
    private TextView mHealthText;
    private TextView mFloorText;
    private int mMaxHealth;
    private int mCurrentHealth;
    private int mCurrentFloor;
    private CountDownTimer mTimer;
    private long mTimeInMilliseconds = 30000;
    private boolean mTimeRunning = false;
    private TextView mTimerText;
    private ImageButton mRockButton;
    private ImageButton mPaperButton;
    private ImageButton mScissorsButton;
    private enum DamageType {
        ROCK, PAPER, SCISSORS;
    }
    private DamageType mCurrentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        /*
        mBackButton = findViewById(R.id.button6);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        */

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

        mTimerText = findViewById(R.id.timerText);

        mRockButton = findViewById(R.id.rockButton);
        mRockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDamageType(DamageType.ROCK);
            }
        });
        mPaperButton = findViewById(R.id.paperButton);
        mPaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDamageType(DamageType.PAPER);
            }
        });
        mScissorsButton = findViewById(R.id.scissorsButton);
        mScissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDamageType(DamageType.SCISSORS);
            }
        });

        mCurrentType = DamageType.ROCK;
        startStop();
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

    public void startStop() {
        if(mTimeRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        mTimer = new CountDownTimer(mTimeInMilliseconds, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                mTimerText.setText("GAME OVER!");
            }
        }.start();

        mTimeRunning = true;
    }

    public void stopTimer() {
        mTimer.cancel();
        mTimeRunning = false;
    }

    public void updateTimer() {
        int seconds = (int) mTimeInMilliseconds / 1000;
        int hundredthSeconds = (int) mTimeInMilliseconds % 1000 / 10;

        String timeLeft = seconds + ".";
        if(hundredthSeconds < 10) {
            timeLeft += "0" + hundredthSeconds;
        } else {
            timeLeft += hundredthSeconds;
        }
        mTimerText.setText(timeLeft);
    }

    public void changeDamageType(DamageType dmg) {
        if(dmg == DamageType.ROCK) {
            mCurrentType = DamageType.ROCK;
            mRockButton.setBackgroundColor(getResources().getColor(R.color.buttonGreen));
            mPaperButton.setBackgroundColor(getResources().getColor(R.color.buttonRed));
            mScissorsButton.setBackgroundColor(getResources().getColor(R.color.buttonRed));
        }
        if(dmg == DamageType.PAPER) {
            mCurrentType = DamageType.PAPER;
            mRockButton.setBackgroundColor(getResources().getColor(R.color.buttonRed));
            mPaperButton.setBackgroundColor(getResources().getColor(R.color.buttonGreen));
            mScissorsButton.setBackgroundColor(getResources().getColor(R.color.buttonRed));
        }
        if(dmg == DamageType.SCISSORS) {
            mCurrentType = DamageType.SCISSORS;
            mRockButton.setBackgroundColor(getResources().getColor(R.color.buttonRed));
            mPaperButton.setBackgroundColor(getResources().getColor(R.color.buttonRed));
            mScissorsButton.setBackgroundColor(getResources().getColor(R.color.buttonGreen));
        }
    }
}
