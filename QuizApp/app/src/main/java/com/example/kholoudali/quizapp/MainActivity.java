package com.example.kholoudali.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText nameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameET = (EditText) findViewById(R.id.name_Etext);
    }

    public void startQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("userName",  "Hello "+ nameET.getText()+",");
        startActivity(intent);
    }
}
