package com.elifakay.onlinequizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elifakay.onlinequizapp.Common.Common;
import com.elifakay.onlinequizapp.Model.QuestionScore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StreamDownloadTask;

import org.w3c.dom.Text;

public class DoneActivity extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtResultScore,txtDoneTotalQuestion;
    ProgressBar progressBar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseRefQuestionScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        //Firebase
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseRefQuestionScore=firebaseDatabase.getReference("QuestionScore");

        txtResultScore=(TextView)findViewById(R.id.txtTotalScore);
        txtDoneTotalQuestion=(TextView)findViewById(R.id.txtDoneTotalQuestion);
        progressBar=(ProgressBar)findViewById(R.id.doneProgressBar);
        btnTryAgain=(Button)findViewById(R.id.btnTryAgain);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoneActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Get data from bundle and set to view
        Bundle extra=getIntent().getExtras();
        if(extra != null) {
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");

            txtResultScore.setText(String.format("SKOR : %d", score));
            txtDoneTotalQuestion.setText(String.format("GEÇTİ : %d / %d", correctAnswer, totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);

            //Upload point to DB
            databaseRefQuestionScore.child(String.format("%s_%s", Common.currentUser.getUserName(),
                                                                    Common.categoryId))
                    .setValue(new QuestionScore(String.format("%s_%s", Common.currentUser.getUserName(),
                            Common.categoryId),
                            Common.currentUser.getUserName(),
                            String.valueOf(score)));

        }
    }
}
