package edu.binghamton.cs.cs441_a7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstructionsActivity extends AppCompatActivity {
    private Button mBackButton;
    private TextView mInstructionsHeader;
    private TextView mInstructionsBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        mBackButton = findViewById(R.id.button4);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mInstructionsHeader = findViewById(R.id.instructionsText);
        mInstructionsHeader.setText(Html.fromHtml("<h1>Welcome to Janken Tower!</h1>"));

        mInstructionsBody = findViewById(R.id.instructionsText2);
        mInstructionsBody.setText(Html.fromHtml("<p><b>1.</b> Your goal is to climb through "
                                                +"the tower to the highest point you can before "
                                                +"the timer hits 0. You start with 30"
                                                +" seconds. When time runs out, you lose!</p>"
                                                +"<br>"
                                                +"<p><b>2.</b> You gain time by defeating enemies "
                                                +"on your way through the tower. Do this by tapping"
                                                +" on them until their health reaches 0.</p>"
                                                +"<br>"
                                                +"<p><b>3.</b> Each enemy will have one of three "
                                                +"types: Rock, paper, or scissors. Make sure to "
                                                +"select the proper counter on the bottom of your"
                                                +" screen to do maximum damage!</p>"));
    }

    public void openMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
