package com.epicodus.android.boggle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScoreActivity extends AppCompatActivity {

    @Bind(R.id.scoreListView) ListView mScoreListView;
    @Bind(R.id.scoreTitleTextView) TextView mScoreTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        ArrayList<String> userWords = intent.getStringArrayListExtra("userWords");
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userWords);
        mScoreListView.setAdapter(adapter);
        String userScore = Integer.toString(userWords.size());
        mScoreTitleTextView.setText("Your Score: " + userScore);

    }
}
