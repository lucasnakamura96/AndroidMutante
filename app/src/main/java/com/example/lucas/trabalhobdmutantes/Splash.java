package com.example.lucas.trabalhobdmutantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class Splash extends Activity implements Runnable {
    private final int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(this, DELAY);
    }

    @Override
    public void run(){
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

}
