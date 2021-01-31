package com.projects.luguu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projects.luguu.modules.MainApp;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button mentorButton =  findViewById(R.id.mentorButton);
        mentorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mentorMove = new Intent(AccountActivity.this, TutorActivity.class);
                startActivity(mentorMove);
            }
        });

        Button menteeButton =  findViewById(R.id.menteeButton);
        menteeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menteeMove = new Intent(AccountActivity.this, TuteeActivity.class);
                startActivity(menteeMove);
            }
        });

    }
}