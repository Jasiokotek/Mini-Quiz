package com.example.miniquiz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView questionText, scoreText;
    Button answerA, answerB, answerC, startButton, resetButton;

    ArrayList<Question> questions = new ArrayList<>();
    int currentQuestionIndex = 0;
    int score = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        answerA = findViewById(R.id.buttonA);
        answerB = findViewById(R.id.buttonB);
        answerC = findViewById(R.id.buttonC);
        startButton = findViewById(R.id.buttonStart);
        resetButton = findViewById(R.id.buttonReset);

        loadQuestions();
        hideQuiz();

        startButton.setOnClickListener(v -> startQuiz());
        resetButton.setOnClickListener(v -> resetQuiz());

        answerA.setOnClickListener(v -> checkAnswer(answerA.getText().toString()));
        answerB.setOnClickListener(v -> checkAnswer(answerB.getText().toString()));
        answerC.setOnClickListener(v -> checkAnswer(answerC.getText().toString()));
    }

    private void loadQuestions() {
        questions.add(new Question("Stolica Włoch to:", "Rzym", "Paryż", "Madryt", "Rzym"));
        questions.add(new Question("2 + 2 =", "3", "4", "5", "4"));
        questions.add(new Question("Kolor nieba:", "Zielony", "Niebieski", "Czerwony", "Niebieski"));
        questions.add(new Question("Stolica Polski:", "Warszawa", "Kraków", "Gdańsk", "Warszawa"));
        questions.add(new Question("5 * 3 =", "15", "10", "20", "15"));
        questions.add(new Question("Stolica Niemiec:", "Berlin", "Monachium", "Hamburg", "Berlin"));
    }

    private void startQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        Collections.shuffle(questions);
        scoreText.setText("Wynik: " + score + " / 5 ");
        showQuiz();
        showQuestion();
    }

    private void showQuestion() {
        if (currentQuestionIndex < 5) {
            Question q = questions.get(currentQuestionIndex);

            questionText.setText(q.getQuestionText());
            answerA.setText(q.getAnswerA());
            answerB.setText(q.getAnswerB());
            answerC.setText(q.getAnswerC());
        } else {
            Toast.makeText(this,
                    "Koniec quizu! Twój wynik: " + score + " / 5",
                    Toast.LENGTH_LONG).show();
            hideQuiz();
        }
    }

    private void checkAnswer(String selectedAnswer) {
        Question q = questions.get(currentQuestionIndex);

        if (selectedAnswer.equals(q.getCorrectAnswer())) {
            score++;
        }

        scoreText.setText("Wynik: " + score + " / 5 ");
        currentQuestionIndex++;
        showQuestion();
    }

    private void resetQuiz() {
        score = 0;
        scoreText.setText("Wynik");
        hideQuiz();
    }

    private void hideQuiz() {
        questionText.setVisibility(View.GONE);
        answerA.setVisibility(View.GONE);
        answerB.setVisibility(View.GONE);
        answerC.setVisibility(View.GONE);
    }

    private void showQuiz() {
        questionText.setVisibility(View.VISIBLE);
        answerA.setVisibility(View.VISIBLE);
        answerB.setVisibility(View.VISIBLE);
        answerC.setVisibility(View.VISIBLE);
    }
}