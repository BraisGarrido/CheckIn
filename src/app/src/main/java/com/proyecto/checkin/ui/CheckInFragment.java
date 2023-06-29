package com.proyecto.checkin.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;
import com.proyecto.checkin.R;
import com.proyecto.checkin.adaptadores.ListaCheckInAdapter;
import com.proyecto.checkin.db.DbCheckIn;
import com.proyecto.checkin.db.DbHelper;
import com.proyecto.checkin.entidades.CheckIn;

import java.util.ArrayList;

public class CheckInFragment extends Fragment {
    RecyclerView recy_datos;
    View root;
    Context context;
    DbCheckIn dbCheckIn;
    ArrayList<CheckIn> listaArrayCheckin;
    ListaCheckInAdapter adapter;
    Button nuevo_check, finalizar;
    SQLiteDatabase db;
    DbHelper dbHelper;
    Cursor cursor;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fire_nombre, fire_apellido1, fire_apellido2, fire_nacimiento, fire_sexo, fire_tipoDocumento, fire_numDocumento, fire_expedicion, fire_nacionalidad, fire_fechaHora;
    int fire_id;
    ImageButton atras;
    Bundle bundle;

    public CheckInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_check_in, container, false);
        setupNavigation(root);
        return root;
    }

    public void setupNavigation(View root) {
        //En esta parte se declaran los elementos
        recy_datos = root.findViewById(R.id.recy_datos);

        nuevo_check = root.findViewById(R.id.nuevo_check);
        finalizar = root.findViewById(R.id.finalizar);

        atras = root.findViewById(R.id.atras);

        recy_datos.setLayoutManager(new LinearLayoutManager(context));
        dbCheckIn = new DbCheckIn(context);
        listaArrayCheckin = dbCheckIn.mostrarCheckIn();
        adapter = new ListaCheckInAdapter(listaArrayCheckin);
        recy_datos.setAdapter(adapter);

        /*----------------------------------------------ZONA ONCLICK----------------------------------------------------------*/
        nuevo_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.datosFragment);
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envioDatos();
                borrarDatos();
                Navigation.findNavController(root).navigate(R.id.homeFragment);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.datosFragment);
            }
        });

        //Si el usuario pincha en un elemento del recyclerview se envian los datos a otro fragmento para editarlos
        adapter.setOnClickListener(new ListaCheckInAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                bundle = new Bundle();
                bundle.putInt("id", listaArrayCheckin.get(position).getId());
                bundle.putString("nombre", listaArrayCheckin.get(position).getNombre());
                bundle.putString("apellido1", listaArrayCheckin.get(position).getApellido1());
                bundle.putString("apellido2", listaArrayCheckin.get(position).getApellido2());
                bundle.putString("nacimiento", listaArrayCheckin.get(position).getNacimiento());
                bundle.putString("sexo", listaArrayCheckin.get(position).getSexo());
                bundle.putString("documento", listaArrayCheckin.get(position).getTipo_documento());
                bundle.putString("num_documento", listaArrayCheckin.get(position).getNum_documento());
                bundle.putString("expedicion", listaArrayCheckin.get(position).getExpedicion());
                bundle.putString("nacionalidad", listaArrayCheckin.get(position).getNacionalidad());
                bundle.putString("fechaHora", listaArrayCheckin.get(position).getFechaHora());
                getParentFragmentManager().setFragmentResult("datos", bundle);
                Navigation.findNavController(root).navigate(R.id.editFragment);
            }
        });
    }

    //Se preparan los datos para enviar a la base de datos en firebase
    @SuppressLint({"Range", "RestrictedApi"})
    public void envioDatos() {
        //Seleccionamos todos los elementos para enviarlos
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM checkIn", null);

        //Instanciamos la base de datos en Firebase para indicar a donde enviar los datos
        firebaseDatabase = FirebaseDatabase.getInstance("https://checkin-52edb-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("checkIn");
        while (cursor.moveToNext()) {
            //Preparamos los datos y los enviamos
            fire_id = cursor.getInt(cursor.getColumnIndex("id"));
            fire_nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            fire_apellido1 = cursor.getString(cursor.getColumnIndex("apellido1"));
            fire_apellido2 = cursor.getString(cursor.getColumnIndex("apellido2"));
            fire_nacimiento = cursor.getString(cursor.getColumnIndex("nacimiento"));
            fire_sexo = cursor.getString(cursor.getColumnIndex("sexo"));
            fire_tipoDocumento = cursor.getString(cursor.getColumnIndex("tipo_documento"));
            fire_numDocumento = cursor.getString(cursor.getColumnIndex("num_documento"));
            fire_expedicion = cursor.getString(cursor.getColumnIndex("expedicion"));
            fire_nacionalidad = cursor.getString(cursor.getColumnIndex("nacionalidad"));
            fire_fechaHora = cursor.getString(cursor.getColumnIndex("fechaHora"));

            databaseReference.child("checkIn").push().setValue(new CheckIn(fire_id, fire_nombre, fire_apellido1, fire_apellido2, fire_nacimiento, fire_sexo, fire_tipoDocumento, fire_numDocumento, fire_expedicion, fire_nacionalidad, fire_fechaHora));
        }
    }

    //Borramos todos los datos de la tabla despues de enviarlos
    public void borrarDatos() {
        db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM checkIn");
    }
}
