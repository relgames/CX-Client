package org.relgames.cxclient.service;

import android.util.Log;
import org.simpleframework.xml.core.Persister;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleg Poleshuk
 */
public class CxService {
    private Persister persister = new Persister();

    private static final String SITE_URL = "www.cxgomel.by";
    private static final String USERNAME = "relgames";
    private static final String PASSWORD = "fuckoff";

    private static final String TAG = "CxService";

    private static final Map<Class, String> URLS = Collections.unmodifiableMap(new HashMap<Class, String>() {{
        put(User.class, "mvc/user/me");
        put(GameList.class, "/mvc/games/open");
    }});

    public User getUserInfo() throws CxServiceException {
        return getDataFromServer(User.class, null);
    }

    public List<Game> getGameList() throws CxServiceException {
        return getDataFromServer(GameList.class, null).games;
    }

    private <T> T getDataFromServer(Class<T> clazz, Map<String, String> parameters) {
        try {
            StringBuilder url = new StringBuilder();
            url.append("http://").append(SITE_URL).append("/").append(URLS.get(clazz));

            Map<String, String> fullParameters = new HashMap<String, String>();
            if (parameters != null) {
                fullParameters.putAll(parameters);
            }
            fullParameters.put("userName", USERNAME);
            fullParameters.put("userPassword", PASSWORD);

            appendParameters(url, fullParameters);

            Log.d(TAG, url.toString());
            String result = get(url.toString());
            Log.d(TAG, result);

            return persister.read(clazz, result);
        } catch (Exception e) {
            throw new CxServiceException("Can't get user info", e);
        }
    }

    private void appendParameters(StringBuilder builder, Map<String, String> fullParameters) {
        if (fullParameters.isEmpty()) {
            return;
        }

        boolean first = true;
        for (Map.Entry<String, String> entry : fullParameters.entrySet()) {
            if (first) {
                builder.append("?");
                first = false;
            } else {
                builder.append("&");
            }
            builder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue()));
        }
    }

    private String get(String url) throws IOException {
        InputStream stream = null;
        try {
            stream = new URL(url).openStream();

            char[] buffer = new char[0x10000];
            StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(stream, "UTF-8");
            int read;
            do {
                read = in.read(buffer, 0, buffer.length);
                if (read > 0) {
                    out.append(buffer, 0, read);
                }
            } while (read >= 0);

            return out.toString();
        } finally {
            closeStream(stream);
        }


    }

    private void closeStream(InputStream stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException ignored) {
            }
        }
    }
}
