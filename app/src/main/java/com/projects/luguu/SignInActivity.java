package com.projects.luguu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignInActivity.this, TuteeActivity.class));
        finish();
    }

    // Checks if user is logged in to firebase
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ImageButton accountButton = findViewById(R.id.goToSignUpButton);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent signupMove = new Intent(SignInActivity.this, SignUpActivity.class);
                    startActivity(signupMove);
            }
        });


        ImageButton signInButton = findViewById(R.id.signinButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: DO LOGIN STUFF HERE


            }
        });
    }
}