package com.proyecto.checkin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Aqui creamos un intent para que una vez acabado el splashactivity se pase automaticamente a la primera pantalla
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}