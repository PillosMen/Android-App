package com.example.adm.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CutClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut);
    }

    public void lanzar(View view) {
        Intent i = new Intent(this, LoginActivity.class );
        startActivity(i);
    }
}
