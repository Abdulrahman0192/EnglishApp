package com.example.englishapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class Register_class extends AppCompatActivity {

FirebaseAuth firebaseAuth;
Button Register;
TextView login;
EditText Password, Email;
String emil_holder, pass_holder;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    firebaseAuth = FirebaseAuth.getInstance();
    Register = findViewById(R.id.Register);
    Email = findViewById(R.id.email);
    Password = findViewById(R.id.password);
    login = findViewById(R.id.Login);

    login.setOnClickListener(view->{

        startActivity(new Intent(Register_class.this, Login.class));
        finish();

    });


    Register.setOnClickListener(view->{

        CreateUser();

    });

}//end onCreate

private void CreateUser() {

    emil_holder = Email.getText().toString().trim();
    pass_holder = Password.getText().toString().trim();

    if (TextUtils.isEmpty(emil_holder)) {

        Toasty.error(this, "Email cannot be empty", Toasty.LENGTH_SHORT).show();
        Email.requestFocus();

    }//end if


    else if (TextUtils.isEmpty(pass_holder)) {

        Toasty.error(this, "Password cannot be empty", Toasty.LENGTH_SHORT).show();
        Password.requestFocus();
    }//end else if

    else {

        //end onComplete
        firebaseAuth.createUserWithEmailAndPassword(emil_holder, pass_holder).addOnCompleteListener(task->{

            if (task.isSuccessful()) {


                Toasty.success(this, "User registered successfully", Toasty.LENGTH_SHORT).show();
                startActivity(new Intent(Register_class.this, Login.class));
                finish();

            }//end if

            else {

                Toasty.error(this, "Registration Error:" + task.getException().getMessage(), Toasty.LENGTH_SHORT).show();

            }//end else

        });

    }//end else


}//end method
}//end class