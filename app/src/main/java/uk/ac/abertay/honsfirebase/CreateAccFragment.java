package uk.ac.abertay.honsfirebase;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class CreateAccFragment extends Fragment implements View.OnClickListener {

    private FragmentManager fm;
    private Button submit, forgot_pass, login;
    private EditText email_field, pass_field, conf_field;
    FirebaseAuth auth;

    public CreateAccFragment(){
        //empty constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View inflated_view = inflater.inflate(R.layout.create_acc_frag, container, false);

        fm = getFragmentManager(); //get parent fragment manager

        //assign view id's to variables
        submit = inflated_view.findViewById(R.id.create_acc_submit);
        login = inflated_view.findViewById(R.id.login);
        forgot_pass = inflated_view.findViewById(R.id.forgot_pass);
        email_field = inflated_view.findViewById(R.id.email_field_create);
        pass_field = inflated_view.findViewById(R.id.pass_field_create);
        conf_field = inflated_view.findViewById(R.id.conf_pass_field); //confirm pass only present in create acc

        //assign listeners
        submit.setOnClickListener(this);
        login.setOnClickListener(this);
        forgot_pass.setOnClickListener(this);

        return inflated_view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseApp.initializeApp(getContext());
        auth = FirebaseAuth.getInstance();
        FirebaseUser current_user = auth.getCurrentUser();
        if(current_user != null){
            Toast.makeText(getContext(), "Logged into firebase as "+current_user.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }

    private void createUserAcc(String[] credentials){
        auth.createUserWithEmailAndPassword(credentials[0], credentials[1])
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Account created successfully
                            Toast.makeText(getContext(), "ACCOUNT CREATED!", Toast.LENGTH_SHORT).show();
                        } else {
                            // If account creation fails, display an error message to the user
                            Toast.makeText(getContext(), "ACCOUNT COULD NOT BE CREATED!\n" +task.getException()
                                    , Toast.LENGTH_LONG).show();
                            //logs firebase exception
                            Log.d("~~RegistrationException~~", "Exception: "+task.getException());
                        }

                    }
                });
    }

    @Override
    public void onClick(View view){
        switch(view.getId()) {

            //SUMMARY
            //get values entered by user and validate them
            //password values must match
            //output sanitised and valid values to Firebase and receive response on completion
            case R.id.create_acc_submit:
                Toast.makeText(getContext(), "Submit account creation details", Toast.LENGTH_SHORT).show();
                String[] vals = getValuesFromFields();
                createUserAcc(vals);
                break;

            //SUMMARY
            //launch login fragment transaction
            case R.id.login:
                Toast.makeText(getContext(), "CreateAcc => Login", Toast.LENGTH_SHORT).show();
                fm.beginTransaction()
                        .replace(R.id.fragment_container, new LoginFragment(), "login")
                        .setReorderingAllowed(true)
                        .addToBackStack("login")
                        .commit();
                break;

            //SUMMARY
            //launch forgot password fragment transaction
            case R.id.forgot_pass:
                Toast.makeText(getContext(), "CreateAcc => ForgotPass", Toast.LENGTH_SHORT).show();
                //TODO - add forgot pass functionality here & in login frag
                break;
        }
    }


    //SUMMARY
    //Retrieves email and password entered into EditText fields by user
    //Checks that values are not null / empty and casts them to String type
    //returns String[<email>,<password>]
    private String[] getValuesFromFields(){

        Editable email = email_field.getText();
        Editable password = pass_field.getText();
        Editable conf_pass = conf_field.getText(); //conf_pass & password must match
        String[] vals = {"email", "password"};

        //very basic validation - just checks fields were entered
        if(email != null && email.length()>0){
            vals[0] = email.toString(); //update email value

            if(password != null && password.length()>5) {
                if (conf_pass.toString().equals(password.toString())) {
                    vals[1] = password.toString(); //update password value
                }else { Toast.makeText(getContext(), "Passwords must match!", Toast.LENGTH_SHORT).show(); }
            }
            else{
                Toast.makeText(getContext(), "Password is empty!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "Email is empty!", Toast.LENGTH_SHORT).show();
        }
        return vals;
    }
}
