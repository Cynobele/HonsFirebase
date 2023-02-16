package uk.ac.abertay.honsfirebase;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LessonSelectFragment extends Fragment implements View.OnClickListener {

    private Button lesson_variables, lesson_operators, lesson_conditions, lesson_loops, lesson_functions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated_view = inflater.inflate(R.layout.lesson_select_frag, container, false);

        //initialise layout objects here
        lesson_conditions = inflated_view.findViewById(R.id.lesson_conditions);
        lesson_functions = inflated_view.findViewById(R.id.lesson_functions);
        lesson_loops = inflated_view.findViewById(R.id.lesson_loops);
        lesson_operators = inflated_view.findViewById(R.id.lesson_operators);
        lesson_variables = inflated_view.findViewById(R.id.lesson_variables);

        //assign listeners
        lesson_variables.setOnClickListener(this);
        lesson_operators.setOnClickListener(this);
        lesson_conditions.setOnClickListener(this);
        lesson_loops.setOnClickListener(this);
        lesson_functions.setOnClickListener(this);

        return inflated_view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //firebase initialisation goes here
    }

    //SUMMARY
    //  Handles the launching of Lesson Activities through switch statement
    //  Lessons can be accessed in any order
    //TODO - when lesson activites have been implemented, add transactions to redirect here
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lesson_variables:
                Toast.makeText(getContext(), "VARIABLES LESSON", Toast.LENGTH_SHORT).show();
                break;

            case R.id.lesson_operators:
                Toast.makeText(getContext(), "OPERATORS LESSON", Toast.LENGTH_SHORT).show();
                break;

            case R.id.lesson_conditions:
                Toast.makeText(getContext(), "CONDITIONS & SELECTION LESSON", Toast.LENGTH_SHORT).show();
                break;

            case R.id.lesson_loops:
                Toast.makeText(getContext(), "LOOPS LESSON", Toast.LENGTH_SHORT).show();
                break;

            case R.id.lesson_functions:
                Toast.makeText(getContext(), "FUNCTIONS LESSON", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
