package com.myapplication.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String URL = "https://jsonplaceholder.typicode.com/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textResponse);
        findViewById(R.id.my_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataFromServer(URL);
            }
        });
    }

    private void getDataFromServer(String url) {
        // url is the URL to download.
        AsyncHttpClient.getDefaultInstance().executeString(new AsyncHttpGet(url), new AsyncHttpClient.StringCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, final String result) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(result);
                    }
                });
            }
        });
    }
}
