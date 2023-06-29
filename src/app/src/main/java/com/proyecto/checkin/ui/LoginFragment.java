package com.proyecto.checkin.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
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


public class LoginFragment extends Fragment {
    Button signin, login;
    View root;
    EditText login_email, login_pass;
    FirebaseAuth firebaseAuth;
    Context context;
    Bundle bundle;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        root= inflater.inflate(R.layout.fragment_login, container, false);
        setupNavigation(root);

        //Recoge el email y contraseña guardada en sharedpreferences para autorrellenar los campso la proxima vez que vuelvas iniciar la sesion
        sharedPreferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");

        // Establecer los valores guardados en los EditText correspondientes
        login_email.setText(savedEmail);
        login_pass.setText(savedPassword);

        return root;
    }

    //Este metodo lo uso para instanciar todos los elementos y dirigir el flujo que hay entre los fragmentos
    private void setupNavigation(View root){
        signin=root.findViewById(R.id.login_signIn);
        login=root.findViewById(R.id.login);

        login_email=root.findViewById(R.id.login_email);
        login_pass=root.findViewById(R.id.login_pass);

        firebaseAuth=FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.signInFragment);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        String email=login_email.getText().toString().trim();
        String password=login_pass.getText().toString().trim();

        //Controlamos que todos los campos estan cubiertos
        if(email.isEmpty()){
            Toast.makeText(context, "Se debe ingresar un email", Toast.LENGTH_SHORT).show();
        }
        else if (password.isEmpty()){
            Toast.makeText(context, "Se debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
        } else {
            // Guardar los valores en SharedPreferences
            sharedPreferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Si el usuario esta en la base de datos lo enviamos a homeActivity
                    if(task.isSuccessful()){
                        //Cogemos todo lo que este antes de la @ para usarlo como nombre de usuario
                        int pos=email.indexOf('@');
                        String usuario=email.substring(0, pos);
                        Navigation.findNavController(root).navigate(R.id.homeFragment);
                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show();
                        bundle=new Bundle();
                        bundle.putString("usuario", usuario);
                        getParentFragmentManager().setFragmentResult("llave", bundle);
                    }
                    else {
                        Toast.makeText(context, "El correo o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}