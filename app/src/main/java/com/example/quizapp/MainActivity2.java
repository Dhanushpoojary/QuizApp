package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private TextView tq, qt, timerTextView;
    private Button op1, op2, op3, op4, nxt;
    private int score = 0, totalq = QuizModel.questions.length, qindx = 0;
    private String selectedans = "";
    private CountDownTimer timer;
    private long timeLeftInMillis;
    private static final long COUNTDOWN_INTERVAL = 1000; // 1 second
    private static final long QUIZ_DURATION = 180000; // 60 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tq = findViewById(R.id.textView2);
        qt = findViewById(R.id.textView1);
        op1 = findViewById(R.id.button1);
        op2 = findViewById(R.id.button2);
        op3 = findViewById(R.id.button3);
        op4 = findViewById(R.id.button4);
        nxt = findViewById(R.id.button5);
        timerTextView = findViewById(R.id.timer);

        op1.setOnClickListener(this);
        op2.setOnClickListener(this);
        op3.setOnClickListener(this);
        op4.setOnClickListener(this);
        nxt.setOnClickListener(this);

        tq.setText("Total question: " + totalq);
        loadNewQuestion();

        timeLeftInMillis = QUIZ_DURATION;
        startTimer();
    }

    @Override
    public void onClick(View view) {
        op1.setBackgroundColor(Color.WHITE);
        op2.setBackgroundColor(Color.WHITE);
        op3.setBackgroundColor(Color.WHITE);
        op4.setBackgroundColor(Color.WHITE);

        Button clb = (Button) view;
        if (clb.getId() == R.id.button5) {
            if (selectedans.equals(QuizModel.answer[qindx])) {
                score++;
            }
            qindx++;
            loadNewQuestion();
        } else {
            selectedans = clb.getText().toString();
            clb.setBackgroundColor(Color.GREEN);
        }
    }

    private void loadNewQuestion() {
        if (qindx == totalq) {
            finishQuiz();
        }

        qt.setText(QuizModel.questions[qindx]);
        op1.setText(QuizModel.choices[qindx][0]);
        op2.setText(QuizModel.choices[qindx][1]);
        op3.setText(QuizModel.choices[qindx][2]);
        op4.setText(QuizModel.choices[qindx][3]);
    }

    private void finishQuiz() {
        timer.cancel();

        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
        finish();
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeftInMillis, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                finishQuiz();
            }
        };

        timer.start();
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }

    void restartQuiz() {
        score = 0;
        qindx = 0;
        loadNewQuestion();
    }
}



