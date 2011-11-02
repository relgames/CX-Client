package org.relgames.cxclient;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.relgames.cxclient.service.CxService;
import org.relgames.cxclient.service.Game;

import java.util.ArrayList;
import java.util.List;

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
                Log.d(TAG, game!=null? game.toString() : "null");

                intent.putExtra("game", game);
                startActivity(intent);
            }
        });
    }
}
