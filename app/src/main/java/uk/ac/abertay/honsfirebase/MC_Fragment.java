package uk.ac.abertay.honsfirebase;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MC_Fragment extends Fragment implements View.OnClickListener{

    private Button option_1, option_2, option_3;
    private TextView question_text;
    private int selected, location;

    public MC_Fragment() {/*constructor*/}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated_view = inflater.inflate(R.layout.mc_frag, container, false);

        option_1 = inflated_view.findViewById(R.id.top_button);
        option_2 = inflated_view.findViewById(R.id.mid_button);
        option_3 = inflated_view.findViewById(R.id.bot_button);
        question_text = inflated_view.findViewById(R.id.question_text);

        option_1.setOnClickListener(this);
        option_2.setOnClickListener(this);
        option_3.setOnClickListener(this);

        option_1.setBackgroundColor(Color.LTGRAY); //set initial button colours
        option_2.setBackgroundColor(Color.LTGRAY);
        option_3.setBackgroundColor(Color.LTGRAY);

        selected = 0;

        Question q = new Question(getArguments().getString("q_text"), getArguments().getStringArray("answers"), getArguments().getString("q_correct"), "mc");

        question_text.setText(q.getQuestion()); //display question

        setButtonOrder(q); //randomise the order of buttons

        return inflated_view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    private void setButtonOrder(Question q){
        int x;
        x = new Random().nextInt(3);
        switch(x){
            case 0:

                option_1.setText(q.getAnswers()[1]);
                option_2.setText(q.getCorrect_answer());
                option_3.setText(q.getAnswers()[0]);

                location = 2;
                break;
            case 1:

                option_1.setText(q.getAnswers()[0]);
                option_2.setText(q.getAnswers()[1]);
                option_3.setText(q.getCorrect_answer());

                location = 3;
                break;
            case 2:

                option_1.setText(q.getCorrect_answer());
                option_2.setText(q.getAnswers()[1]);
                option_3.setText(q.getAnswers()[0]);

                location = 1;
                break;
        }
    }

    public int getResultFromMC(){

        if(selected == 0){
            return 2; //nothing was selected, display message to user
        }else if(selected == location){

            return 1; // selected answer is correct
        }else{
            return 0; // selected answer was incorrect
        }
    }

    public void setSelected(int i){
        this.selected = i;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.top_button:

                option_1.setBackgroundColor(Color.GREEN);
                option_2.setBackgroundColor(Color.LTGRAY);
                option_3.setBackgroundColor(Color.LTGRAY);

                selected = 1; //button 1 was last selected

                break;
            case R.id.mid_button:

                option_1.setBackgroundColor(Color.LTGRAY);
                option_2.setBackgroundColor(Color.GREEN);
                option_3.setBackgroundColor(Color.LTGRAY);

                selected = 2; //button 2 was last selected

                break;
            case R.id.bot_button:

                option_1.setBackgroundColor(Color.LTGRAY);
                option_2.setBackgroundColor(Color.LTGRAY);
                option_3.setBackgroundColor(Color.GREEN);

                selected = 3; //button 3 was last selected

                break;
        }
    }
}
