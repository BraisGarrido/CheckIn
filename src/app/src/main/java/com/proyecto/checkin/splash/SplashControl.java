package com.proyecto.checkin.splash;

import android.app.Application;
import android.os.SystemClock;

public class SplashControl extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Como a veces puede fallar firebase a la hora de registrarse el splashcontrol durara 1 segundo para que todo funcione correctamente
        SystemClock.sleep(1000);
    }
}
