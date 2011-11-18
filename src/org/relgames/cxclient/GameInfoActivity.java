package org.relgames.cxclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.relgames.cxclient.service.GameInfo;

import java.util.Date;

/**
 * @author Oleg Poleshuk
 */
public class GameInfoActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_info);

        final GameInfo gameInfo = (GameInfo) getIntent().getSerializableExtra("gameInfo");
        Log.d(TAG, gameInfo.toString());

        ((TextView) findViewById(R.id.gameName)).setText(gameInfo.name);
        ((TextView) findViewById(R.id.gameNumber)).setText(gameInfo.number);
        ((TextView) findViewById(R.id.gamePrice)).setText(gameInfo.price);
        ((TextView) findViewById(R.id.gameStart)).setText(new Date(gameInfo.dateStart).toLocaleString());
        ((TextView) findViewById(R.id.gameStop)).setText(new Date(gameInfo.dateStop).toLocaleString());
        ((TextView) findViewById(R.id.gameType)).setText(gameInfo.type);
        ((TextView) findViewById(R.id.gameZone)).setText(gameInfo.zone);

        final Button button = (Button) findViewById(R.id.startGame);
        button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 Intent intent = new Intent(GameInfoActivity.this, InGameActivity.class);
                 Log.d(TAG, "Starting InGameActivity for gameId " + gameInfo.id);
                 intent.putExtra("gameId", gameInfo.id);
                 startActivity(intent);
             }
         });
    }

    public void startGame(View view) {

    }
}
