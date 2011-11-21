package org.relgames.cxclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import org.relgames.cxclient.service.CxApplication;
import org.relgames.cxclient.service.CxService;
import org.relgames.cxclient.service.CxServiceException;
import org.relgames.cxclient.service.InGame;

import static org.relgames.cxclient.Utils.error;

/**
 * @author Oleg Poleshuk
 */
public class InGameActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    protected CxService getCxService() {
        return ((CxApplication) getApplication()).getCxService();
    }

    private String gameId;
    private String baseUrl;

    private String CSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CSS = Utils.streamToString(this.getResources().openRawResource(R.raw.cxgomel));

        setContentView(R.layout.in_game);

        gameId = getIntent().getStringExtra("gameId");
        baseUrl = Utils.getBaseUrl(this);

        Log.d(TAG, "gameId = " + gameId + "; baseUrl = " + baseUrl);

        refresh();
    }


    private void refresh() {
        try {
            InGame inGame = getCxService().inGame(gameId, null, null);
            WebView html = (WebView)findViewById(R.id.inGameHtml);

            Log.d(TAG + " levelHtml", inGame.levelHtml);
            String fullHtml = "<html><head><style>"+CSS+"</style></head><body>" + inGame.levelHtml + "</body></html>";
            html.loadDataWithBaseURL(baseUrl, fullHtml, "text/html", "UTF-8", null);
        } catch (CxServiceException e) {
            error(e, this);
        }
    }

    private void showStatistics() {
        Log.d(TAG, "Starting StatisticsActivity with gameId = " + gameId);

        Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra("gameId", gameId);
        startActivity(intent);
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
            case R.id.menu_statistics:
                showStatistics();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
