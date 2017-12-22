package com.example.kholoudali.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    private int scoreNo;
    private RadioGroup q1_radioGrpoup;
    private RadioButton q1_CorrectRadioButton1;
    private EditText q2_answer;
    private CheckBox q3_checkbox1;
    private CheckBox q3_checkbox2;
    private CheckBox q3_checkbox3;
    private CheckBox q3_checkbox4;
    private RadioGroup q4_radioGroup;
    private RadioButton q4_radiobutton;
    private RadioButton q4_CorrectRadioButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //get user name
        Intent intent = getIntent();
        String userName =  intent.getExtras().getString("userName");
        TextView name = (TextView) findViewById(R.id.userName);
        name.setText(""+userName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //---- Defin the questions components -------
        q1_radioGrpoup = (RadioGroup) this.findViewById(R.id.q1_rg);
        q1_CorrectRadioButton1 = (RadioButton) findViewById(R.id.Q1_radioButton1);
        q2_answer = (EditText) findViewById(R.id.Q2_answer);
        q3_checkbox1 = (CheckBox) findViewById(R.id.Q3_checkbox1);
        q3_checkbox2 = (CheckBox) findViewById(R.id.Q3_checkbox2);
        q3_checkbox3 = (CheckBox) findViewById(R.id.Q3_checkbox3);
        q3_checkbox4 = (CheckBox) findViewById(R.id.Q3_checkbox4);
        q4_radioGroup = (RadioGroup) findViewById(R.id.Q4_radiogroup);
        q4_radiobutton = (RadioButton) findViewById(R.id.Q4_radioButton3);
        q4_CorrectRadioButton1 = (RadioButton) findViewById(R.id.Q4_radioButton3);
        // make the sumbit button
        FloatingActionButton sumbit = (FloatingActionButton) findViewById(R.id.fab);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateScore(view);
            }
        });
    }

    private void calculateScore(View view) {
        //Reset the Score number
        scoreNo = 0;

        //Validate user entry
        if (q1_radioGrpoup.getCheckedRadioButtonId() == -1 || TextUtils.isEmpty(q2_answer.getText()) || q4_radioGroup.getCheckedRadioButtonId() == -1
                || q3_checkbox1.isChecked() == false && q3_checkbox2.isChecked() == false && q3_checkbox3.isChecked() == false &&
                q3_checkbox4.isChecked() == false) {
            //Show score snackbar to the user
            Snackbar snackbar = Snackbar.make(view, R.string.errorMsg, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(Color.WHITE);
            snackbar.show();
            return;
        }


        //--- check the correct answers --
        // Question #1
        int q1_selectedId = q1_radioGrpoup.getCheckedRadioButtonId();
        RadioButton q1_selectedItem = (RadioButton) findViewById(q1_selectedId);
        if (q1_selectedItem.getText().equals("True")) {
            scoreNo = scoreNo + 2;
            q1_selectedItem.setTextColor(Color.parseColor("#5d9732"));
        } else {
            q1_selectedItem.setTextColor(Color.RED);
            q1_CorrectRadioButton1.setTextColor(Color.parseColor("#5d9732"));
        }

        // Question #2
        if (q2_answer.getText().toString().toLowerCase().contains("manifest")) {
            scoreNo = scoreNo + 2;
            q2_answer.setTextColor(Color.parseColor("#5d9732"));
        } else
            q2_answer.setError("Wrong answer");

        //Question #3
        if (q3_checkbox1.isChecked() == true && q3_checkbox2.isChecked() == true && q3_checkbox3.isChecked() == false && q3_checkbox4.isChecked() == false) {
            scoreNo = scoreNo + 2;
            q3_checkbox2.setTextColor(Color.parseColor("#5d9732"));
            q3_checkbox1.setTextColor(Color.parseColor("#5d9732"));
        } else {

            q3_checkbox2.setTextColor(Color.parseColor("#5d9732"));
            q3_checkbox1.setTextColor(Color.parseColor("#5d9732"));
        }

        //Question #4
        int q4_selectedId = q4_radioGroup.getCheckedRadioButtonId();
        RadioButton q4_selectedItem = (RadioButton) findViewById(q4_selectedId);
        if (q4_selectedItem.getText().equals("Main Thread")) {
            scoreNo = scoreNo + 2;
            q4_selectedItem.setTextColor(Color.parseColor("#5d9732"));
        } else {
            q4_selectedItem.setTextColor(Color.RED);
            q4_CorrectRadioButton1.setTextColor(Color.parseColor("#5d9732"));
        }

        //make response msg based on score value
        String scoreRespone = "";
        if (scoreNo == 8)
            scoreRespone = getString(R.string.fullmark_response);
        else if (scoreNo > 2 && scoreNo < 8)
            scoreRespone = getString(R.string.good_response);
        else
            scoreRespone = getString(R.string.bad_response);

        //Show score snackbar to the user
        Snackbar snackbar = Snackbar.make(view, scoreRespone + getString(R.string.snackbarMessage) + scoreNo, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(Color.WHITE);
        snackbar.show();
    }
}
