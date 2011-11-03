package org.relgames.cxclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.relgames.cxclient.service.*;
import us.gorges.viewaclue.TwoDScrollView;

/**
 * @author Oleg Poleshuk
 */
public class StatisticsActivity extends Activity {
    protected CxService getCxService() {
        return ((CxApplication) getApplication()).getCxService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        String gameId = getIntent().getStringExtra("gameId");
        assert gameId != null;
*/

        String gameId = "2859003";

        Statistics statistics = getCxService().getStatistics(gameId);

        LinearLayout table = new LinearLayout(this);
        table.setOrientation(LinearLayout.HORIZONTAL);

        for (LevelColumn level : statistics.levels) {
            LinearLayout column = new LinearLayout(this);
            column.setOrientation(LinearLayout.VERTICAL);

            TextView header = new TextView(this);
            header.setText(level.name);

            column.addView(header);

            for (Score score : level.scores) {
                TextView cell = new TextView(this);
                cell.setGravity(Gravity.CENTER_HORIZONTAL);
                cell.setText(score.teamName);

                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                llp.setMargins(15, 15, 15, 15);

                cell.setLayoutParams(llp);

                column.addView(cell);
            }

            table.addView(column);
        }


        TwoDScrollView scrollView = new TwoDScrollView(this);
        scrollView.addView(table);

        setContentView(scrollView);
    }
}
