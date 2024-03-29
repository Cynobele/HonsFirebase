package uk.ac.abertay.honsfirebase;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
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

public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentManager fm;
    private Button login, forgot, create;
    private EditText email_field, pass_field;
    FirebaseAuth auth;

    public LoginFragment(){/*empty constructor*/}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View inflated_view = inflater.inflate(R.layout.login_frag, container, false);

        fm = getFragmentManager(); //gets the fragment manager from parent

        //assign view id's to variables
        login = inflated_view.findViewById(R.id.submit_login); // login details submission button
        forgot = inflated_view.findViewById(R.id.forgot_pass); // forgot password redirect button
        create = inflated_view.findViewById(R.id.create_acc); // create new account redirect button
        email_field = inflated_view.findViewById(R.id.email_field_login);
        pass_field = inflated_view.findViewById(R.id.pass_field_login);

        login.setOnClickListener(this); //set listeners
        forgot.setOnClickListener(this);
        create.setOnClickListener(this);

        return inflated_view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseApp.initializeApp(getContext());
        auth = FirebaseAuth.getInstance();

        FirebaseUser current_user = auth.getCurrentUser();
        if(current_user != null){ //redirect if already logged in
            Toast.makeText(getContext(), "Logged in as "+current_user.getEmail(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }
    }

    //SUMMARY
    //Retrieves email and password entered into EditText fields by user
    //Checks that values are not null / empty and casts them to String type
    //returns String[<email>,<password>]
    private String[] getValuesFromFields(){

        Editable email = email_field.getText();
        Editable password = pass_field.getText();
        String[] vals = {"email", "password"};

        //very basic validation - just checks fields were entered
        if(email != null && email.length()>0){
            vals[0] = email.toString(); //update email value

            if(password != null && password.length()>5){
                vals[1] = password.toString(); //update password value
            }
            else{
                Toast.makeText(getContext(), "Password is empty!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "Email is empty!", Toast.LENGTH_SHORT).show();
        }
        return vals;
    }



    //SUMMARY
    //attempt to login to an existing user in firebase using values entered by user
    private void attemptFirebaseLogin(String[] credentials){

        auth.signInWithEmailAndPassword(credentials[0], credentials[1])
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in successful
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(getContext(), "Logged in!\nEmail:"+user.getEmail(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user and log exception
                            Toast.makeText(getContext(), "Login Failed!",Toast.LENGTH_SHORT).show();
                            Log.d("~~Login Exception~~", "Exception: "+task.getException());
                        }
                    }
                });
    }


    @Override
    public void onClick(View view){
        switch (view.getId()){

            //SUMMARY
            //grab email and password , validate data , submit for login
            //if login succeeds , redirect to home screen
            case R.id.submit_login:

                String[] vals = getValuesFromFields();
                attemptFirebaseLogin(vals);
                break;

            //SUMMARY
            //launch create account fragment to submit details for account creation
            case R.id.create_acc:
               // Toast.makeText(getContext(), "Login => Create Account", Toast.LENGTH_SHORT).show();

                fm.beginTransaction()
                        .replace(R.id.fragment_container, new CreateAccFragment(),"create_acc")
                        .setReorderingAllowed(true)
                        .addToBackStack("create_acc")
                        .commit();
                break;

            //SUMMARY
            //launch forgot password fragment
            case R.id.forgot_pass:
               // Toast.makeText(getContext(), "Login => Forgot Password", Toast.LENGTH_SHORT).show();
                fm.beginTransaction()
                        .replace(R.id.fragment_container, new ForgotPassFragment(), "forgot_pass")
                        .setReorderingAllowed(true)
                        .addToBackStack("forgot_pass")
                        .commit();
                break;
        }
    }
}

