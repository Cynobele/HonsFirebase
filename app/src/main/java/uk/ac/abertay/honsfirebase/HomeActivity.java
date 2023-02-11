package uk.ac.abertay.honsfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button quiz, lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //setups

        quiz = findViewById(R.id.quiz_button);
        lesson = findViewById(R.id.lesson_button);

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
    //redirect to a selection screen based on the button selected
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.quiz_button:
                //TODO - redirect to quiz selection
                Toast.makeText(this, "QUIZZES SELECTED", Toast.LENGTH_SHORT).show();
                break;

            case R.id.lesson_button:
                //TODO - redirect to lesson selection
                Toast.makeText(this, "LESSONS SELECTED", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
