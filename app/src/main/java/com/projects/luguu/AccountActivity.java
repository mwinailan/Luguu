package com.projects.luguu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.projects.luguu.modules.Account;
import com.projects.luguu.modules.MainApp;


public class AccountActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Set application to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_account);

        ImageButton mentorButton =  findViewById(R.id.mentorButton);
        mentorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mentorMove = new Intent(AccountActivity.this, TutorActivity.class);
                startActivity(mentorMove);
            }
        });

        ImageButton menteeButton =  findViewById(R.id.menteeButton);
        menteeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menteeMove = new Intent(AccountActivity.this, TuteeActivity.class);
                startActivity(menteeMove);
            }
        });

        ImageButton settingsButton =  findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(AccountActivity.this, "User Logged Out.",
                        Toast.LENGTH_SHORT).show();
                Intent tuteeMove = new Intent(AccountActivity.this, TuteeActivity.class);
                startActivity(tuteeMove);
            }
        });

    }
}