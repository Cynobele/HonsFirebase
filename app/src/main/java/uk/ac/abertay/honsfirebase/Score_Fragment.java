package uk.ac.abertay.honsfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Score_Fragment extends Fragment {

    private TextView score_text;

    public Score_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated_view = inflater.inflate(R.layout.score_frag, container, false);

        score_text = inflated_view.findViewById(R.id.score_text);

        int score = getArguments().getInt("score");


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


        return inflated_view;
    }
}
