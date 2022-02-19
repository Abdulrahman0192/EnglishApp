package com.example.englishapp;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class reading_level1 extends AppCompatActivity implements View.OnClickListener {

Button option1, option2, option3, nextBtn;
TextView question, skipBtn;
ImageView exitBtn;
TextView questionCounter;
int questionNum, qCounter = 1;
List<QuestionLibrary> questionLibraries;
int selectedPosition = 0;

@SuppressLint("NewApi")
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reading_level1);

    option1 = findViewById(R.id.option1);
    option2 = findViewById(R.id.option2);
    option3 = findViewById(R.id.option3);
    nextBtn = findViewById(R.id.nextBtn);
    skipBtn = findViewById(R.id.Skip_btn);
    exitBtn = findViewById(R.id.exit_btn);
    question = findViewById(R.id.question);
    questionCounter = findViewById(R.id.question_counter);
    option1.setOnClickListener(this);
    option2.setOnClickListener(this);
    option3.setOnClickListener(this);
    getQuestionsList();
    SkippingOrQuitting();

}//end onCreate

private void getQuestionsList() {

    questionLibraries = new ArrayList<>();

    questionLibraries.add(new QuestionLibrary("A dog is bigger than a mouse, but smaller than an elephant.", "A dog is smaller than a mouse", "A dog is very big", "An elephant is bigger than a dog", 3));
    questionLibraries.add(new QuestionLibrary("He is the oldest man in the world.", "He is not as old as my grandmother", "There are no older men anywhere", "Many men are older", 2));
    questionLibraries.add(new QuestionLibrary("You can't come without a ticket.", "You can come if you have a ticket", "You mustn't go with a ticket", "You don't need a ticket to come", 1)); //original
    questionLibraries.add(new QuestionLibrary("They only have one car for the family.", "They only like cars", "They do not like any other cars", "They do not have two cars", 3));
    questionLibraries.add(new QuestionLibrary("What does Veronica like?", "What are her favourite things?", "How is she?", "Does she look like Veronica?", 1));

    setQuestions();

}//end method


@SuppressLint("SetTextI18n")
private void setQuestions() {

    question.setText(questionLibraries.get(0).getQuestion());
    option1.setText(questionLibraries.get(0).getOption1());
    option2.setText(questionLibraries.get(0).getOption2());
    option3.setText(questionLibraries.get(0).getOption3());

    questionCounter.setText("Question " + qCounter);
    questionNum = 0;
}//end method


@SuppressLint("NonConstantResourceId")
@Override
public void onClick(View view) {


    switch (view.getId()) {

        case R.id.option1:
            selectedPosition = 1;

            break;
        case R.id.option2:
            selectedPosition = 2;

            break;
        case R.id.option3:
            selectedPosition = 3;

            break;

        default:


    }//end switch


    checkAnswer(selectedPosition, view);


}//end method

@SuppressLint("NewApi")
private void checkAnswer(int selectedPosition, View view) {


    if (selectedPosition == questionLibraries.get(questionNum).getAnswer()) {

        ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3A7455")));

    }//end if

    else {

        ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF743A3A")));

        switch (questionLibraries.get(questionNum).getAnswer()) {

            case 1:

                option1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3A7455")));

                break;

            case 2:

                option2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3A7455")));


                break;

            case 3:

                option3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3A7455")));

                break;


        }//end switch

    }//end else

    option1.setEnabled(false);
    option1.setTextColor(Color.WHITE);
    option2.setEnabled(false);
    option2.setTextColor(Color.WHITE);
    option3.setEnabled(false);
    option3.setTextColor(Color.WHITE);
    changeQuestion();

}//end method

private void changeQuestion() {


    nextBtn.setEnabled(true);
    nextBtn.setOnClickListener(view1->{

        if (nextBtn.isPressed() || skipBtn.isPressed()) {

            questionsEngine();

        }//end if
    });


}//end method


private void questionsEngine() {

    if (questionNum < questionLibraries.size() - 1) {
        questionNum++;
        qCounter += 1;
        questionCounter.setText("Question " + qCounter);
        startAnim(question, 0, 0);
        startAnim(option1, 0, 1);
        startAnim(option2, 0, 2);
        startAnim(option3, 0, 3);
        option1.setEnabled(true);
        option1.setTextColor(Color.WHITE);
        option2.setEnabled(true);
        option2.setTextColor(Color.WHITE);
        option3.setEnabled(true);
        option3.setTextColor(Color.WHITE);


    }//end if

    else {

        startActivity(new Intent(reading_level1.this, reading_section.class));
        finish();

    }//end else


}//end method


private void SkippingOrQuitting() {

    skipBtn.setOnClickListener(view->{

        if (skipBtn.isPressed()) {

            questionsEngine();

        }//end if

    });


    if (exitBtn.isPressed()) {

        long t = System.currentTimeMillis();
        int pressedBtn = 1;
        if (t - pressedBtn < 2000) {

            Toast.makeText(this, "Click again to finish the game", Toast.LENGTH_SHORT).show();

        }//end if

        else {

            startActivity(new Intent(reading_level1.this, reading_section.class));
            finish();

        }//end else

    }//end if

}//end method


private void startAnim(View view, final int value, final int questionView) {


    view.animate().alpha(value).scaleX(value).scaleY(value).
            setDuration(500).setStartDelay(400).setInterpolator(new DecelerateInterpolator())
            .setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }//end method

                @SuppressLint("NewApi")
                @Override
                public void onAnimationEnd(Animator animator) {


                    if (value == 0) {


                        switch (questionView) {
                            case 0:
                                ((TextView) view).setText(questionLibraries.get(questionNum).getQuestion());

                                break;

                            case 1:
                                ((Button) view).setText(questionLibraries.get(questionNum).getOption1());

                                break;
                            case 2:
                                ((Button) view).setText(questionLibraries.get(questionNum).getOption2());

                                break;
                            case 3:
                                ((Button) view).setText(questionLibraries.get(questionNum).getOption3());

                                break;


                        }//end switch

                        if (questionView != 0)

                            ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3A4A74")));
                        nextBtn.setEnabled(false);

                        startAnim(view, 1, questionView);

                    }//end if


                }//end method

                @Override
                public void onAnimationCancel(Animator animator) {

                }//end method

                @Override
                public void onAnimationRepeat(Animator animator) {

                }//end method
            });

}//end method


@Override
public void onBackPressed() {

    super.onBackPressed();

}//end method

}//end class