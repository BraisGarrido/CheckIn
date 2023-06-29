package com.proyecto.checkin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Al usar fragments como pantallas el mainactivity solo esta para controlar el flujo de los distintos elementos. Por lo que
        //aqui no es necesario hacer nada
    }
}