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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassFragment extends Fragment implements View.OnClickListener {

    private FragmentManager fm;
    private Button submit, create_acc, login;
    private EditText email_field;
    private FirebaseAuth auth;

    public ForgotPassFragment(){/*empty constructor*/}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated_view = inflater.inflate(R.layout.forgot_pass_frag, container, false);

        fm = getFragmentManager(); //gets the fragment manager of the parent activity
        submit = inflated_view.findViewById(R.id.submit);
        create_acc = inflated_view.findViewById(R.id.create_acc);
        login = inflated_view.findViewById(R.id.login);
        email_field = inflated_view.findViewById(R.id.email_field);

        //assign listeners
        submit.setOnClickListener(this);
        create_acc.setOnClickListener(this);
        login.setOnClickListener(this);

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

    //SUMMARY
    //retrieves email value entered into text field by user
    //sanitisies user input
    //sends password reset link if email meets criteria
    private String getEmailForPasswordReset(){

        Editable input = email_field.getText(); //get user input
        if(input != null && input.length() > 0){
            //user entered a value
            String sanitised = input.toString();
            return sanitised;
        }else{
            Toast.makeText(getContext(), "Email field is empty!", Toast.LENGTH_SHORT).show();
        }

        return null;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            //SUMMARY
            //retrieve email from password
            case R.id.submit:

                String email = getEmailForPasswordReset();
                if(email != null){
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "Password reset link has been sent!\nPlease check your spam folder!", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getContext(), "ERROR!\n"+task.getException(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                break;

            //SUMMARY
            //redirect to create account fragment view
            case R.id.create_acc:
                Toast.makeText(getContext(), "ForgotPass => CreateAcc", Toast.LENGTH_SHORT).show();
                fm.beginTransaction()
                        .replace(R.id.fragment_container, new CreateAccFragment(), "create_acc")
                        .setReorderingAllowed(true)
                        .addToBackStack("create_acc")
                        .commit();
                break;

            //SUMMARY
            //redirect to login fragment view
            case R.id.login:
                Toast.makeText(getContext(), "ForgotPass => Login", Toast.LENGTH_SHORT).show();
                fm.beginTransaction()
                        .replace(R.id.fragment_container, new LoginFragment(), "login")
                        .setReorderingAllowed(true)
                        .addToBackStack("login")
                        .commit();
                break;

        }
    }
}
