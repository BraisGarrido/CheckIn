package com.proyecto.checkin.ui;

import android.app.DatePickerDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proyecto.checkin.R;
import com.proyecto.checkin.datepicker.DatePickerFragment;
import com.proyecto.checkin.db.DbCheckIn;
import com.proyecto.checkin.db.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class DatosFragment extends Fragment {
    View root;
    EditText nacimiento, expedicion, nombre, apellido1, apellido2, n_documento, nacionalidad;
    Button checkIn, consultar;
    Spinner sexo, tipo_documento;
    DatePickerFragment datePickerFragment;
    String selectedDate, Snombre, Sapellido1, Sapellido2, Snacimiento, Ssexo, Stipo_documento, Snum_documento, Sexpedicion, Snacionalidad ,SfechaHora;
    DbCheckIn dbCheckIn;
    Context context;
    ImageButton atras;

    public DatosFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        root= inflater.inflate(R.layout.fragment_datos, container, false);
        setupNavigation(root);
        return root;
    }

    public void setupNavigation(View root){

        //Aqui instancio todos los elementos
        nacimiento=root.findViewById(R.id.nacimiento);
        expedicion=root.findViewById(R.id.expedicion);
        nombre=root.findViewById(R.id.nombre);
        apellido1=root.findViewById(R.id.apellido1);
        apellido2=root.findViewById(R.id.apellido2);
        n_documento=root.findViewById(R.id.num_documento);
        nacionalidad=root.findViewById(R.id.nacionalidad);

        checkIn=root.findViewById(R.id.check_in);
        consultar=root.findViewById(R.id.consultar);
        atras=root.findViewById(R.id.atras);

        sexo=root.findViewById(R.id.sexo);
        tipo_documento=root.findViewById(R.id.tipo_documento);

    /*--------------------------------------------------EVENTOS ONCLICK----------------------------------------------------------*/
        nacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(nacimiento);
            }
        });

        expedicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(expedicion);
            }
        });

        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.checkInFragment);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.homeFragment);
            }
        });
    }

    //Metodo para mostrar todas las fechas en formato dd/mm/aaaa
    private void showDatePickerDialog(EditText editText){
        datePickerFragment=DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedDate=dayOfMonth+" / "+(month+1)+" / "+year;
                editText.setText(selectedDate);
            }
        });
        datePickerFragment.show(requireActivity().getFragmentManager(), "datepicker");
    }

    //Metodo para coger los strings de los edittext e insertarlos en la base de datos
    private void insertar(){
        //Instanciamos dbcheckin para que los campos sean enviados e insertados en la base de datos SQLite
       dbCheckIn=new DbCheckIn(context);

       //Se obtiene la fecha del momento en el que se haga en checkin en forma dd/mm/aaaa
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaHoraActual = dateFormat.format(calendar.getTime());

       Snombre= nombre.getText().toString();
       Sapellido1=apellido1.getText().toString();
       Sapellido2=apellido2.getText().toString();
       Snacimiento=nacimiento.getText().toString();
       Ssexo=sexo.getSelectedItem().toString();
       Stipo_documento=tipo_documento.getSelectedItem().toString();
       Snum_documento=n_documento.getText().toString();
       Sexpedicion=expedicion.getText().toString();
       Snacionalidad=nacionalidad.getText().toString();
       SfechaHora=fechaHoraActual;

       //Se envian los datos y luego se borran
       long id=dbCheckIn.insertarCheckIn(Snombre, Sapellido1, Sapellido2, Snacimiento, Ssexo, Stipo_documento,
               Snum_documento, Sexpedicion, Snacionalidad, SfechaHora);
        if(id>0){
            Toast.makeText(context, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    //Metodo para limpiar los campos una vez registrados
    private void limpiar(){
        nacimiento.setText("");
        expedicion.setText("");
        nombre.setText("");
        apellido1.setText("");
        apellido2.setText("");
        n_documento.setText("");
        nacionalidad.setText("");
    }
}