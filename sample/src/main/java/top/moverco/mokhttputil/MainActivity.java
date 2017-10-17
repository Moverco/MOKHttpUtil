package top.moverco.mokhttputil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import top.moverco.mokhttputil.httpclient_demo.HttpClientDemo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClientDemo.executeHttpClient();
            }
        }).start();
    }
}
