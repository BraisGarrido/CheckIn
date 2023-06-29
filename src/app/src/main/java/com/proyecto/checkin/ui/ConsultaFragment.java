package com.proyecto.checkin.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.checkin.R;
import com.proyecto.checkin.adaptadores.ListaCheckInAdapter;
import com.proyecto.checkin.entidades.CheckIn;

import java.util.ArrayList;
import java.util.List;

public class ConsultaFragment extends Fragment {
    View root;
    Button regresar;
    RecyclerView consultas;
    FirebaseDatabase firebaseDatabase;
    ArrayList<CheckIn> arrayCheckIns;
    ListaCheckInAdapter adapter;
    Context context;
    ImageButton atras;
    Bundle bundle;

    public ConsultaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_consulta, container, false);
        setupNavigation(root);
        return root;
    }

    public void setupNavigation(View root) {
        //En esta zona instanciamos los elementos para usarlos
        regresar = root.findViewById(R.id.regresar);
        atras = root.findViewById(R.id.atras);

        consultas = root.findViewById(R.id.consultas);
        consultas.setLayoutManager(new LinearLayoutManager(context));

        arrayCheckIns = new ArrayList<>();
        adapter = new ListaCheckInAdapter(arrayCheckIns);

        consultas.setAdapter(adapter);

        //Instanciamos la base de datos en firebase para mostrar los datos
        firebaseDatabase = FirebaseDatabase.getInstance("https://checkin-52edb-default-rtdb.europe-west1.firebasedatabase.app/");
        firebaseDatabase.getReference("checkIn/checkIn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayCheckIns.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CheckIn checkIn = snapshot.getValue(CheckIn.class);
                    arrayCheckIns.add(checkIn);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*-----------------------------------------ZONA ONCLICK-------------------------------------------------------------*/

        //Preparamos para cuando el usuario haga un click simple sobre uno de los elementos este se envie a otra pantalla
        adapter.setOnClickListener(new ListaCheckInAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                int id=arrayCheckIns.get(position).getId();
                String Snombre = arrayCheckIns.get(position).getNombre();
                String Sapellido1 = arrayCheckIns.get(position).getApellido1();
                String Sapellido2=arrayCheckIns.get(position).getApellido2();
                String Snacimiento=arrayCheckIns.get(position).getNacimiento();
                String Ssexo=arrayCheckIns.get(position).getSexo();
                String StipoDocumento=arrayCheckIns.get(position).getTipo_documento();
                String SnumDocumento=arrayCheckIns.get(position).getNum_documento();
                String Sexpedicion=arrayCheckIns.get(position).getExpedicion();
                String Snacionalidad=arrayCheckIns.get(position).getNacionalidad();
                String SfechaHora=arrayCheckIns.get(position).getFechaHora();

                bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("nombre", Snombre);
                bundle.putString("apellido1", Sapellido1);
                bundle.putString("apellido2", Sapellido2);
                bundle.putString("nacimiento", Snacimiento);
                bundle.putString("sexo", Ssexo);
                bundle.putString("tipoDocumento", StipoDocumento);
                bundle.putString("numDocumento", SnumDocumento);
                bundle.putString("expedicion", Sexpedicion);
                bundle.putString("nacionalidad", Snacionalidad);
                bundle.putString("fechaHora", SfechaHora);

                getParentFragmentManager().setFragmentResult("datos", bundle);

                Navigation.findNavController(root).navigate(R.id.detalleFragment);
            }
        });

        //Preparamos para que cuando el usuario haga un click prolongado salga un menu contextual para eliminar los datos
        adapter.setOnLongClickListener(new ListaCheckInAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(View v, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Eliminar");
                builder.setMessage("¿Estás seguro de que deseas eliminar este elemento?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Obtengo la ID del elemento seleccionado
                        int selectedId = arrayCheckIns.get(position).getId();

                        // Elimino los datos del Firebase Realtime Database
                        DatabaseReference checkInRef = firebaseDatabase.getReference("checkIn/checkIn").child(String.valueOf(selectedId));
                        checkInRef.setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Elimino el elemento del RecyclerView
                                    arrayCheckIns.remove(position);
                                    adapter.notifyItemRemoved(position);
                                    Toast.makeText(context, "Eliminación exitosa", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.homeFragment);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.homeFragment);
            }
        });
    }
}
