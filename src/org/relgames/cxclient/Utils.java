package org.relgames.cxclient;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Oleg Poleshuk
 */
public class Utils {
    public static void showError(Exception e, Context c) {
        Toast toast = Toast.makeText(c, e.getLocalizedMessage(), Toast.LENGTH_LONG);
        toast.show();
    }
}
