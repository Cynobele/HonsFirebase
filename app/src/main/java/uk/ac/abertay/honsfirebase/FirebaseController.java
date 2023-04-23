package uk.ac.abertay.honsfirebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class FirebaseController {
    //handles uploading data to firebase realtime database

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase fb_db;
    private DatabaseReference db_ref;

    // required constructor
    public FirebaseController() {
        //connect to the realtime database at the url
        fb_db = FirebaseDatabase.getInstance("https://honsfirebase-default-rtdb.europe-west1.firebasedatabase.app");

    }

    public void writeScore(Map<Integer, Boolean> answers, String behaviour){
        //recieve score pack and write accordingly

        db_ref = fb_db.getReference().child("users");
        user = FirebaseAuth.getInstance().getCurrentUser();

        // nodes in database are separated by user emails (symbols are forbidden)
        // email is split so  : name@email.com -> email
        String user_email = user.getEmail();
        user_email = user_email.split("@")[0];
        // remove any symbols that are before the @
        user_email = user_email.replaceAll("[^a-zA-Z0-9]", "");

        //create a new entry under the correct quiz topic
        db_ref = fb_db.getReference("users/"+user_email+"/"+behaviour);
        db_ref.child(formatDate()).setValue(answers.values().toString());
    }

    private String formatDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd|MM|yyyy h|mm|ss a");
        return format.format(calendar.getTime());
    }
}
