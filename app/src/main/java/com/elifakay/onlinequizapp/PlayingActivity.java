package com.elifakay.onlinequizapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elifakay.onlinequizapp.Common.Common;
import com.squareup.picasso.Picasso;


public class PlayingActivity extends AppCompatActivity implements View.OnClickListener {

    final static long INTERVAL=1000; //1 sec
    final static long TIMEOUT=7000; //7 sec
    int progressValue=0;

    CountDownTimer countDownTimer;

    int index=0,score=0,thisQuestion=0,totalQuestion,correctAnswer;

    //Firebase
    /*FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseRefQuestion;
    */
    ProgressBar progressBar;
    ImageView imgQuestion;
    Button btnA,btnB,btnC,btnD;
    TextView txtScore,txtQuestionNum,txtQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        //Firebase
        /*firebaseDatabase=FirebaseDatabase.getInstance();
        databaseRefQuestion=firebaseDatabase.getReference("Questions");
        */
        txtScore=(TextView)findViewById(R.id.txtScore);
        txtQuestionNum=(TextView)findViewById(R.id.txtTotalQuestion);
        txtQuestion=(TextView)findViewById(R.id.txtQuestion);
        imgQuestion=(ImageView)findViewById(R.id.imgQuestion);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        btnA=(Button)findViewById(R.id.btnAnswerA);
        btnB=(Button)findViewById(R.id.btnAnswerB);
        btnC=(Button)findViewById(R.id.btnAnswerC);
        btnD=(Button)findViewById(R.id.btnAnswerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        countDownTimer.cancel();
        if(index<totalQuestion)
        {
            Button btnClicked=(Button)view;
            if(btnClicked.getText().equals(Common.questionList.get(index).getCorrectAnswer()))
            {
                //Choose correct answer
                score+=10;
                correctAnswer++;
                showQuestion(++index);
            }else
            {
                //Choose wrong answer
                Intent intent=new Intent(this,DoneActivity.class);
                Bundle dataSend=new Bundle();
                dataSend.putInt("SCORE",score);
                dataSend.putInt("TOTAL",totalQuestion);
                dataSend.putInt("CORRECT",correctAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
            txtScore.setText(String.format("%d",score));
        }
    }

    private void showQuestion(int index) {

        if(index<totalQuestion)
        {
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d",thisQuestion,totalQuestion));
            progressBar.setProgress(0);
            progressValue=0;

            if(Common.questionList.get(index).getIsImageQuestion().equals("true"))
            {
                Picasso.with(getBaseContext())
                        .load(Common.questionList.get(index).getQuestion())
                        .into(imgQuestion);
                imgQuestion.setVisibility(View.VISIBLE);
                txtQuestion.setVisibility(View.INVISIBLE);
            }
            else
            {
                txtQuestion.setText(Common.questionList.get(index).getQuestion());

                imgQuestion.setVisibility(View.INVISIBLE);
                txtQuestion.setVisibility(View.VISIBLE);
            }
            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());

            countDownTimer.start(); //Start Time
        }
        else
        {
            //If it is final question
            Intent intent=new Intent(this,DoneActivity.class);
            Bundle dataSend=new Bundle();
            dataSend.putInt("SCORE",score);
            dataSend.putInt("TOTAL",totalQuestion);
            dataSend.putInt("CORRECT",correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        totalQuestion=Common.questionList.size();

        countDownTimer=new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }
}
