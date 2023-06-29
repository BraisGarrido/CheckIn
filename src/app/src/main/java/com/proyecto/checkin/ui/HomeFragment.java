package com.proyecto.checkin.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.checkin.R;


public class HomeFragment extends Fragment {
    TextView bienvenida;
    View root;
    Button checkIn, registros;
    ImageButton cerrar_sesion, configuracion;
    FirebaseAuth firebaseAuth;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo para imprimir el string cogido en el bundle de loginFragment
        getParentFragmentManager().setFragmentResultListener("llave", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String usuario=result.getString("usuario");
                bienvenida.setText("Bienvenido, "+usuario);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_home, container, false);
        setupNavigation(root);
        return root;
    }

    //metodo para navegar entre fragments e instanciar objetos
    public void setupNavigation(View root){
        bienvenida=root.findViewById(R.id.bienvenida);

        firebaseAuth=FirebaseAuth.getInstance();

        configuracion=root.findViewById(R.id.configuracion);

        checkIn=root.findViewById(R.id.checkIn);
        registros=root.findViewById(R.id.registros);
        cerrar_sesion=root.findViewById(R.id.cerrar_sesion);

        /*-------------------------------------EVENTOS ONCLICK-------------------------------------------------------*/
        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.datosFragment);
            }
        });

        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.optionsFragment);
            }
        });

        registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.consultaFragment);
            }
        });

        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hacemos signOut en firebase auth al pulsar el boton
                firebaseAuth.signOut();
                Toast.makeText(getContext(), "Hasta luego", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(root).navigate(R.id.loginFragment);
            }
        });
    }
}