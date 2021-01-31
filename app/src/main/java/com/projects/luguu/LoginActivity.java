package com.projects.luguu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.projects.luguu.modules.Account;
import com.projects.luguu.modules.AccountsContainer;
import com.projects.luguu.modules.FirebaseHandler;
import com.projects.luguu.modules.MainApp;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText ePassword, eEmail;
    private ImageButton go;
    private FirebaseHandler firebaseHandler = new FirebaseHandler();
    private MainApp mainApp = MainApp.getInstance();

    //@Override
    /*public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, TuteeActivity.class));
        finish();
    }*/

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, AccountActivity.class));
            finish();
        }
    }*/

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mainApp.update();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            startActivity(new Intent(LoginActivity.this, AccountActivity.class));
//            finish();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseHandler.getAllAccount();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        ePassword = findViewById(R.id.editPassword);
        eEmail = findViewById(R.id.editEmail);
        go = findViewById(R.id.signinButton);

        ImageButton loginButton = findViewById(R.id.goToSignUpButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginMove = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(loginMove);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
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
                if (!(password.isEmpty() && email.isEmpty())){
                    boolean found = false;
                    for(Account acc: AccountsContainer.getAccounts()){
                        if(acc.getEmail().equals(email) && acc.getPassword().equals(password)){
                            mainApp.setActiveAccount(acc);
                            startActivity(new Intent (LoginActivity.this, TuteeActivity.class));
                            found = true;
                        }
                    }
                    if(!found){
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT ).show();
                    }
//                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (!task.isSuccessful())
//                            {
//                                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT ).show();
//                            }
//                            else {
//                                startActivity(new Intent (LoginActivity.this, TuteeActivity.class));
//                            }
//                        }
//                    });
                }

            }
        });
    }
}