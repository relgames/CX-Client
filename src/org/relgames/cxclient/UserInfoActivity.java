package org.relgames.cxclient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import org.relgames.cxclient.service.CxApplication;
import org.relgames.cxclient.service.CxService;
import org.relgames.cxclient.service.User;

import static org.relgames.cxclient.Utils.showError;

public class UserInfoActivity extends Activity {
    protected CxService getCxService() {
        return ((CxApplication)getApplication()).getCxService();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        try {
            User user = getCxService().getUserInfo();

            ((TextView)findViewById(R.id.userId)).setText(user.id);
            ((TextView)findViewById(R.id.userName)).setText(user.name);
            ((TextView)findViewById(R.id.email)).setText(user.email);
            ((TextView)findViewById(R.id.team)).setText(user.team!=null ? user.team.name : "");
        } catch (Exception e) {
            showError(e, this);
        }
    }

}
