package uk.ac.abertay.honsfirebase;

import android.content.Intent;
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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    QuestionCollection qc = new QuestionCollection();
    private Button next_question;
    private FrameLayout container;
    private String tag = "initial";
    private TreeMap<Integer, Question> pack;
    private TreeMap<Integer, Boolean> answers = new TreeMap<>();
    private FragmentManager fm = getSupportFragmentManager();

    //TODO - add other fragment inits once classes are created
    private Fragment multiple_choice = new MC_Fragment();
    private Fragment user_input = new UI_Fragment();
    private Fragment score_frag = new Score_Fragment();


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

                Log.d("Map Keys", ""+pack.keySet());
                //check the first and begin transaction
                //Question first = getFirstQuestion(pack);

                //Map.Entry<Integer, Question> first = pollFirstPackEntry();

                switchFragment(getVisibleFrag());

                break;
            case "OPER_QUIZ":
                questions = getResources().getStringArray(R.array.oper_questions);
                q_types = getResources().getStringArray(R.array.oper_types);
                correct_answers = getResources().getStringArray(R.array.oper_correct_answers);
                wrong_answers = getResources().getStringArray(R.array.oper_wrong_answers);

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

                pack = get5RandomQuestions();

                Log.d("Map Keys", ""+pack.keySet());

                switchFragment(getVisibleFrag());
                break;
            case "COND_QUIZ":
                questions = getResources().getStringArray(R.array.cond_questions);
                q_types = getResources().getStringArray(R.array.cond_types);
                correct_answers = getResources().getStringArray(R.array.cond_correct_answers);
                wrong_answers = getResources().getStringArray(R.array.cond_wrong_answers);

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

                pack = get5RandomQuestions();

                Log.d("Map Keys", ""+pack.keySet());

                switchFragment(getVisibleFrag());
                break;
            case "LOOPS_QUIZ":

                questions = getResources().getStringArray(R.array.loops_questions);
                q_types = getResources().getStringArray(R.array.loops_types);
                correct_answers = getResources().getStringArray(R.array.loops_answers);
                wrong_answers = getResources().getStringArray(R.array.loops_wrong_answers);

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

                pack = get5RandomQuestions();

                Log.d("Map Keys", ""+pack.keySet());

                switchFragment(getVisibleFrag());
                break;

            case "FUNC_QUIZ":
                questions = getResources().getStringArray(R.array.funcs_questions);
                q_types = getResources().getStringArray(R.array.funcs_types);
                correct_answers = getResources().getStringArray(R.array.funcs_answers);
                wrong_answers = getResources().getStringArray(R.array.funcs_wrong_answers);

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

                pack = get5RandomQuestions();

                Log.d("Map Keys", ""+pack.keySet());

                switchFragment(getVisibleFrag());
                break;
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

    //Get the next question from the map and remove it
    public Map.Entry<Integer, Question> pollFirstPackEntry(){return pack.pollFirstEntry();}


    @Override
    protected void onStart() {
        super.onStart();
    }


    //nullable frag - null is acceptable, as this means no fragment on display
    private void switchFragment(/*Question q,*/ @Nullable Fragment frag){

        Map.Entry<Integer, Question> entry = pollFirstPackEntry();
        Question q = entry.getValue();

        String x = q.getQuestion_type();
        Bundle bundle = new Bundle();
        bundle.putString("q_text", q.getQuestion());
        bundle.putString("q_correct", q.getCorrect_answer());
        bundle.putStringArray("answers", q.getAnswers());


        if(frag != null) {

            //remove any visible fragment before adding the next question
            fm.beginTransaction().remove(frag).commit();
        }

        //display whichever fragment type the question requires
        switch (x){
            case "mc":
                multiple_choice.setArguments(bundle);

                fm.beginTransaction()
                        .add(R.id.fragment_container, multiple_choice, "mc")
                        .setReorderingAllowed(true)
                        .addToBackStack("multiple_choice")
                        .commit();
                break;
            case "ui":
                user_input.setArguments(bundle);

                fm.beginTransaction()
                        .add(R.id.fragment_container, user_input, "ui")
                        .setReorderingAllowed(true)
                        .addToBackStack("user_input")
                        .commit();
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:

                Fragment f = getVisibleFrag();
                String behaviour = f.getTag();
                Boolean can_move = false; //can we move to next question?
                int result_code; //default to the no input code

                Log.d("Behaviour onClick", ""+behaviour);

                switch (behaviour){
                    case "mc":

                        //collect and save selected question from multiple choice
                        MC_Fragment mc_f = (MC_Fragment) getSupportFragmentManager().findFragmentByTag("mc");
                        result_code = mc_f.getResultFromMC();

                        if(result_code == 2) { // no answer selected
                            Toast.makeText(this, "No option was chosen!", Toast.LENGTH_SHORT).show();
                            can_move = false;
                        }else if(result_code == 0) { //incorrect answer chosen

                            if(!answers.isEmpty())
                            {answers.put(answers.size(), false);}
                            else{answers.put(0, false);}

                            can_move = true;

                        }else{ //correct answer chosen

                            if(!answers.isEmpty())
                            {answers.put(answers.size(), true);}
                            else{answers.put(0, true);}

                            can_move = true;
                        }

                        //reset the selected answer so the next question cannot be left unanswered
                        mc_f.setSelected(0);
                        break;

                    case "ui":
                        //collect and save user input & result

                        UI_Fragment ui_f = (UI_Fragment)getSupportFragmentManager().findFragmentByTag("ui");
                        result_code = ui_f.getResultFromUI();

                        if(result_code == 2) { // no input
                            Toast.makeText(this, "The input box was left empty!", Toast.LENGTH_SHORT).show();
                            can_move = false;
                        }
                        else if(result_code == 0) { //incorrect answer

                            if(!answers.isEmpty())
                            {answers.put(answers.size(), false);}
                            else{answers.put(0, false);}

                            can_move = true;
                        }
                        else{ //correct answer
                            if(!answers.isEmpty())
                            {answers.put(answers.size(), true);}
                            else{answers.put(0, true);}

                            can_move = true;
                        }
                        break;
                }



                if(!pack.isEmpty() && can_move){

                    switchFragment(f);

                }else if(pack.isEmpty() && answers.size() == 5){//all questions have been asked



                    //TODO - !!! save answers to firebase for storage !!!

                    if(behaviour == "score"){
                        //score has been displayed
                        Intent intent = new Intent(this, HomeActivity.class);
                        //passing a key to homeactivity will auto redirect to select screen
                        intent.putExtra("frag", "QUIZ_SELECT");
                        startActivity(intent);
                    }

                    //  Calculate and display the score to the user
                    int score = 0;
                    Log.d("Answer map ", "Keys: "+answers.keySet()+" | Vals: "+answers.values());
                    Boolean[] vals = answers.values().toArray(new Boolean[1]);

                    //primitive boolean array, so it can be added to bundle
                    boolean[] upload_vals = {vals[0], vals[1], vals[2], vals[3], vals[4]};

                    for(int i=0; i<answers.size(); i++){
                        if(vals[i]){
                            score += 1;
                        }
                    }

                    Log.d("Score Array ", ""+vals[0]+" "+vals[1]+" "+vals[2]+" "+vals[3]+" "+vals[4]+"");
                    // send the score to the score fragment for display
                    Bundle b = new Bundle();
                    b.putInt("score", score);
                    b.putBooleanArray("answers", upload_vals);
                    score_frag.setArguments(b);

                    fm.beginTransaction().replace(R.id.fragment_container, score_frag, "score")
                            .setReorderingAllowed(true)
                            .commit();


                }else{ //huh? how did we get here...
                    Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }

                break;
        }

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
}
