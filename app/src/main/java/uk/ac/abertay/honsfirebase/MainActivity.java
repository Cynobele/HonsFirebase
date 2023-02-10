package uk.ac.abertay.honsfirebase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FrameLayout container;
    private FragmentManager fm = getSupportFragmentManager();
    private Fragment login_frag = new LoginFragment();
    private Fragment create_acc_frag = new CreateAccFragment();

    FirebaseAuth auth;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        auth = FirebaseAuth.getInstance();
        root = findViewById(R.id.constraint_root);

        //fragments will be displayed in this frame
        container = findViewById(R.id.fragment_container);

    }

    @Override
    public void onStart() {
        super.onStart();

        Fragment vis = getVisibleFrag();
        if(vis == null){
            //no fragment has been added yet - this is the initial transaction
            fm.beginTransaction()
                    .add(R.id.fragment_container, login_frag, "login")
                    .setReorderingAllowed(true)
                    .addToBackStack("login")
                    .commit();
        }else{

            switch (vis.getTag()) {

                case "login":
                    //the login frag is already visible
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, login_frag, "login")
                            .setReorderingAllowed(true)
                            .addToBackStack("login")
                            .commit();
                    break;

                case "create_acc":
                    //refresh create_account view on app restart
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, create_acc_frag, "login")
                            .setReorderingAllowed(true)
                            .addToBackStack("create_acc")
                            .commit();
                    break;
            }
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

    @Override
    public void onClick(View view) {
    }
}
