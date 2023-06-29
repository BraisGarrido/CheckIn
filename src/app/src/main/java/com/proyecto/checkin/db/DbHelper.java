package com.proyecto.checkin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="checkIn.db";
    private static final int DB_VERSION=1;
    public static final String TABLE_CHECKIN="checkIn";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Se crea la tabla donde se insertaran los campos de forma temporal
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_CHECKIN+"(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, " +
                "apellido1 TEXT NOT NULL, apellido2 TEXT NOT NULL, nacimiento TEXT NOT NULL, sexo TEXT NOT NULL, " +
                "tipo_documento TEXT NOT NULL, num_documento TEXT NOT NULL, expedicion TEXT NOT NULL, nacionalidad TEXT NOT NULL, fechaHora TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
