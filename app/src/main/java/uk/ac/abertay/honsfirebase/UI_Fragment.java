package uk.ac.abertay.honsfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class UI_Fragment extends Fragment {

    //Class handles the creation of user input quiz questions
    // Display an image and text for question, and accept a user's input as answer
    // Check user input against correct answer keywords and if any match, assign correct


    public UI_Fragment() {/*constructor*/}

    private ImageView image_container;
    private TextView question_text;
    private EditText input_field;
    private Question q;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated_view = inflater.inflate(R.layout.ui_frag ,container, false);

        image_container = inflated_view.findViewById(R.id.image_container);
        question_text = inflated_view.findViewById(R.id.question_text);
        input_field = inflated_view.findViewById(R.id.user_input_field);

        //Map.Entry<Integer, Question> first = ((QuizActivity)getActivity()).getFirstPackEntry();
       // Question obj = first.getValue();

        q = new Question(getArguments().getString("q_text"), getArguments().getStringArray("answers"), getArguments().getString("q_correct"), "ui");


        input_field.setText(" ");
        input_field.setHint("Write your answer here");
        question_text.setText(q.getQuestion());

        displayCorrectImage(q.getCorrect_answer());

        return inflated_view;
    }

    private void displayCorrectImage(String correct_string){
        //display the correct image based on what the answer should be

        String[] corrects = tokeniseSanitisedString(correct_string);

        switch(corrects[0].toLowerCase()){
            //vars cases
            case "no":
                break;

            //operator cases
            case "0":
                image_container.setImageResource(R.drawable.result_zero);
                break;
            case "flag":
                image_container.setImageResource(R.drawable.flag_true);
                break;
            case "a":
                image_container.setImageResource(R.drawable.a_true);
                break;

            //conditional cases
            case "word":
                image_container.setImageResource(R.drawable.output_word);
                break;
            case "6":
                image_container.setImageResource(R.drawable.value_6);
                break;
            case "14":
                image_container.setImageResource(R.drawable.value_14);
                break;
            case "nested":
                image_container.setImageResource(R.drawable.nested_conditional);
                break;

            //loops cases
            case "4":
                image_container.setImageResource(R.drawable.b_four);
                break;
            case "8":
                image_container.setImageResource(R.drawable.b_eight);
                break;
            case "15":
                image_container.setImageResource(R.drawable.sum_fifteen);
                break;


            //functions cases
            case "float":
                image_container.setImageResource(R.drawable.float_params);
                break;
            case "string":
                image_container.setImageResource(R.drawable.function_string);
                break;
            case "functionexample":
                image_container.setImageResource(R.drawable.function_name);
                break;
            case "55":
                image_container.setImageResource(R.drawable.value_55);
                break;
        }
    }

    private String getInputFromField(){
        return input_field.getText().toString().toLowerCase();
    }

    private String sanitiseStringInput(String input){
        String regex = "[^a-zA-Z0-9\\s]";

        //remove all non-english characters & symbols
        //only text and numbers will remain
        return input.replaceAll(regex, "");
    }

    private String[] tokeniseSanitisedString(String input){
        //receive sanitised string and split it into individual words
        return input.split("\\s");
    }

    private Boolean doesAnswerContainKeywords(String[] tokens){

        //check if the user input matches the 'keywords'
        //correct answer in String.xml should be list of keywords, not actual sentence
        String correct_string = q.getCorrect_answer();
        String[] keywords = tokeniseSanitisedString(correct_string);

        //check the tokens against the keywords, return true if there is a match
        for(int i=0; i<tokens.length; i++){
            for(int j=0; j<keywords.length; j++){
                if(tokens[i].contentEquals(keywords[j])){
                    return true;
                }
            }
        }

        //return false if no tokens matched keywords
        return false;
    }

    public int getResultFromUI(){
        String input = getInputFromField();
        String[] tokens;
        Boolean flag;

        if(input.length() > 0) {
            input = sanitiseStringInput(input);
            tokens = tokeniseSanitisedString(input);
            flag = doesAnswerContainKeywords(tokens);

            if(flag){

                //input box contained 'correct' answer
                //a keyword matched, very simple checking method
                return 1;
            }

        }else{

            //input box was empty, display message to user
            return 2;
        }

        //box contained answer, but was incorrect
        return 0;
    }

}
