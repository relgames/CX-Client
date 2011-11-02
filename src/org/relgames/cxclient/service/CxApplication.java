package org.relgames.cxclient.service;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main class that holds all services and global resources
 * @author Oleg Poleshuk
 */
public class CxApplication extends Application implements CxService {
    public CxService getCxService() {
        return this;
    }

    private Persister persister = new Persister();

    private static final String TAG_SERVICE = "CxService";

    private static class RemoteService {
        public String url;
        public boolean needsAuthorization;

        private RemoteService(String url, boolean needsAuthorization) {
            this.url = url;
            this.needsAuthorization = needsAuthorization;
        }
    }

    private static final Map<Class, RemoteService> SERVICES = Collections.unmodifiableMap(new HashMap<Class, RemoteService>() {{
        put(User.class, new RemoteService("mvc/user/me", true));
        put(GameList.class, new RemoteService("mvc/games/open", false));
    }});

    @Override
    public User getUserInfo() throws CxServiceException {
        return getDataFromServer(User.class, null);
    }

    @Override
    public List<Game> getGameList() throws CxServiceException {
        return getDataFromServer(GameList.class, null).games;
    }

    private <T> T getDataFromServer(Class<T> clazz, Map<String, String> parameters) throws CxServiceException {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        final String username = sp.getString("username", "");
        final String password = sp.getString("password", "");
        final String siteUrl = sp.getString("url", "");

        try {
            StringBuilder url = new StringBuilder();
            url.append("http://").append(siteUrl).append("/").append(SERVICES.get(clazz).url);

            Map<String, String> fullParameters = new HashMap<String, String>();
            if (parameters != null) {
                fullParameters.putAll(parameters);
            }

            if (SERVICES.get(clazz).needsAuthorization) {
                fullParameters.put("userName", username);
                fullParameters.put("userPassword", password);
            }

            appendParameters(url, fullParameters);

            Log.d(TAG_SERVICE, url.toString());
            String result = get(url.toString());
            Log.d(TAG_SERVICE, result);

            return persister.read(clazz, result, false);
        } catch (Exception e) {
            throw new CxServiceException("Communication error: " + e.getLocalizedMessage(), e);
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
