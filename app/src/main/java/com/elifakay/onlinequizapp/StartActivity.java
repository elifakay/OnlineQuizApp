package com.elifakay.onlinequizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elifakay.onlinequizapp.Common.Common;
import com.elifakay.onlinequizapp.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.Collections;

public class StartActivity extends AppCompatActivity {

    Button btnPlay;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseRefQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseRefQuestions=firebaseDatabase.getReference("Questions");

        loadQuestions(Common.categoryId);

        btnPlay=(Button)findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, PlayingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuestions(String categoryId) {

        if(Common.questionList.size()>0)
            Common.questionList.clear();

        databaseRefQuestions.orderByChild("CategoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {
                            Question ques=postSnapshot.getValue(Question.class);
                            Common.questionList.add(ques);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        //Random List
        Collections.shuffle(Common.questionList);
    }
}
