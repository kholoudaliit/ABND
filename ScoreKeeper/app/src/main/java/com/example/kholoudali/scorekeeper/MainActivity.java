package com.example.kholoudali.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // ----------- Varaibles ------------
    TextView team1Score ;
    TextView team2Score ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        team1Score = this.findViewById(R.id.team1_score);
        team2Score = this.findViewById(R.id.team2_score);
    }

    // This method for handling the reset button action
    public void reset(View view) {
        team1Score.setText("0");
        team2Score.setText("0");
    }

    //  method for updating the team#1 score based on user choice
    public void update_team1_score(View view) {

       //get the current score
        int team1Scorenumber= Integer.parseInt(team1Score.getText().toString());

        // update the score based on  user choice
        switch (view.getId())
        {
            case R.id.team1_addone:
                team1Score.setText(String.valueOf(++team1Scorenumber));
                break;
            case R.id.team1_addtow:
                team1Score.setText(String.valueOf(team1Scorenumber+2));
                break;
            case R.id.team1_addthree:
                team1Score.setText(String.valueOf(team1Scorenumber+3));
                break;
        }

    }
    // this method for updating the team#2 score
    public void update_team2_score(View view) {
        int team2Scorenumber= Integer.parseInt(team2Score.getText().toString());
        switch (view.getId())
        {
            case R.id.team2_addone:
                team2Score.setText(String.valueOf(++team2Scorenumber));
                break;
            case R.id.team2_addtow:
                team2Score.setText(String.valueOf(team2Scorenumber+2));
                break;
            case R.id.team2_addthree:
                team2Score.setText(String.valueOf(team2Scorenumber+3));
                break;
        }    }
}
