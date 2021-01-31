package com.projects.luguu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AddMentorActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddMentorActivity.this, TuteeActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mentor);

        ImageButton searchMentorButton =  findViewById(R.id.searchMentorButton);
        searchMentorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchMentor = new Intent(AddMentorActivity.this, MentorFoundActivity.class);
                startActivity(searchMentor);
            }
        });
    }
}