package com.example.englishapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main_Home extends AppCompatActivity {
FirebaseAuth mAuth;
ImageView test;
Button reading_btn, listening_btn, grammar_btn;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_home);

    mAuth = FirebaseAuth.getInstance();
    test = findViewById(R.id.test);
    reading_btn = findViewById(R.id.reading_btn);
    listening_btn = findViewById(R.id.Listening_btn);
    grammar_btn = findViewById(R.id.grammar_btn);

    reading_btn.setOnClickListener(view->lessons_engine());

    listening_btn.setOnClickListener(view->lessons_engine());

    grammar_btn.setOnClickListener(view->lessons_engine());


}//end onCreate


@Override
public void onStart() {
    super.onStart();

    FirebaseUser currentUser = mAuth.getCurrentUser();
    if (currentUser == null) {

        startActivity(new Intent(Main_Home.this, Login.class));
        finish();

    }//end if
}//end method

private void lessons_engine() {

    if (reading_btn.isPressed()) {

        startActivity(new Intent(Main_Home.this, reading_section.class));

    }//end if
//
//    if (listening_btn.isPressed()) {
//
//        startActivity(new Intent(Main_Home.class, ));
//        finish();
//
//    }//end if
//
//    if (grammar_btn.isPressed()) {
//
//        startActivity(new Intent(Main_Home.class, ));
//        finish();
//
//
//    }//end if

}//end method

}//end class

