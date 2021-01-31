package com.projects.luguu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText ePassword, eEmail;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignInActivity.this, TuteeActivity.class));
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(SignInActivity.this, AccountActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        // Set application to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sign_in);

        ImageButton signupButton = findViewById(R.id.goToSignUpButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupMove = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signupMove);
            }
        });


        // Obtain User Details
        ePassword = findViewById(R.id.ePassword);
        eEmail = findViewById(R.id.eEmail);

        ImageButton signInButton = findViewById(R.id.signinButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = eEmail.getText().toString();
                String password = ePassword.getText().toString();

                if (email.isEmpty()) {
                    eEmail.setError("Please enter your email");
                    eEmail.requestFocus();
                }
                if (password.isEmpty()) {
                    ePassword.setError("Please enter a password");
                    ePassword.requestFocus();
                }

                if (!(password.isEmpty() && email.isEmpty())) {
                    //TODO: DO LOGIN STUFF HERE
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(SignInActivity.this, "Sign In Successful.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent signinMove = new Intent(SignInActivity.this, AccountActivity.class);
                                        startActivity(signinMove);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignInActivity.this, "Authentication Failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}