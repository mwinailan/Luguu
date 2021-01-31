package com.projects.luguu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TutorDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set application to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_tutor_details);

        ImageButton menteeButton =  findViewById(R.id.menteeButton);
        menteeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menteeMove = new Intent(TutorDetailsActivity.this, TuteeActivity.class);
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
                    Intent accountMove = new Intent(TutorDetailsActivity.this, AccountActivity.class);
                    startActivity(accountMove);
                }
                else {
                    Intent signupMove = new Intent(TutorDetailsActivity.this, SignUpActivity.class);
                    startActivity(signupMove);
                }
            }
        });

    }
}