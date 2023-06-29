package com.proyecto.checkin.ui;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.proyecto.checkin.R;
import com.proyecto.checkin.datepicker.DatePickerFragment;
import com.proyecto.checkin.db.DbCheckIn;
import com.proyecto.checkin.entidades.CheckIn;
import com.proyecto.checkin.verificador.VerificarDni;

public class EditFragment extends Fragment {
    View root;
    int id;
    EditText edit_nombre, edit_apellido1, edit_apellido2, edit_nacimiento, edit_sexo, edit_tipo_documento, edit_num_documento, edit_expedicion, edit_nacionalidad;
    Button editar;
    Boolean correcto;
    DatePickerFragment datePickerFragment;
    DbCheckIn dbCheckIn;
    String s_edit_fechaHora, selectedDate, nuevoNombre, nuevoApellido1, nuevoApellido2, nuevoNacimiento, nuevoSexo, nuevoTipoDocumento, nuevoNumDocumento, nuevoExpedicion, nuevoNacionalidad;
    Context context;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context=getActivity();

        //Se recogen los datos del bundle para rellenar los editText
        getParentFragmentManager().setFragmentResultListener("datos", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                id=result.getInt("id");
                String s_edit_nombre=result.getString("nombre");
                String s_edit_apellido1=result.getString("apellido1");
                String s_edit_apellido2=result.getString("apellido2");
                String s_edit_nacimiento=result.getString("nacimiento");
                String s_edit_sexo=result.getString("sexo");
                String s_edit_documento=result.getString("documento");
                String s_edit_num_documento=result.getString("num_documento");
                String s_edit_expedicion=result.getString("expedicion");
                String s_edit_nacionalidad=result.getString("nacionalidad");
                s_edit_fechaHora=result.getString("fechaHora");

                edit_nombre.setText(s_edit_nombre);
                edit_apellido1.setText(s_edit_apellido1);
                edit_apellido2.setText(s_edit_apellido2);
                edit_nacimiento.setText(s_edit_nacimiento);
                edit_sexo.setText(s_edit_sexo);
                edit_tipo_documento.setText(s_edit_documento);
                edit_num_documento.setText(s_edit_num_documento);
                edit_expedicion.setText(s_edit_expedicion);
                edit_nacionalidad.setText(s_edit_nacionalidad);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_edit, container, false);
        setupNavigation(root);
        return root;
    }

    public void setupNavigation(View root){
        //Metodo para instanciar todos los elementos
        edit_nombre=root.findViewById(R.id.edit_nombre);
        edit_apellido1=root.findViewById(R.id.edit_apellido1);
        edit_apellido2=root.findViewById(R.id.edit_apellido2);
        edit_nacimiento=root.findViewById(R.id.edit_nacimiento);
        edit_sexo=root.findViewById(R.id.edit_sexo);
        edit_tipo_documento=root.findViewById(R.id.edit_tipo_documento);
        edit_num_documento=root.findViewById(R.id.edit_num_documento);
        edit_expedicion=root.findViewById(R.id.edit_expedicion);
        edit_nacionalidad=root.findViewById(R.id.edit_nacionalidad);

        editar=root.findViewById(R.id.editar);

        dbCheckIn=new DbCheckIn(context);

        correcto=false;

        /*-----------------------------------------EVENTOS ONCLICK-----------------------------------------------------------*/
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar();
            }
        });

        edit_nacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(edit_nacimiento);
            }
        });

        edit_expedicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(edit_expedicion);
            }
        });
    }

    public void editar(){
        //Instanciamos dbcheckin para enviar los datos
        dbCheckIn = new DbCheckIn(context);

        //Preparamos los datos para que sean comprobados y actualizado en la base de datos
        nuevoNombre = edit_nombre.getText().toString();
        nuevoApellido1 = edit_apellido1.getText().toString();
        nuevoApellido2 = edit_apellido2.getText().toString();
        nuevoNacimiento = edit_nacimiento.getText().toString();
        nuevoSexo = edit_sexo.getText().toString();
        nuevoTipoDocumento = edit_tipo_documento.getText().toString();
        nuevoNumDocumento = edit_num_documento.getText().toString();
        nuevoExpedicion = edit_expedicion.getText().toString();
        nuevoNacionalidad = edit_nacionalidad.getText().toString();

        int resultado=dbCheckIn.editarCheckin(id, nuevoNombre, nuevoApellido1, nuevoApellido2, nuevoNacimiento, nuevoSexo, nuevoTipoDocumento,
                nuevoNumDocumento, nuevoExpedicion, nuevoNacionalidad, s_edit_fechaHora);

        if(resultado>0){
            Toast.makeText(context, "REGISTRO ACTUALIZADO", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(root).navigate(R.id.checkInFragment);
        }
    }

    //Metodo para que la fecha seleccionada de los editText este en formato dd/mm/aaaa
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
}