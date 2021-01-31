package com.projects.luguu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.projects.luguu.modules.Account;
import com.projects.luguu.modules.Activity;
import com.projects.luguu.modules.MainApp;

import java.util.Date;

public class TuteeActivity extends AppCompatActivity {

    private MainApp mainApp = MainApp.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee);

        FirebaseApp.initializeApp(this);
        //Testing
        Account testAccount = new Account("testFromJava", 9808);
        testAccount.addMenteeingActivity(
                new Activity(testAccount,testAccount, "MATH 200", "UBC", new Date(), testAccount.getId())
        );
        mainApp.addAccount(testAccount);

        Button mentorButton =  findViewById(R.id.mentorButton);
        mentorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mentorMove = new Intent(TuteeActivity.this, TutorActivity.class);
                startActivity(mentorMove);
            }
        });

        Button accountButton =  findViewById(R.id.accountButton);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountMove = new Intent(TuteeActivity.this, AccountActivity.class);
                startActivity(accountMove);
            }
        });
    }


}