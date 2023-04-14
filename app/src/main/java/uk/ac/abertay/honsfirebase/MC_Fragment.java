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

        //use getFirstPackEntry, not poll, or questions will end early
        Map.Entry<Integer, Question> entry = ((QuizActivity)getActivity()).getFirstPackEntry();
        Question obj = entry.getValue();

        question_text.setText(obj.getQuestion());


        //TODO - when an option is selected in the quiz, change its color
        /*
        option_1.setBackgroundColor(Color.BLUE);
        option_2.setBackgroundColor(Color.YELLOW);
        option_3.setBackgroundColor(Color.GREEN);
        */

        setButtonOrder(obj);

        return inflated_view;
    }


    private void setButtonOrder(Question q){
        int x;
        x = new Random().nextInt(3);
        switch(x){
            case 0:

                option_1.setText(q.getAnswers()[1]);
                option_2.setText(q.getCorrect_answer());
                option_3.setText(q.getAnswers()[0]);

                break;
            case 1:

                option_1.setText(q.getAnswers()[0]);
                option_2.setText(q.getAnswers()[1]);
                option_3.setText(q.getCorrect_answer());

                break;
            case 2:

                option_1.setText(q.getCorrect_answer());
                option_2.setText(q.getAnswers()[1]);
                option_3.setText(q.getAnswers()[0]);

                break;
        }
    }

    @Override
    public void onClick(View view) {

    }
}
