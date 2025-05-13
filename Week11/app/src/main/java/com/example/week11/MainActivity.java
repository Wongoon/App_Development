package com.example.week11;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tView = (TextView) findViewById(R.id.text);
    }

    public void onClick(View v) {
        if (isNetworkAvailable()) {
            EditText urlEdit = (EditText) findViewById(R.id.url);
            url = urlEdit.getText().toString();

            Thread downloadThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final String result = downloadUrl(url);

                        tView.post(new Runnable() {
                            @Override
                            public void run() {
                                tView.setText(result);
                                Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e) {
                        Log.d("Background Task", e.toString());
                    }
                }
            });
            downloadThread.start();
        }
        else {
            Toast.makeText(getBaseContext(), "Network is not Available", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        boolean available = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            available = true;
        }
        return available;
    }

    private String downloadUrl(String strUrl) throws IOException {
        String s = null;
        byte[] buffer = new byte[1000];
        InputStream iStream = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            iStream.read(buffer);
            s = new String(buffer);
        } catch (Exception e) {
            Log.d("Exception download", e.toString());
        } finally {
            if (iStream != null) {
                iStream.close();
            }
        }
        return s;
    }
}