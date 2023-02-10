package uk.ac.abertay.honsfirebase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth auth;
    private Button login, forgot, create;
    private EditText email_field, pass_field;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();

        root = findViewById(R.id.constraint_root);
        login = findViewById(R.id.submit_login); // login details submission button
        forgot = findViewById(R.id.forgot_pass); // forgot password redirect button
        create = findViewById(R.id.create_acc); // create new account redirect button
        email_field = findViewById(R.id.email_field);
        pass_field = findViewById(R.id.pass_field);

        login.setOnClickListener(this); //set listeners
        forgot.setOnClickListener(this);
        create.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //SUMMARY
    //Retrieves email and password entered into EditText fields by user
    //Checks that values are not null / empty and casts them to String type
    //returns String[<email>,<password>]
    public String[] getValuesFromFields(){

        Editable email = email_field.getText();
        Editable password = pass_field.getText();
        String[] vals = {"email", "password"};

        //very basic validation - just checks fields were entered
        if(email != null && email.length()>0){
            vals[0] = email.toString(); //update email value

            if(password != null && password.length()>0){
                vals[1] = password.toString(); //update password value
            }
            else{
                Toast.makeText(this, "Password is empty!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Email is empty!", Toast.LENGTH_SHORT).show();
        }
        return vals;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.submit_login:
                //grab email and password , validate data , submit for login
                //if login succeeds , redirect to home screen
                String[] vals = getValuesFromFields();
                Toast.makeText(this, "Login tapped\nEmail:" +vals[0]+" | Pass: "+vals[1], Toast.LENGTH_SHORT).show();
                break;
            case R.id.create_acc:
                //launch create account fragment to submit details for account creation
                Toast.makeText(this, "Create Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.forgot_pass:
                //run password reset funcs
                Toast.makeText(this, "Forgot Password", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
