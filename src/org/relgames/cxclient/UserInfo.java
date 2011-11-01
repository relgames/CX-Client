package org.relgames.cxclient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import org.relgames.cxclient.service.CxService;
import org.relgames.cxclient.service.User;

public class UserInfo extends Activity {
    private CxService getCxService() {
        return ((CxApplication)getApplication()).getCxService();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        User user = getCxService().getUserInfo();

        TextView userId = (TextView) findViewById(R.id.userId);
        userId.setText(user.id);

        TextView userName = (TextView) findViewById(R.id.userName);
        userName.setText(user.name);
    }

}
