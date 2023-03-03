package uk.ac.abertay.honsfirebase;

import androidx.fragment.app.Fragment;

import android.content.Intent;
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
    @Override
    public void onClick(View view) {
        //this intent is shared by each branch
        Intent intent = new Intent(getContext(), LessonActivity.class);
        switch (view.getId()){
            case R.id.lesson_variables:
                //Toast.makeText(getContext(), "VARIABLES LESSON", Toast.LENGTH_SHORT).show();

                intent.putExtra("FRAG_TAG", "VARS_LESSON");
                startActivity(intent);
                break;

            case R.id.lesson_operators:
                //Toast.makeText(getContext(), "OPERATORS LESSON", Toast.LENGTH_SHORT).show();

                intent.putExtra("FRAG_TAG", "OPER_LESSON");
                startActivity(intent);
                break;

            case R.id.lesson_conditions:
                //Toast.makeText(getContext(), "CONDITIONS & SELECTION LESSON", Toast.LENGTH_SHORT).show();

                intent.putExtra("FRAG_TAG", "COND_LESSON");
                startActivity(intent);
                break;

            case R.id.lesson_loops:
                //Toast.makeText(getContext(), "LOOPS LESSON", Toast.LENGTH_SHORT).show();

                intent.putExtra("FRAG_TAG", "LOOP_LESSON");
                startActivity(intent);
                break;

            case R.id.lesson_functions:
                Toast.makeText(getContext(), "FUNCTIONS LESSON", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
