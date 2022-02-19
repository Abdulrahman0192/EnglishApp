package com.example.englishapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class reading_section extends AppCompatActivity {

Button reading_level1;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reading_section);

    reading_level1 = findViewById(R.id.reading_level1);

    reading_level1.setOnClickListener(view->{

        startActivity(new Intent(reading_section.this, com.example.englishapp.reading_level1.class));

    });

}//end onCreate
}//end class