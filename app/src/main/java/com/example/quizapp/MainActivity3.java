package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    ImageView img;
    TextView scoreTextView,passFailTextView;
    Button restrt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        img=findViewById(R.id.imageView2);

         scoreTextView = findViewById(R.id.textView4);
         passFailTextView = findViewById(R.id.textView5);
        restrt=findViewById(R.id.button6);
        restrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        // Retrieve the score and total questions from the intent
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = 10;


        scoreTextView.setText("Score: " + score+"/10");

        if (score > totalQuestions*0.60) {
            passFailTextView.setText("Congratulation you have passed in Test!!");
            img.setImageResource(R.drawable.trophy);

        } else {
            passFailTextView.setText("Sorry you have Failed in test.");
            img.setImageResource(R.drawable.sad);

        }
    }
    }
