package jalagus.msgsender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by jalagus on 06/04/14.
 */
public class Settings extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        EditText editHost = (EditText) findViewById(R.id.setting_ip);
        EditText editPort = (EditText) findViewById(R.id.setting_port);

        Intent intent = getIntent();

        editHost.setText(intent.getStringExtra(MyActivity.EXTRA_MESSAGE_IP));
        editPort.setText(intent.getStringExtra(MyActivity.EXTRA_MESSAGE_PORT));
    }

    public void saveSettings(View view) {
        EditText editHost = (EditText) findViewById(R.id.setting_ip);
        EditText editPort = (EditText) findViewById(R.id.setting_port);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(MyActivity.EXTRA_MESSAGE_IP, editHost.getText().toString());
        resultIntent.putExtra(MyActivity.EXTRA_MESSAGE_PORT, editPort.getText().toString());

        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}