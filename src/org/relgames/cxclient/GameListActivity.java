package org.relgames.cxclient;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.relgames.cxclient.service.CxApplication;
import org.relgames.cxclient.service.CxService;
import org.relgames.cxclient.service.Game;

import java.util.ArrayList;
import java.util.List;

import static org.relgames.cxclient.Utils.showError;

/**
 * @author Oleg Poleshuk
 */
public class GameListActivity extends ListActivity {
    protected CxService getCxService() {
        return ((CxApplication) getApplication()).getCxService();
    }

    private static final String TAG = "GameListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String url = sp.getString("url", "");

        if (url == null || url.trim().length() == 0) {
            showSettings();
        } else {
            Log.i(TAG, url);
            refresh();
        }
    }

    private void showSettings() {
        startActivityForResult(new Intent(this, SettingsActivity.class), 0);
    }

    private void showUserInfo() {
        startActivity(new Intent(this, UserInfoActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refresh();
    }

    private void refresh() {
        try {
            final List<Game> games = getCxService().getGameList();

            List<String> gameNames = new ArrayList<String>(games.size());
            for (Game game : games) {
                gameNames.add(game.name);
            }

            setListAdapter(new ArrayAdapter<String>(this, R.layout.game_item, gameNames));

            ListView lv = getListView();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(GameListActivity.this, GameInfoActivity.class);

                    Game game = games.get((int) id);
                    Log.d(TAG, game != null ? game.toString() : "null");

                    intent.putExtra("game", game);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            showError(e, this);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                refresh();
                return true;
            case R.id.menu_settings:
                showSettings();
                return true;
            case R.id.menu_user_info:
                showUserInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
