package org.relgames.cxclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Oleg Poleshuk
 */
public class Utils {
    public static void error(Exception e, Context c) {
        Log.e(c.getClass().getSimpleName(), "Error", e);

        Toast toast = Toast.makeText(c, e.getLocalizedMessage(), Toast.LENGTH_LONG);
        toast.show();
    }

    public static String getBaseUrl(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String baseUrl = sp.getString("url", "");

        if (! (baseUrl.startsWith("http://") || baseUrl.startsWith("https://")) ) {
            baseUrl = "http://" + baseUrl;
        }

        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length()-1);
        }

        return baseUrl;
    }
}
