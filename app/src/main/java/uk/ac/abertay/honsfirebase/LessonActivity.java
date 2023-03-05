package uk.ac.abertay.honsfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.List;

public class LessonActivity extends AppCompatActivity{

    private FrameLayout container;
    private String tag; // the lesson that was selected by the user
    private FragmentManager fm = getSupportFragmentManager();
    private Fragment vars_frag = new VariableLessonFragment();
    private Fragment oper_frag = new OperatorLessonFragment();
    private Fragment cond_frag = new ConditionLessonFragment();
    private Fragment loop_frag = new LoopLessonFragment();
    private Fragment func_frag = new FunctionLessonFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //pass tag to displayLesson

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            tag = extras.getString("FRAG_TAG");
            //Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
            displayLesson(tag);
        }
    }

    //SUMMARY
    //when the back button is pressed and a fragment is in display,
    //only remove the fragment if there is another to show
    //- if no fragments, then restart the selection activity
    @Override
    public void onBackPressed() {
        if(fm.getFragments().size() == 1) {
            Intent intent = new Intent(this, HomeActivity.class);
            //pass a key to make home activity automatically start the lesson selection activity
            //this will make it appear as if we only went back 1 screen...
            intent.putExtra("frag", "LESSON_SELECT");
            startActivity(intent);
        }else{
            //get number of frags (add or replace)
            super.onBackPressed();
        }
    }

    //SUMMARY
    //receives the tag value passed through the activity transaction (in bundle)
    //switch statement displays correct lesson topic fragment based on tag
    //tag value is determined by the lesson selection fragment user input buttons
    private void displayLesson(String tag){

        Fragment vis = getVisibleFrag();
        switch(tag){
            case "VARS_LESSON":
                //use replace if frag is present
                //use add if none present.

                if(vis == null){
                    //no fragment is visible

                    fm.beginTransaction().add(R.id.fragment_container, vars_frag, "vars_frag")
                            .addToBackStack("vars_frag").setReorderingAllowed(true).commit();
                } else {
                    //a fragment is visible

                    fm.beginTransaction().replace(R.id.fragment_container, vars_frag, "vars_frag")
                            .addToBackStack("vars_frag").setReorderingAllowed(true).commit();
                }

                break;
            case "OPER_LESSON":

                if(vis == null){
                    //no fragment is visible
                    fm.beginTransaction().add(R.id.fragment_container, oper_frag, "oper_frag")
                            .addToBackStack("oper_frag").setReorderingAllowed(true).commit();
                } else {
                    //a fragment is visible
                    fm.beginTransaction().replace(R.id.fragment_container, oper_frag, "oper_frag")
                            .addToBackStack("oper_frag").setReorderingAllowed(true).commit();
                }

                break;

            case "COND_LESSON":

            if(vis == null){
                //no fragment is visible
                fm.beginTransaction().add(R.id.fragment_container, cond_frag, "cond_frag")
                        .addToBackStack("cond_frag").setReorderingAllowed(true).commit();
            } else {
                //a fragment is visible
                fm.beginTransaction().replace(R.id.fragment_container, cond_frag, "cond_frag")
                        .addToBackStack("cond_frag").setReorderingAllowed(true).commit();
            }

            break;

            case "LOOP_LESSON":

            if(vis == null){
                //no fragment is visible
                fm.beginTransaction().add(R.id.fragment_container, loop_frag, "loop_frag")
                        .addToBackStack("loop_frag").setReorderingAllowed(true).commit();
            } else {
                //a fragment is visible
                fm.beginTransaction().replace(R.id.fragment_container, loop_frag, "loop_frag")
                        .addToBackStack("loop_frag").setReorderingAllowed(true).commit();
            }

            break;

            case "FUNC_LESSON":

                if(vis == null){
                    //no fragment is visible
                    fm.beginTransaction().add(R.id.fragment_container, func_frag, "func_frag")
                            .addToBackStack("func_frag").setReorderingAllowed(true).commit();
                } else {
                    //a fragment is visible
                    fm.beginTransaction().replace(R.id.fragment_container, func_frag, "func_frag")
                            .addToBackStack("func_frag").setReorderingAllowed(true).commit();
                }

                break;
        }
    }

    //SUMMARY
    //finds which fragment is currently visible, so it can be replaced
    //returns null if no fragment is visible
    public Fragment getVisibleFrag(){
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
