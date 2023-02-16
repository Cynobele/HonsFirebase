package uk.ac.abertay.honsfirebase;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String[] permissions = {
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE
    };

    ConnectivityManager conman;
    private boolean connected = false; //web connectivity status - false default

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
        if(!Utils.checkAllPermissions(this, permissions)){ //true if all permissions are granted
            requestPermissions(permissions, 0);
        }

        //initialise network connectivity
        conman = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest net_req = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build();
        conman.registerNetworkCallback(net_req, net_callback);

        //check if a network connection has been established before initialising firebase
        connected = isNetworkAvailable();
        if(connected) {
            FirebaseApp.initializeApp(this);
            auth = FirebaseAuth.getInstance();

        }else{
            Toast.makeText(getApplicationContext(), "No internet available, cannot connect to firebase!", Toast.LENGTH_SHORT).show();
        }

        root = findViewById(R.id.constraint_root);
        container = findViewById(R.id.fragment_container); //fragments will be displayed in this frame

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


    //SUMMARY
    //checks if a network is connected to
    //checks if the network has ethernet, cell or wifi capability
    //has deprecated value for older versions
    //returns bool value
    private Boolean isNetworkAvailable() {
        conman = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network nw = conman.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities net_capa = conman.getNetworkCapabilities(nw);
            return net_capa != null && (net_capa.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || net_capa.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || net_capa.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
        }
        else { //deprecated function for older android versions
            NetworkInfo nwInfo = conman.getActiveNetworkInfo();
            return nwInfo != null && nwInfo.isConnected();
        }
    }

    // NETWORK CALLBACK VARIABLE
    // DEFINED HERE - KEEPS TOP OF DOCUMENT TIDY
    ConnectivityManager.NetworkCallback net_callback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onLost(Network network) {
            /* report the loss to the user and disable the functionality that depends on it */
            Toast.makeText(getApplicationContext(), "LOST CONNECTION TO NETWORK!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLosing(Network network, int max_ms) {
            /*report the potential loss to the user */
            Toast.makeText(getApplicationContext(), "LOSING CONNECTION!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUnavailable() {
            /*report the loss to the user and disable the functionality that depends on it */
            Toast.makeText(getApplicationContext(), "NETWORK UNAVAILABLE!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAvailable(Network network) {
            /* report the new connection and resume enable the network functionality */
            Toast.makeText(getApplicationContext(), "CONNECTED TO NETWORK!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCapabilitiesChanged(Network network, NetworkCapabilities net_capa) {
            /* report the change in capabilities and adjust the app behaviour accordingly */
        }
    };

    @Override
    protected void onDestroy() {
        conman.unregisterNetworkCallback(net_callback);
        super.onDestroy();
    }
}
