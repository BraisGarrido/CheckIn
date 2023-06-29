package com.proyecto.checkin.adaptadores;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.checkin.R;
import com.proyecto.checkin.entidades.CheckIn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaCheckInAdapter extends RecyclerView.Adapter<ListaCheckInAdapter.CheckInViewHolder> {
    private ArrayList<CheckIn> checkIns;
    private ArrayList<CheckIn> listaOriginal;
    private OnClickListener listener;
    private OnLongClickListener longClickListener;

    public ListaCheckInAdapter(ArrayList<CheckIn> checkIns) {
        this.checkIns = checkIns;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(checkIns);
    }

    @NonNull
    @Override
    public CheckInViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño del elemento de RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_recycler, parent, false);
        return new CheckInViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckInViewHolder holder, int position) {
        // Obtener el objeto CheckIn correspondiente a la posición actual
        CheckIn checkIn = checkIns.get(position);
        // Actualizar las vistas del elemento con los valores del CheckIn
        holder.viewNombre.setText(checkIns.get(position).getNombre());
        holder.viewApellido1.setText(checkIns.get(position).getApellido1());
        holder.viewApellido2.setText(checkIns.get(position).getApellido2());
        holder.viewId.setText(String.valueOf(checkIn.getId()) + ".-");
    }

    @Override
    public int getItemCount() {
        // Devolver el número de elementos en la lista
        return checkIns.size();
    }

    public void setOnClickListener(OnClickListener listener) {
        // Establecer el listener para el evento de clic
        this.listener = listener;
    }

    public void setOnLongClickListener(OnLongClickListener longClickListener) {
        // Establecer el listener para el evento de clic largo
        this.longClickListener = longClickListener;
    }

    public interface OnClickListener {
        void onClick(View v, int position);
    }

    public interface OnLongClickListener {
        void onLongClick(View v, int position);
    }

    public class CheckInViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewApellido1, viewId, viewApellido2;

        public CheckInViewHolder(@NonNull View itemView) {
            super(itemView);
            // Obtener referencias a las vistas del elemento de RecyclerView
            viewNombre = itemView.findViewById(R.id.recy_nombre);
            viewApellido1 = itemView.findViewById(R.id.recy_apellido1);
            viewId = itemView.findViewById(R.id.recy_id);
            viewApellido2 = itemView.findViewById(R.id.recy_apellido2);

            // Configurar el listener para el evento de clic en el elemento
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onClick(v, position);
                        }
                    }
                }
            });

            // Configurar el listener para el evento de clic largo en el elemento
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            longClickListener.onLongClick(v, position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }
}