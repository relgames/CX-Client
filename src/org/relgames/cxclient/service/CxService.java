package org.relgames.cxclient.service;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Oleg Poleshuk
 */
public class CxService {
    private Persister persister = new Persister();

    public User getUserInfo() throws CxServiceException {
        InputStream stream = null;
        try {
            URL url = new URL("http://www.cxgomel.by/mvc/user/me?userName=relgames&userPassword=fuckoff");
            stream = url.openStream();
            return persister.read(User.class, stream);
        } catch (Exception e) {
            throw new CxServiceException("Can't get user info", e);
        } finally {
            closeStream(stream);
        }
    }

    private void closeStream(InputStream stream) {
        if (stream!=null) {
            try {
                stream.close();
            } catch (IOException ignored) {
            }
        }
    }
}
