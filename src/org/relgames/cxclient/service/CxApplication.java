package org.relgames.cxclient.service;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import org.relgames.cxclient.Utils;
import org.simpleframework.xml.convert.AnnotationStrategy;
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

    private Persister persister = new Persister(new AnnotationStrategy());

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
        put(GameInfoList.class, new RemoteService("mvc/games/open", false));
        put(Statistics.class, new RemoteService("mvc/statistics/{gameId}", false));
        put(InGame.class, new RemoteService("mvc/ingame/{gameId}", true));
    }});

    @Override
    public User getUserInfo() throws CxServiceException {
        return getDataFromServer(User.class, null);
    }

    @Override
    public List<GameInfo> getGameList() throws CxServiceException {
        return getDataFromServer(GameInfoList.class, null).gameInfos;
    }

    @Override
    public Statistics getStatistics(final String gameId) throws CxServiceException {
        return getDataFromServer(Statistics.class, new HashMap<String, String>(){{
            put("gameId", gameId);
        }});
    }

    @Override
    public InGame inGame(final String gameId, final String bonusId, final String key) throws CxServiceException {
        return getDataFromServer(InGame.class, new HashMap<String, String>(){{
            put("gameId", gameId);
            put("bonusId", bonusId);
            put("key", key);
        }});
    }

    private <T> T getDataFromServer(Class<T> clazz, Map<String, String> parameters) throws CxServiceException {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        final String username = sp.getString("username", "");
        final String password = sp.getString("password", "");
        final String siteUrl = Utils.getBaseUrl(this);

        try {
            StringBuilder url = new StringBuilder();
            url.append(siteUrl).append("/").append(SERVICES.get(clazz).url);

            Map<String, String> fullParameters = new HashMap<String, String>();
            if (parameters != null) {
                fullParameters.putAll(parameters);
            }

            if (SERVICES.get(clazz).needsAuthorization) {
                fullParameters.put("userName", username);
                fullParameters.put("userPassword", password);
            }

            applyParameters(url, fullParameters);

            Log.d(TAG_SERVICE, url.toString());
            String result = get(url.toString());
            Log.d(TAG_SERVICE, result);

            return persister.read(clazz, result, false);
        } catch (Exception e) {
            throw new CxServiceException("Communication error: " + e.getLocalizedMessage(), e);
        }
    }

    private void applyParameters(StringBuilder builder, Map<String, String> fullParameters) {
        if (fullParameters.isEmpty()) {
            return;
        }

        boolean first = true;
        for (Map.Entry<String, String> entry : fullParameters.entrySet()) {
            if (entry.getValue()!=null && entry.getValue().trim().length()>0) {
                String param = "{"+entry.getKey()+"}";
                int i = builder.indexOf(param);
                if (i >= 0) {
                    builder.replace(i, i+param.length(), entry.getValue());
                } else {
                    if (first) {
                        builder.append("?");
                        first = false;
                    } else {
                        builder.append("&");
                    }
                    builder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue()));
                }
            }
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
