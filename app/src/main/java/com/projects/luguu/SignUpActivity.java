package com.projects.luguu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SignUpActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignUpActivity.this, TuteeActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageButton signupButton =  findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO : CREATE ACCOUNT IN DATABASE HERE


                Intent signupMove = new Intent(SignUpActivity.this, SetUpProfileActivity.class);
                startActivity(signupMove);
            }
        });

        ImageButton loginButton =  findViewById(R.id.goToLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginMove = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(loginMove);
            }
        });
    }


}