package uk.ac.abertay.honsfirebase;

import android.content.Context;
import android.content.pm.PackageManager;

public class Utils {

    public static boolean checkAllPermissions(Context con, String[] permissions){
        Boolean result = false;
        for (String p:permissions) { // for each permission in the list
            result &= (con.checkSelfPermission(p) == PackageManager.PERMISSION_GRANTED);
        }
        return result;
    }
}
