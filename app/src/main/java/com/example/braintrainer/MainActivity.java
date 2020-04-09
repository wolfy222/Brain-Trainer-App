package com.example.braintrainer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button goBuuton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    private Object Random;
    private java.util.Random rand;
    int locationCorrectAnswer;
    TextView resultTextView;
    int score;
    int numberOfQuestions;
    TextView scoreTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    boolean gameActive= true;
    public void start(View view)
    {
        goBuuton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));

    }

    public void chooseAnswer(View view) {
        if(gameActive) {
            if (Integer.toString(locationCorrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Correct!");
                resultTextView.setTextColor(getResources().getColor(R.color.greenn));

                score++;
            } else {

                resultTextView.setTextColor(getResources().getColor(R.color.red));
                resultTextView.setText(" Wrong :( ");
            }
            numberOfQuestions++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            newQuestion();
        }
    }

    public void newQuestion() {
        rand = new Random();
        if(gameActive) {
            int a = rand.nextInt(51);
            int b = rand.nextInt(51);

            sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

            locationCorrectAnswer = rand.nextInt(4);
            int correctAnswer = a + b;
            int upper = (correctAnswer + 20);
            int lower = (correctAnswer - 20);

            answers.clear();

            for (int i = 0; i < 4; i++) {
                if (i == locationCorrectAnswer) {
                    answers.add(correctAnswer);
                } else {
                    while (lower < 1) {
                        lower++;
                    }
                    int wrongAnswer = rand.nextInt(upper - lower) + lower;
                    while (wrongAnswer == a + b) {
                        wrongAnswer = rand.nextInt(upper - lower) + lower;
                    }
                    answers.add(wrongAnswer);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void playAgain(View view) {
        timerTextView.setBackgroundColor(getResources().getColor(R.color.green));
        gameActive=true;
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                if (millisUntilFinished <= 10000) {
                    timerTextView.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
            @Override
            public void onFinish() {
                timerTextView.setText(String.valueOf(0) + "s");
                resultTextView.setText("Time Up!");

                resultTextView.setTextColor(getResources().getColor(R.color.done));

                playAgainButton.setVisibility(View.VISIBLE);
                gameActive=false;

            }
        }.start();
        timerTextView.setClipToOutline(false);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        goBuuton = (Button) findViewById(R.id.goButton);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameLayout =  findViewById(R.id.gameLayout);

        goBuuton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
        gameLayout.setClipToOutline(false);

    }

}

