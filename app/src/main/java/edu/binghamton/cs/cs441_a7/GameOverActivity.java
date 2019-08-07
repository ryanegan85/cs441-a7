package edu.binghamton.cs.cs441_a7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private TextView mScoreText;
    private EditText mName;
    private Button mSubmitButton;
    private Button mMenuButton;
    private TextView mConfirmationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        String score = getIntent().getStringExtra("SCORE");

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

}
