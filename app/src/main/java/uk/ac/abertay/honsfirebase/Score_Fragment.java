package uk.ac.abertay.honsfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Score_Fragment extends Fragment {

    private TextView score_text;
    private ImageView img1, img2, img3, img4, img5;

    public Score_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated_view = inflater.inflate(R.layout.score_frag, container, false);

        score_text = inflated_view.findViewById(R.id.score_text);
        img1 = inflated_view.findViewById(R.id.answer_image_1);
        img2 = inflated_view.findViewById(R.id.answer_image_2);
        img3 = inflated_view.findViewById(R.id.answer_image_3);
        img4 = inflated_view.findViewById(R.id.answer_image_4);
        img5 = inflated_view.findViewById(R.id.answer_image_5);

        //TODO - pass the array of bools and display graphics for correct or wrong answers
        // - 5 small images
        int score = getArguments().getInt("score");
        boolean[] answers = getArguments().getBooleanArray("answers");

        //display text & images based on the answers provided
        displayScoreText(score);
        displayAnswerGraphics(answers);

        return inflated_view;
    }

    private void displayScoreText(int score){
        //display text based on the score achieved
        switch (score){
            case 5:
                score_text.setText("You scored "+score+ "/5! \n Well Done!");
                break;
            case 4:
                score_text.setText("You scored "+score+ "/5! \n So Close!");
                break;
            case 0:
                score_text.setText("You scored "+score+ "/5! \n Read through a lesson and try again!");
                break;
            default:
                score_text.setText("You scored "+score+ "/5!");
                break;
        }
    }

    private void displayAnswerGraphics(boolean[] answers){

        for(int i =0; i<answers.length; i++){
            setImage(i, answers[i]);
        }
    }

    private void setImage(int i, boolean j){
        switch(i){
            case 0:
                if (j){ img1.setImageResource(R.drawable.correct);}else{img1.setImageResource(R.drawable.wrong);}
                break;
            case 1:
                if (j){ img2.setImageResource(R.drawable.correct);}else{img2.setImageResource(R.drawable.wrong);}
                break;
            case 2:
                if (j){ img3.setImageResource(R.drawable.correct);}else{img3.setImageResource(R.drawable.wrong);}
                break;
            case 3:
                if (j){ img4.setImageResource(R.drawable.correct);}else{img4.setImageResource(R.drawable.wrong);}
                break;
            case 4:
                if (j){ img5.setImageResource(R.drawable.correct);}else{img5.setImageResource(R.drawable.wrong);}
                break;
        }
    }
}
