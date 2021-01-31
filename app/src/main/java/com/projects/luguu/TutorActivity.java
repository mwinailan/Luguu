package com.projects.luguu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TutorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        ImageButton menteeButton =  findViewById(R.id.menteeButton);
        menteeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menteeMove = new Intent(TutorActivity.this, TuteeActivity.class);
                startActivity(menteeMove);
            }
        });

        // Checks if user is logged in to firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        ImageButton accountButton = findViewById(R.id.accountButton);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null) {
                    Intent accountMove = new Intent(TutorActivity.this, AccountActivity.class);
                    startActivity(accountMove);
                }
                else {
                    Intent signupMove = new Intent(TutorActivity.this, SignUpActivity.class);
                    startActivity(signupMove);
                }
            }
        });
    }
}