package com.example.englishapp;

public class QuestionLibrary {

String Question;
String Option1;
String Option2;
String Option3;
int Answer;


public QuestionLibrary(String Q, String Op1, String Op2, String Op3, int A) {

    this.Question = Q;
    this.Option1 = Op1;
    this.Option2 = Op2;
    this.Option3 = Op3;
    this.Answer = A;

}//end constructor

public String getQuestion() {
    return Question;
}

public void setQuestion(String question) {
    Question = question;
}


public String getOption1() {
    return Option1;
}

public void setOption1(String option1) {
    Option1 = option1;
}

public String getOption3() {
    return Option3;
}

public void setOption3(String option3) {
    Option3 = option3;
}


public String getOption2() {
    return Option2;
}

public void setOption2(String option2) {
    Option2 = option2;
}

public int getAnswer() {
    return Answer;
}

public void setAnswer(int answer) {
    Answer = answer;
}
}//end class
