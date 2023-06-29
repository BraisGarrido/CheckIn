package com.proyecto.checkin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.proyecto.checkin.entidades.CheckIn;
import com.proyecto.checkin.verificador.VerificacionPermisoResidencia;
import com.proyecto.checkin.verificador.VerificarDni;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DbCheckIn extends DbHelper{
    Context context;
    SQLiteDatabase db;
    ContentValues values;
    DbHelper dbHelper;
    ArrayList<CheckIn> listaCheckin;
    CheckIn checkIn;
    Cursor cursor;

    public DbCheckIn(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    //Metodo para insertar los datos en la tabla temporal
    public long insertarCheckIn(String nombre, String apellido1, String apellido2, String nacimiento,
                             String sexo, String tipo_documento, String num_documento, String expedicion, String nacionalidad, String fechaHora){
        long id = 0;

        try {
            // Se obtiene una instancia de la base de datos en modo escritura
            dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();

            //Se comprueban algunos campos antes de insertar
            if (nombre.isEmpty() || apellido1.isEmpty() || nacimiento.isEmpty() ||
                    sexo.isEmpty() || tipo_documento.isEmpty() || num_documento.isEmpty() || expedicion.isEmpty() ||
                    nacionalidad.isEmpty()){
                Toast.makeText(context, "Todos los campos deben estar cubiertos", Toast.LENGTH_SHORT).show();
            }

            else if (tipo_documento.equals("Documento nacional de identidad") && !VerificarDni.validarDNI(num_documento)) {
                Toast.makeText(context, "DNI incorrecto", Toast.LENGTH_SHORT).show();
            }

            else if(tipo_documento.equals("Permiso de conducir") && !VerificarDni.validarDNI(num_documento)){
                Toast.makeText(context, "Permiso de conducir incorrecto", Toast.LENGTH_SHORT).show();
            }

            else if(tipo_documento.equals("Permiso de residencia español") && !VerificacionPermisoResidencia.validarNumeroResidencia(num_documento)){
                Toast.makeText(context, "Permiso de residencia española incorrecto", Toast.LENGTH_SHORT).show();
            }
            else {
                // Se crean los valores a insertar
                values = new ContentValues();
                values.put("nombre", nombre);
                values.put("apellido1", apellido1);
                values.put("apellido2", apellido2);
                values.put("nacimiento", nacimiento);
                values.put("sexo", sexo);
                values.put("tipo_documento", tipo_documento);
                values.put("num_documento", num_documento);
                values.put("expedicion", expedicion);
                values.put("nacionalidad", nacionalidad);
                values.put("fechaHora", fechaHora);

                // Se inserta la fila en la tabla "checkIn" y se obtiene el ID generado
                id = db.insert(TABLE_CHECKIN, null, values);
            }
        } catch (Exception ex) {
            ex.toString();
        } finally {
            db.close();
        }
        return id;
    }

    // Método para mostrar los datos de check-in
    public ArrayList<CheckIn> mostrarCheckIn(){
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();

        listaCheckin=new ArrayList<>();
        checkIn=null;
        cursor=null;

        cursor=db.rawQuery("SELECT * FROM "+TABLE_CHECKIN, null);

        if(cursor.moveToFirst()){
            do{
                checkIn=new CheckIn();
                checkIn.setId(cursor.getInt(0));
                checkIn.setNombre(cursor.getString(1));
                checkIn.setApellido1(cursor.getString(2));
                checkIn.setApellido2(cursor.getString(3));
                checkIn.setNacimiento(cursor.getString(4));
                checkIn.setSexo(cursor.getString(5));
                checkIn.setTipo_documento(cursor.getString(6));
                checkIn.setNum_documento(cursor.getString(7));
                checkIn.setExpedicion(cursor.getString(8));
                checkIn.setNacionalidad(cursor.getString(9));
                checkIn.setFechaHora(cursor.getString(10));
                listaCheckin.add(checkIn);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaCheckin;
    }

    // Método para editar un check-in existente
    public int editarCheckin(int id, String nuevoNombre, String nuevoApellido1, String nuevoApellido2,
                             String nuevoNacimiento, String nuevoSexo, String nuevoDocumento,
                             String nuevoNumDocumento, String nuevoExpedicion, String nuevoNacionalidad,
                             String s_edit_fechaHora) {

        int resultado = 0;

        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();

        try {
            //Se comprueban algunos campos antes de insertar
            if (nuevoNombre.isEmpty() || nuevoApellido1.isEmpty() || nuevoNacimiento.isEmpty() ||
                    nuevoSexo.isEmpty() || nuevoDocumento.isEmpty() || nuevoNumDocumento.isEmpty() || nuevoExpedicion.isEmpty() ||
                    nuevoNacionalidad.isEmpty()){
                Toast.makeText(context, "Todos los campos deben estar cubiertos", Toast.LENGTH_SHORT).show();
            }

            else if (nuevoDocumento.equals("Documento nacional de identidad") && !VerificarDni.validarDNI(nuevoNumDocumento)) {
                Toast.makeText(context, "DNI incorrecto", Toast.LENGTH_SHORT).show();
            }

            else if(nuevoDocumento.equals("Permiso de conducir") && !VerificarDni.validarDNI(nuevoNumDocumento)){
                Toast.makeText(context, "Permiso de conducir incorrecto", Toast.LENGTH_SHORT).show();
            }

            else if(nuevoDocumento.equals("Permiso de residencia español") && !VerificacionPermisoResidencia.validarNumeroResidencia(nuevoNumDocumento)){
                Toast.makeText(context, "Permiso de residencia española incorrecto", Toast.LENGTH_SHORT).show();
            }
            else if (!(nuevoDocumento.equals("Documento nacional de identidad") || nuevoDocumento.equals("Pasaporte") ||
                    nuevoDocumento.equals("Permiso de conducir") || nuevoDocumento.equals("Documento de identidad extranjero") ||
                    nuevoDocumento.equals("Permiso de residencia español") || nuevoDocumento.equals("Permiso de residencia extranjero"))){
                Toast.makeText(context, "El tipo de documento deben ser correcto", Toast.LENGTH_SHORT).show();
            }
            else {
                ContentValues valores = new ContentValues();
                valores.put("nombre", nuevoNombre);
                valores.put("apellido1", nuevoApellido1);
                valores.put("apellido2", nuevoApellido2);
                valores.put("nacimiento", nuevoNacimiento);
                valores.put("sexo", nuevoSexo);
                valores.put("tipo_documento", nuevoDocumento);
                valores.put("num_documento", nuevoNumDocumento);
                valores.put("expedicion", nuevoExpedicion);
                valores.put("nacionalidad", nuevoNacionalidad);
                valores.put("fechaHora", s_edit_fechaHora);

                String whereClause = "id = ?";
                String[] whereArgs = {String.valueOf(id)};

                int rowsAffected = db.update(TABLE_CHECKIN, valores, whereClause, whereArgs);
                db.close();

                if (rowsAffected > 0) {
                    resultado = rowsAffected;
                } else {
                    resultado = -1;
                }
            }
        } catch (Exception ex) {
            ex.toString();
            resultado = -1;
        } finally {
            db.close();
        }

        return resultado;
    }
}
