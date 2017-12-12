package com.example.adm.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class CutClass extends AppCompatActivity {

    ProgressBar progreso;
    int miProgreso = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut);

        progreso = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(myThread).start();
    }
    private Runnable myThread = new Runnable(){

        public void run() {
            while (miProgreso<1){
                try{
                    myHandle.sendMessage(myHandle.obtainMessage());
                    Thread.sleep(3000);
                    lanzar(null);
                } catch(Throwable t){}
            }
        }

        Handler myHandle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                miProgreso++;
                progreso.setProgress(miProgreso);
            }
        };

    };


        public void lanzar(View view) {
        Intent i = new Intent(this, LoginActivity.class );
        startActivity(i);
    }
}
