package edu.binghamton.cs.cs441_a7;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private Button mBackButton;
    private ImageButton mEnemyButton;
    private ProgressBar mHealthBar;
    private TextView mHealthText;
    private TextView mFloorText;
    private double mMaxHealth;
    private double mCurrentHealth;
    private int mCurrentFloor;
    private CountDownTimer mTimer;
    private long mTimeInMilliseconds = 10000;
    private boolean mTimeRunning = false;
    private TextView mTimerText;
    private ImageButton mRockButton;
    private ImageButton mPaperButton;
    private ImageButton mScissorsButton;
    private enum DamageType {
        ROCK, PAPER, SCISSORS;
    }
    private DamageType mCurrentType;
    private DamageType mEnemyType;
    private ImageView mEnemyTypeImage;
    Random randomGen;

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
        mHealthBar.setMax((int) mCurrentHealth);
        mHealthBar.setProgress((int) mCurrentHealth);

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

        mEnemyTypeImage = findViewById(R.id.enemyTypeImage);

        mCurrentType = DamageType.ROCK;
        randomGen = new Random();
        rollEnemyType();
        startStop();
    }

    public void openMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void updateHealthBar() {
        if(mEnemyType == DamageType.ROCK) {
            if(mCurrentType == DamageType.ROCK) {
                mCurrentHealth -= 1;
            }
            if(mCurrentType == DamageType.PAPER) {
                mCurrentHealth -= 3;
            }
            if(mCurrentType == DamageType.SCISSORS) {

            }
        }
        if(mEnemyType == DamageType.PAPER) {
            if(mCurrentType == DamageType.ROCK) {

            }
            if(mCurrentType == DamageType.PAPER) {
                mCurrentHealth -= 1;
            }
            if(mCurrentType == DamageType.SCISSORS) {
                mCurrentHealth -= 3;
            }
        }
        if(mEnemyType == DamageType.SCISSORS) {
            if(mCurrentType == DamageType.ROCK) {
                mCurrentHealth -= 3;
            }
            if(mCurrentType == DamageType.PAPER) {

            }
            if(mCurrentType == DamageType.SCISSORS) {
                mCurrentHealth -= 1;
            }
        }

        mHealthBar.setProgress((int) mCurrentHealth);
        if(mCurrentHealth <= 0) {
            increaseHealthBar();
            advanceFloor();
            rollEnemyType();

            mTimeInMilliseconds += 2000;
            stopTimer();
            startTimer();
        }


        setHealthText();
    }

    public void setHealthText() {
        mHealthText.setText((int) mCurrentHealth + " / " + (int) mMaxHealth);
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
                goToGameOverActivity();
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

    public void rollEnemyType () {
        int i = randomGen.nextInt(3);
        if(i==0) {
            mEnemyType = DamageType.ROCK;
            mEnemyTypeImage.setBackgroundResource(R.drawable.rock);
            mEnemyButton.setImageResource(R.drawable.enemy_rockmonster);
        }
        if(i==1) {
            mEnemyType = DamageType.PAPER;
            mEnemyTypeImage.setBackgroundResource(R.drawable.paper);
            mEnemyButton.setImageResource(R.drawable.enemy_elephant);
        }
        if(i==2) {
            mEnemyType = DamageType.SCISSORS;
            mEnemyTypeImage.setBackgroundResource(R.drawable.scissors);
            mEnemyButton.setImageResource(R.drawable.enemy_gorilla);
        }
    }

    public void increaseHealthBar() {
        mMaxHealth = Math.pow(mMaxHealth, 1.05);
        mCurrentHealth = mMaxHealth;
        mHealthBar.setMax((int) mCurrentHealth);
        mHealthBar.setProgress((int) mCurrentHealth);
    }

    public void goToGameOverActivity() {
        Intent intent = new Intent(getBaseContext(), GameOverActivity.class);
        intent.putExtra("SCORE", Integer.toString(mCurrentFloor));
        startActivity(intent);
    }
}
