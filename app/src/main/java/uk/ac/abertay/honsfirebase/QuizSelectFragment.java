package uk.ac.abertay.honsfirebase;

import androidx.fragment.app.Fragment;

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


    //SUMMARY
    //  Launches a quiz activity based on user selection
    //  The mixed topics quiz CAN ONLY BE ACCESSED IF USER HAS ATTEMPTED ALL OTHER TOPICS
    //TODO - add activity transactions when quiz activities are implemented
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.quiz_variables:
                Toast.makeText(getContext(), "VARIABLES QUIZ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.quiz_operators:
                Toast.makeText(getContext(), "OPERATORS QUIZ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.quiz_conditions:
                Toast.makeText(getContext(), "CONDITIONS & SELECTION QUIZ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.quiz_loops:
                Toast.makeText(getContext(), "LOOPS QUIZ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.quiz_functions:
                Toast.makeText(getContext(), "FUNCTIONS QUIZ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.quiz_mixed:
//TODO - the mixed quiz should only be accessed once the user has attempted the other 5 at least one time
                Toast.makeText(getContext(), "MIXED QUIZ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
