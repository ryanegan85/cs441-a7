package edu.binghamton.cs.cs441_a7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class HiScoresActivity extends AppCompatActivity {
    private Button mBackButton;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private HiScoreEntry hiScores;

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
        hiScores.putNewHiScore("Ryan", "23000");
        hiScores.putNewHiScore("Alex", "2");

        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this, hiScores);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void openMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
