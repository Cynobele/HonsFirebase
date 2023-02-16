package uk.ac.abertay.honsfirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button quiz, lesson;
    private FragmentManager fm;
    private Fragment quiz_select = new QuizSelectFragment();
    private Fragment lesson_select = new LessonSelectFragment();
    private Activity home_activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setups

        quiz = findViewById(R.id.quiz_button);
        lesson = findViewById(R.id.lesson_button);

        fm = getSupportFragmentManager();

        quiz.setOnClickListener(this);
        lesson.setOnClickListener(this);
    }

    //SUMMARY
    //creates a kebab button on navbar that allows user to log out
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }


    //SUMMARY
    // onClick functionality for kebab button on home screen
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            //SUMMARY
            //log out user account and redirect to MainActivity (login etc)
            case R.id.logout_button:
                //TODO - ask if sure before logging out
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return true;
    }

    //SUMMARY
    // if the user presses the back key on their device
    // either minimise the application or refresh the home activity and reassign objects
    @Override
    public void onBackPressed() {
        if(fm.getFragments().size() < 1) {
            moveTaskToBack(true);
        }else{

            //remove any visible fragments
            for(Fragment fragment : fm.getFragments()){
                fm.beginTransaction().remove(fragment).commit();
            }

            //restart the home activity - this will reassign the buttons and onClickListeners
            home_activity.recreate();
        }
    }

    //SUMMARY
    //redirect to a selection screen based on the button selected
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.quiz_button:
                //TODO - redirect to quiz selection
                Toast.makeText(this, "QUIZZES SELECTED", Toast.LENGTH_SHORT).show();

                setContentView(R.layout.activity_home_container);

                //if user presses 'back' key then fragment container may already hold an item
                //so using add will cause fatal crash - must use replace in this instance
                if(fm.getFragments().size() < 1){
                    fm.beginTransaction().add(R.id.fragment_container, quiz_select, "quiz_select")
                            .setReorderingAllowed(true)
                            .addToBackStack("quiz_select").commit();
                }else{
                    fm.beginTransaction().replace(R.id.fragment_container, quiz_select, "quiz_select")
                            .setReorderingAllowed(true)
                            .addToBackStack("quiz_select").commit();
                }
                break;

            case R.id.lesson_button:
                //TODO - redirect to lesson selection
                Toast.makeText(this, "LESSONS SELECTED", Toast.LENGTH_SHORT).show();

                setContentView(R.layout.activity_home_container);
                if(fm.getFragments().size() < 1){
                    fm.beginTransaction().add(R.id.fragment_container, lesson_select, "lesson_select")
                            .setReorderingAllowed(true)
                            .addToBackStack("lesson_select").commit();
                }else{
                    fm.beginTransaction().replace(R.id.fragment_container, lesson_select, "lesson_select")
                            .setReorderingAllowed(true)
                            .addToBackStack("lesson_select").commit();
                }
                break;
        }
    }
}
