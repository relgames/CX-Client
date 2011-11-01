package org.relgames.cxclient;

import android.app.Application;
import org.relgames.cxclient.service.CxService;

/**
 * Main class that holds all services and global resources
 * @author Oleg Poleshuk
 */
public class CxApplication extends Application {
    private CxService cxService;

    @Override
    public void onCreate() {
        super.onCreate();
        cxService = new CxService();
    }

    public CxService getCxService() {
        return cxService;
    }
}
