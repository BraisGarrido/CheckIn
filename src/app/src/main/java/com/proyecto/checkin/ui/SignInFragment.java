package com.proyecto.checkin.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.proyecto.checkin.R;

public class SignInFragment extends Fragment {
    EditText signIn_email, signIn_pass, signIn_rep_pass;
    Button signIn;
    FirebaseAuth firebaseAuth;
    Context context;
    View root;

    public SignInFragment() {
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
        root= inflater.inflate(R.layout.fragment_sign_in, container, false);
        setupNavigation(root);
        return root;
    }

    //Este metodo se usa para instanciar los objetos y marcar la navegacion que hay entre los fragments
    public void setupNavigation(View root){
        signIn=root.findViewById(R.id.signIn);

        signIn_email=root.findViewById(R.id.signIn_email);
        signIn_pass=root.findViewById(R.id.signIn_pass);
        signIn_rep_pass=root.findViewById(R.id.rep_signIn_pass);

        firebaseAuth=FirebaseAuth.getInstance();

/*-----------------------------------EVENTOS DE PULSADO-----------------------------------------------------*/
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    public void signIn(){
        String email=signIn_email.getText().toString().trim();
        String password=signIn_pass.getText().toString().trim();
        String rep_password=signIn_rep_pass.getText().toString().trim();

        //Controlamos las escepciones
        if(email.isEmpty()){
            Toast.makeText(context, "Se debe ingresar un email", Toast.LENGTH_SHORT).show();
        }
        else if (password.isEmpty()){
            Toast.makeText(context, "Se debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
        }
        else if (rep_password.isEmpty()){
            Toast.makeText(context, "Se tiene que repetir la contraseña", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(rep_password)){
            Toast.makeText(context, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
        }
        else if(signIn_pass.length()<6){
            Toast.makeText(context, "La contraseña como mínimo deben ser 6 caracteres", Toast.LENGTH_SHORT).show();
        }
        else {
            //Si esta todo correcto se registra en firebase
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //Se crea un toast para indicar que todo fue correcto y se manda de neuvo a loginfragment
                        Toast.makeText(context, "Te has registrado con éxito", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(root).navigate(R.id.loginFragment);
                    }
                    else {
                        Toast.makeText(context, "Se ha producido un error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}