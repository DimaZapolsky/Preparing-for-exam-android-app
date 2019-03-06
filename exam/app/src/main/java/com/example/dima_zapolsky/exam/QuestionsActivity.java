package com.example.dima_zapolsky.exam;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    private RecyclerView answerView;
    private Button btnSubmit;
    private TextView questionView;
    private Theme theme;
    private ArrayList<Question> questions;
    private ArrayList<Integer> questionsNum;
    private AnswerAdapter adapter;
    private TextView questionsLeftView;
    private TextView rightAnswersCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        answerView = findViewById(R.id.question_answers);
        btnSubmit = findViewById(R.id.question_submit_button);
        questionView = findViewById(R.id.question_text_view);
        questionsLeftView = findViewById(R.id.questions_left_view);
        rightAnswersCountView = findViewById(R.id.right_answers_count_view);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos", 0);
        theme = GeneralManager.getInstance(null).getThemeByNumber(pos);
        questions = theme.getQuestions();
        questionsNum = new ArrayList<Integer>();
        for (int i = 0; i < questions.size(); i++) {
            questionsNum.add(i);
        }
        btnSubmit.setText("Начать");
        questionView.setText("Нажмите, чтобы начать.");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked();
            }
        });
        answerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnswerAdapter(new ArrayList<String>(), new ArrayList<Integer>());
     }

    private void clicked() {
        if (btnSubmit.getText().equals("Готово")) {
            boolean flag = adapter.check();
            answerView.scrollToPosition(0);
            if (!flag) {
                questionsNum.add(questionsNum.get(0));
                Toast.makeText(getApplicationContext(), "Неверно", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Верно", Toast.LENGTH_SHORT).show();
            }
            btnSubmit.setText("Далее");
            if (flag) {
                clicked();
            }
        }
        else {
            if (!btnSubmit.getText().equals("Начать")) {
                questionsNum.remove(0);
            }
            if (questionsNum.size() == 0) {
                this.finish();
            }
            else {
                questionsLeftView.setText("Осталось вопросов:"
                        .concat(String.valueOf(questionsNum.size())));
                rightAnswersCountView.setText("Правильных ответов:"
                        .concat(String.valueOf(questions.get(questionsNum.get(0))
                                .getRightAnswersSize())));
                questionView.setText(String.valueOf(questionsNum.get(0) + 1).concat(". ").concat(questions.get(questionsNum.get(0)).getName()));
                btnSubmit.setText("Готово");
                adapter = new AnswerAdapter(questions.get(questionsNum.get(0))
                        .getAnswers(), questions.get(questionsNum.get(0)).getRightAnswers());
                answerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
