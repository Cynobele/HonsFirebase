package uk.ac.abertay.honsfirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    QuestionCollection qc = new QuestionCollection();
    private Button next_question;
    private FrameLayout container;
    private String tag = "initial";
    private TreeMap<Integer, Question> pack;
    private FragmentManager fm = getSupportFragmentManager();

    //TODO - add other fragment inits once classes are created
    private Fragment multiple_choice = new MC_Fragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_container);

        next_question = findViewById(R.id.submit);
        container = findViewById(R.id.fragment_container);

        next_question.setOnClickListener(this);


        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras!=null){
                tag = extras.getString("FRAG_TAG");
            }
        }

        Toast.makeText(this, "TAG: "+tag, Toast.LENGTH_SHORT).show();

        String[] questions={}, q_types={}, wrong_answers={}, correct_answers={}, incorrects;
        switch (tag){
            case "VARS_QUIZ":

                questions = getResources().getStringArray(R.array.vars_questions);
                q_types = getResources().getStringArray(R.array.vars_types);
                correct_answers = getResources().getStringArray(R.array.vars_correct_answers);
                wrong_answers = getResources().getStringArray(R.array.vars_wrong_answers);

                //dump any map content before attempting to fill
                if(!qc.isMapEmpty()){
                    qc.emptyMapContent();
                }

                for(int i=1; i<=10; i++){

                    //e.g. if question is i=3, get the answers at 5 & 6 (i*2-2) & (i*2-1)
                    incorrects = new String[]{wrong_answers[(i * 2) - 2], wrong_answers[(i * 2) - 1]};

                    Question q = new Question(questions[i-1], incorrects, correct_answers[i-1], q_types[i-1]);
                    qc.addQuestion(i-1, q);
                }


                //get the random 5
                pack = get5RandomQuestions();

                //check the first and begin transaction
                Question first = getFirstQuestion(pack);

                switchFragment(first, null);

        }


    }

    //retreive QUESTIONS map from qc
    //grab 5 random questions and return them
    public TreeMap<Integer, Question> get5RandomQuestions(){

        TreeMap<Integer, Question> rand_map = new TreeMap<>();

        while(rand_map.size() < 5) {
            Question q = qc.getRandomQuestion(qc.mapSize());
            Integer key = qc.getKey(qc.getMap(), q);

            if(!rand_map.containsValue(key)){
                rand_map.put(key, q); //add unique question
            }

        }

        return rand_map;
    }

    public Question getFirstQuestion(TreeMap<Integer, Question> questions){
        return questions.firstEntry().getValue();
    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    //gets the fragment that is visible - or returns null
    private Fragment getVisibleFrag(){
        List<Fragment> frags = fm.getFragments();
        if(frags!=null){
            for(Fragment fragment : frags){
                if(fragment != null && fragment.isVisible()){
                    return fragment;
                }
            }
        }
        return null;
    }

    //nullable frag - null is acceptable, as this means no fragment on display
    private void switchFragment(Question q, @Nullable Fragment frag){

        String x = q.getQuestion_type();

        //TODO - fill out UI and MT cases once layouts and classes are implemented

        if(frag != null) {
            Toast.makeText(this, "Next Frag:"+x+" | Visible Frag:"+ frag.getTag(), Toast.LENGTH_SHORT).show();
            switch (x) {
                case "mc":

                    //detach and reattach to force the fragment to refresh with new question content
                    fm.beginTransaction().detach(frag)
                            .attach(frag).commit();

                    break;
                case "ui":

                    break;
                case "mt":

                    break;
            }
        }

        //Transactions use .add()
        else{
            switch (x) {
                case "mc":
                    //display the multiple choice fragment
                    fm.beginTransaction()
                            .add(R.id.fragment_container, multiple_choice, "mc")
                            .setReorderingAllowed(true)
                            .addToBackStack("multiple_choice")
                            .commit();

                    break;
                case "ui":

                    break;
                case "mt":

                    break;
            }
        }
    }

    //Get the next question from the map and remove it
    public Map.Entry<Integer, Question> pollFirstPackEntry(){
        return pack.pollFirstEntry();
    }
    //Get the next question from the map
    public Map.Entry<Integer, Question> getFirstPackEntry(){
        return pack.firstEntry();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                //TODO - question submission behaivour
                //selected answer should be saved then compared with actual correct answer
                //next question button should begin question transaction for next question type


                Toast.makeText(this, "BUTTON", Toast.LENGTH_SHORT).show();


                if(!pack.isEmpty()){
                    Map.Entry<Integer, Question> entry = pollFirstPackEntry();
                    Question q = entry.getValue();
                    switchFragment(q, getVisibleFrag());
                }else{
                    Toast.makeText(this, "Pack is empty...", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }
}
