package uk.ac.abertay.honsfirebase;

import java.util.List;

//Class used to define a 'question' - questions are different based on the type of minigame/quiz
public class Question {
    private String question;
    private String[] answers;
    private String correct_answer;
    private String question_type;

    public Question(String question, String[] answers, String correct_answer, String question_type){
        this.question = question;
        this.answers = answers;
        this.correct_answer = correct_answer;
        this.question_type = question_type;
    }

    //the text for the question being asked
    public String getQuestion() {
        return question;
    }

    //a list of dummy answers
    public String[] getAnswers() {
        return answers;
    }

    //the correct answer to the question
    public String getCorrect_answer() {
        return correct_answer;
    }

    //dictates which type of quiz fragment should be displayed
    public String getQuestion_type() {return question_type;}
}
