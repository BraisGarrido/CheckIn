package com.proyecto.checkin.ui;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.proyecto.checkin.R;

import java.util.HashMap;
import java.util.Map;

public class OptionsFragment extends Fragment {
    View root;
    Context context;
    Button registrar, add_imagen;
    EditText nombre, localidad;
    ImageView upl_imagen;
    ImageButton atras;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;

    private static final int COD_SEL_IMAGE=300;
    Intent intent;
    private String urlDescarga;

    public OptionsFragment() {
        // Required empty public constructor
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
        root= inflater.inflate(R.layout.fragment_options, container, false);
        setupNavigation(root);
        return root;
    }

    public void setupNavigation(View root){
        //Metodo para instanciar los elementos
        registrar=root.findViewById(R.id.ingresar_casa);
        add_imagen=root.findViewById(R.id.ingresar_logo);

        atras=root.findViewById(R.id.atras);

        nombre=root.findViewById(R.id.nombre_viv);
        localidad=root.findViewById(R.id.localidad_viv);

        upl_imagen=root.findViewById(R.id.upl_imagen);

        //Instanciamos la base de datos en firebase para poder insertar los datos de la vivienda
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance("https://checkin-52edb-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference=firebaseDatabase.getReference("vivienda");

        //Instanciamos el storage de firebase para poder guardar la imagen del logo
        storageReference= FirebaseStorage.getInstance("gs://checkin-52edb.appspot.com").getReference();

        /*-------------------------------------------------------EVENTOS ONCLICK------------------------------*/
        add_imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, COD_SEL_IMAGE);
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargaDatos();
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.homeFragment);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        // Se verifica si el resultado corresponde a la selección de una imagen
        if(requestCode==COD_SEL_IMAGE && resultCode==RESULT_OK){
            Uri uri=data.getData();

            StorageReference reference=storageReference.child("imagen").child(uri.getLastPathSegment());

            // Se sube el archivo de imagen a Firebase Storage
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(context, "Se subió correctamente", Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            urlDescarga=uri.toString();
                            Glide.with(OptionsFragment.this).load(urlDescarga).into(upl_imagen);
                        }
                    });
                }
            });
        }
    }

    // Se obtienen los valores de nombre y localidad ingresados por el usuario
    private void cargaDatos(){
        String s_nombre=nombre.getText().toString();
        String s_localidad=localidad.getText().toString();
        
        if(s_nombre.isEmpty() || s_localidad.isEmpty()){
            Toast.makeText(context, "Todos los campos deben estar cubiertos", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference viviendaRef=databaseReference.push();

            Map<String, Object> datos=new HashMap<>();
            datos.put("nombre", s_nombre);
            datos.put("localidad", s_localidad);
            datos.put("url_imagen", urlDescarga);
            
            viviendaRef.setValue(datos);
            Toast.makeText(context, "Los datos han sido insertados", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(root).navigate(R.id.homeFragment);
        }
    }
}