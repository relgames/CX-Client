package org.relgames.cxclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.relgames.cxclient.service.Game;

import java.util.Date;

/**
 * @author Oleg Poleshuk
 */
public class GameInfoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_info);

        Game game = (Game)getIntent().getSerializableExtra("game");
        assert game != null;

        ((TextView)findViewById(R.id.gameName)).setText(game.name);
        ((TextView)findViewById(R.id.gameNumber)).setText(game.number);
        ((TextView)findViewById(R.id.gamePrice)).setText(game.price);
        ((TextView)findViewById(R.id.gameStart)).setText(new Date(game.dateStart).toLocaleString());
        ((TextView)findViewById(R.id.gameStop)).setText(new Date(game.dateStop).toLocaleString());
        ((TextView)findViewById(R.id.gameType)).setText(game.type);
        ((TextView)findViewById(R.id.gameZone)).setText(game.zone);

    }
}
