package uk.ac.abertay.honsfirebase;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class QuizSelectFragment extends Fragment implements View.OnClickListener {

    private Button quiz_variables, quiz_operators, quiz_conditions, quiz_loops, quiz_functions, quiz_mixed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated_view = inflater.inflate(R.layout.quiz_select_frag, container, false);

        //layout object initialisation here
        quiz_conditions = inflated_view.findViewById(R.id.quiz_conditions);
        quiz_operators = inflated_view.findViewById(R.id.quiz_operators);
        quiz_variables = inflated_view.findViewById(R.id.quiz_variables);
        quiz_loops = inflated_view.findViewById(R.id.quiz_loops);
        quiz_functions = inflated_view.findViewById(R.id.quiz_functions);
        quiz_mixed = inflated_view.findViewById(R.id.quiz_mixed);

        //assign listeners
        quiz_variables.setOnClickListener(this);
        quiz_operators.setOnClickListener(this);
        quiz_conditions.setOnClickListener(this);
        quiz_loops.setOnClickListener(this);
        quiz_functions.setOnClickListener(this);
        quiz_mixed.setOnClickListener(this);

        return inflated_view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //firebase initialisation -goes here-
    }



    //TODO - vars quiz now working, other topics need implemented

    //SUMMARY
    //  Launches a quiz activity based on user selection
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getContext(), QuizActivity.class);

        switch (view.getId()){
            case R.id.quiz_variables:

                intent.putExtra("FRAG_TAG", "VARS_QUIZ");
                startActivity(intent);
                break;

            case R.id.quiz_operators:

                intent.putExtra("FRAG_TAG", "OPER_QUIZ");
                startActivity(intent);
                break;

            case R.id.quiz_conditions:

                intent.putExtra("FRAG_TAG", "COND_QUIZ");
                startActivity(intent);
                break;

            case R.id.quiz_loops:

                intent.putExtra("FRAG_TAG", "LOOPS_QUIZ");
                startActivity(intent);
                break;

            case R.id.quiz_functions:

                intent.putExtra("FRAG_TAG", "FUNC_QUIZ");
                startActivity(intent);
                break;

            case R.id.quiz_mixed:

                intent.putExtra("FRAG_TAG", "MIXED_QUIZ");
                startActivity(intent);
                break;
        }
    }
}
