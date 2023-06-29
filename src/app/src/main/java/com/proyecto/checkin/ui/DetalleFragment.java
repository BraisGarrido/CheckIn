package com.proyecto.checkin.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;
import com.proyecto.checkin.R;
import com.proyecto.checkin.entidades.CheckIn;
import com.proyecto.checkin.entidades.Vivienda;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.ArrayList;

public class DetalleFragment extends Fragment {
    View root;
    ImageButton atras;
    Button finalizar;
    TextView detalle_nombre, detalle_apellido1, detalle_apellido2, detalle_nacimiento, detalle_sexo, detalle_documento, detalle_expedicion, detalle_nacionalidad, detalle_id, detalle_num_documento, detalle_fecha_actual, detalle_nombre_viv, detalle_localidad;
    ImageView logo;
    FirebaseDatabase firebaseDatabase;
    ArrayList<Vivienda> arrayViviendas;

    public DetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Obtenemos los datos del bundle para rellenar los TextView
        getParentFragmentManager().setFragmentResultListener("datos", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String Sdetalle_nombre=result.getString("nombre");
                String Sdetalle_apellido1=result.getString("apellido1");
                String Sdetalle_apellido2=result.getString("apellido2");
                String Sdetalle_nacimiento=result.getString("nacimiento");
                String Sdetalle_sexo=result.getString("sexo");
                String Sdetalle_documento=result.getString("tipoDocumento");
                String Sdetalle_num_documento=result.getString("numDocumento");
                String Sdetalle_expedicion=result.getString("expedicion");
                String Sdetalle_nacionalidad=result.getString("nacionalidad");
                String Sfecha_actual=result.getString("fechaHora");
                int id=result.getInt("id");

                detalle_nombre.setText(Sdetalle_nombre);
                detalle_apellido1.setText(Sdetalle_apellido1);
                detalle_apellido2.setText(Sdetalle_apellido2);
                detalle_nacimiento.setText("Nacimiento: "+Sdetalle_nacimiento);
                detalle_sexo.setText("Sexo: "+Sdetalle_sexo);
                detalle_documento.setText(Sdetalle_documento);
                detalle_num_documento.setText("Nº de documento: "+Sdetalle_num_documento);
                detalle_expedicion.setText("Expedición: "+Sdetalle_expedicion);
                detalle_nacionalidad.setText("Nacionalidad: "+Sdetalle_nacionalidad);
                detalle_id.setText("Parte de viajero Nº"+id);
                detalle_fecha_actual.setText("Fecha actual  "+Sfecha_actual);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root= inflater.inflate(R.layout.fragment_detalle, container, false);
        setupNavigation(root);
        return root;
    }

    public void setupNavigation(View root){
        //Se declaran los elementos
        atras=root.findViewById(R.id.atras);
        finalizar=root.findViewById(R.id.finalizar);

        detalle_nombre=root.findViewById(R.id.detalle_nombre);
        detalle_apellido1=root.findViewById(R.id.detalle_apellido1);
        detalle_apellido2=root.findViewById(R.id.detalle_apellido2);
        detalle_nacimiento=root.findViewById(R.id.detalle_nacimiento);
        detalle_sexo=root.findViewById(R.id.detalle_sexo);
        detalle_documento=root.findViewById(R.id.detalle_documento);
        detalle_expedicion=root.findViewById(R.id.detalle_expedicion);
        detalle_nacionalidad=root.findViewById(R.id.detalle_nacionalidad);
        detalle_id=root.findViewById(R.id.detalle_id);
        detalle_num_documento=root.findViewById(R.id.detalle_num_documento);
        detalle_fecha_actual=root.findViewById(R.id.fechaActual);
        detalle_nombre_viv=root.findViewById(R.id.detalle_nombre_viv);
        detalle_localidad=root.findViewById(R.id.detalle_localidad);

        arrayViviendas=new ArrayList<>();

        logo=root.findViewById(R.id.logo);

        //Instanciamos la base de datos en firebase para obtener los datos de la vivienda y rellenar los TextView
        firebaseDatabase=FirebaseDatabase.getInstance("https://checkin-52edb-default-rtdb.europe-west1.firebasedatabase.app/");
        firebaseDatabase.getReference("vivienda").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Vivienda vivienda = snapshot.getValue(Vivienda.class);
                    arrayViviendas.add(vivienda);

                    assert vivienda != null;
                    detalle_nombre_viv.setText("Nombre: "+vivienda.getNombre());
                    detalle_localidad.setText("Localidad: "+vivienda.getLocalidad());

                    Picasso.get().load(vivienda.getUrl_imagen()).into(logo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        /*-------------------------------------------------EVENTOS ONCLICK--------------------------------------------------------------*/
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.homeFragment);
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.homeFragment);
            }
        });
    }
}