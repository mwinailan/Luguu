package com.projects.luguu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projects.luguu.modules.MainApp;


public class TuteeActivity extends AppCompatActivity {

    private MainApp mainApp = MainApp.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee);

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