package uk.ac.abertay.honsfirebase;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccFragment extends Fragment implements View.OnClickListener {

    private FragmentManager fm;
    private Button submit, forgot_pass, login;
    private EditText email_field, pass_field, conf_field;

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
    public void onClick(View view){
        switch(view.getId()) {

            //SUMMARY
            //get values entered by user and validate them
            //password values must match
            //output sanitised and valid values to Firebase and receive response on completion
            case R.id.create_acc_submit:
                Toast.makeText(getContext(), "Submit account creation details", Toast.LENGTH_SHORT).show();
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
}
