package org.relgames.cxclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import org.relgames.cxclient.service.CxApplication;
import org.relgames.cxclient.service.CxService;

/**
 * @author Oleg Poleshuk
 */
public class StatisticsActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    protected CxService getCxService() {
        return ((CxApplication) getApplication()).getCxService();
    }

    private WebView webView;
    private String statisticsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String gameId = getIntent().getStringExtra("gameId");
        Log.d(TAG, "gameId = " + gameId);


        statisticsUrl = Utils.getBaseUrl(this) + "/stat.jsp?id=" + gameId;

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(false);

        setContentView(webView);

        refresh();
    }

    private void refresh() {
        Log.d(TAG, "Loading " + statisticsUrl);
        webView.loadUrl(statisticsUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ingame_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
