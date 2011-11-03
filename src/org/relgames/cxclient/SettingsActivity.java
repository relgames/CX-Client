package org.relgames.cxclient;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author Oleg Poleshuk
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
