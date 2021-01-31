package com.projects.luguu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText ePassword, eEmail, eConfirm;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignUpActivity.this, TuteeActivity.class));
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(SignUpActivity.this, AccountActivity.class));
            finish();
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

        setContentView(R.layout.activity_sign_up);

        ImageButton loginButton = findViewById(R.id.goToLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginMove = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(loginMove);
            }
        });

        // Obtain User Details
        ePassword = findViewById(R.id.ePassword);
        eEmail = findViewById(R.id.eEmail);
        eConfirm = findViewById(R.id.eConfirm);

        ImageButton signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = eEmail.getText().toString();
                String password = ePassword.getText().toString();
                String confirm = eConfirm.getText().toString();

                if (email.isEmpty()) {
                    eEmail.setError("Please enter your email");
                    eEmail.requestFocus();
                }
                if (password.isEmpty()) {
                    ePassword.setError("Please enter a password");
                    ePassword.requestFocus();
                }
                if (confirm.isEmpty()) {
                    eConfirm.setError("Please re-enter password");
                    eConfirm.requestFocus();
                }
                if (!confirm.equals(password)) {
                    ePassword.setError("Passwords do not match");
                    ePassword.requestFocus();
                    eConfirm.setError("Passwords do not match");
                    eConfirm.requestFocus();
                }


                if (!(password.isEmpty() && email.isEmpty()) && confirm.equals(password)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(SignUpActivity.this, "Sign Up Successful.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent signupMove = new Intent(SignUpActivity.this, SetUpProfileActivity.class);
                                        startActivity(signupMove);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}


