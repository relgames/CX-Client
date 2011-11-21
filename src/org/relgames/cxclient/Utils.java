package org.relgames.cxclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.*;

/**
 * @author Oleg Poleshuk
 */
public class Utils {
    private static final String TAG = "Utils";

    public static void error(Exception e, Context c) {
        Log.e(c.getClass().getSimpleName(), "Error", e);

        Toast toast = Toast.makeText(c, e.getLocalizedMessage(), Toast.LENGTH_LONG);
        toast.show();
    }

    public static String getBaseUrl(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String baseUrl = sp.getString("url", "");

        if (!(baseUrl.startsWith("http://") || baseUrl.startsWith("https://"))) {
            baseUrl = "http://" + baseUrl;
        }

        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        return baseUrl;
    }

    public static String streamToString(InputStream inputStream) {
        if (inputStream != null) {

            try {
                Writer writer = new StringWriter();
                char[] buffer = new char[1024];
                try {
                    Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    int n;
                    while ((n = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, n);
                    }
                } finally {
                    inputStream.close();
                }
                return writer.toString();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return "";
        }
    }
}
