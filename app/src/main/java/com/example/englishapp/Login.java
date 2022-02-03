package com.example.englishapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {
Button Login;
TextView Register;
String pass_holder, email_holder;
private FirebaseAuth auth;
private EditText password, email;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    Login = findViewById(R.id.Login);
    Register = findViewById(R.id.Register);
    password = findViewById(R.id.password);
    email = findViewById(R.id.email);

    auth = FirebaseAuth.getInstance();

    Login.setOnClickListener(view->{

        user_login();

    });
    Register.setOnClickListener(view->{

        startActivity(new Intent(Login.this,Register_class.class));
        finish();

    });


}//end onCreate

private void user_login() {

    pass_holder = password.getText().toString().trim();
    email_holder = email.getText().toString().trim();


    if (TextUtils.isEmpty(email_holder)) {

        Toasty.error(this, "Email cannot be empty", Toasty.LENGTH_SHORT).show();
        email.requestFocus();

    }//end if

    else if (TextUtils.isEmpty(pass_holder)) {

        Toasty.error(this, "Password cannot be empty", Toasty.LENGTH_SHORT).show();
        password.requestFocus();


    }//end else if

    else {

        auth.signInWithEmailAndPassword(email_holder, pass_holder).addOnCompleteListener(task->{

            if (task.isSuccessful()) {

                Toasty.success(this, "You have singed successfully", Toasty.LENGTH_SHORT).show();

                Intent intent = new Intent(Login.this, Main_Home.class);

                startActivity(intent);
                finish();

            }//end if

            else {

                Toasty.error(this, "Error:" + task.getException().getMessage(), Toasty.LENGTH_SHORT).show();

            }//end else

        });

    }//end else


}//end method


@Override
public void onStart() {
    super.onStart();

    FirebaseUser currentUser = auth.getCurrentUser();
    if (currentUser != null) {

        startActivity(new Intent(Login.this, Main_Home.class));
        finish();
    }//end if
}//end method


}//end class