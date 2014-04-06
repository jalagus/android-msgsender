package jalagus.msgsender;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.PrintWriter;
import java.net.Socket;

public class MyActivity extends Activity {

    private TextView contentView;

    private String hostIp = "88.192.46.88";
    private int hostPort = 8080;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        contentView = (TextView) findViewById(R.id.content_view);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_name);
        String message = editText.getText().toString();

        String stringUrl = editText.getText().toString();
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(stringUrl);
            editText.setText("");
        } else {
            contentView.setText("No network connection available.");
        }
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... msg) {

            // params comes from the execute() call: params[0] is the url.
            try {
                sendMessage(msg[0]);
                return "Message sent";
            } catch (Exception e) {
                return "Unable to send message";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            contentView.setText(result);
        }

        private void sendMessage(String message) throws Exception {
            Socket socket = null;

            socket = new Socket(hostIp, hostPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
            socket.close();

        }
    }
}
