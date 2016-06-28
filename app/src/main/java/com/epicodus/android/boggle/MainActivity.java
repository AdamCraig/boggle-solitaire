package com.epicodus.android.boggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.randomStringTextView) TextView mRandomStringTextView;
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.userEditText) EditText mUserEditText;

    public char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
    public char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    public ArrayList<Character> randomStringArray = new ArrayList<Character>();
    public ArrayList<String> userWords = new ArrayList<String>();
    public String randomString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Random randomGenerator = new Random();

        for (int index = 0; index < 2; index++) {
            int randomInt = randomGenerator.nextInt(4);
            randomStringArray.add(vowels[randomInt]);
        }

        for (int index = 0; index < 6; index++) {
            int randomInt = randomGenerator.nextInt(25);
            randomStringArray.add(letters[randomInt]);
        }

        Collections.shuffle(randomStringArray);

        for (Character character : randomStringArray) {
            randomString += character;
        }

        randomString = randomString.toUpperCase();

        mRandomStringTextView.setText(randomString);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userWord = mUserEditText.getText().toString().toUpperCase();
                int letterCounter = 0;

                if (userWord.length() >= 3 && userWord.length() <= 8) {
                    for (int userIndex = 0; userIndex < userWord.length(); userIndex++) {
                        for (int boggleIndex = 0; boggleIndex < randomString.length(); boggleIndex++) {
                            if (userWord.charAt(userIndex) == (randomString.charAt(boggleIndex))) {
                                letterCounter++;
                                break;
                            }
                        }
                    }
                    if (letterCounter >= 3) {
                        for (String previousWord : userWords) {
                            if (userWord.equals(previousWord)) {
                                Toast.makeText(MainActivity.this, "Word Already Submitted", Toast.LENGTH_LONG).show();
                            }
                        }
                        userWords.add(userWord);
                        Toast.makeText(MainActivity.this, "User words: "+userWords.toString(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Unacceptable!", Toast.LENGTH_LONG).show();
                    }

                } else {
                   Toast.makeText(MainActivity.this, "Invalid entry length", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
