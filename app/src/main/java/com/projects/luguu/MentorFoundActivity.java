package com.projects.luguu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.projects.luguu.R;
import com.projects.luguu.TuteeActivity;
import com.projects.luguu.TutorActivity;

public class MentorFoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set application to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_mentor_found);

        ImageButton cancelMentorButton =  findViewById(R.id.cancelMentorButton);
        cancelMentorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelMentor = new Intent(MentorFoundActivity.this, AddMentorActivity.class);
                startActivity(cancelMentor);
                finish();
            }
        });

    }
}