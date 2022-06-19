package net.iessochoa.danielruizhernandez.practicafinal_v_1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.ViewHolder> {

    private String[] localDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Defino las views

            TextView tvNombreJugador, tvNombrePersonaje, tvClase;


            tvNombreJugador = (TextView) itemView.findViewById(R.id.tvNombreJugador);
            tvNombrePersonaje = (TextView) itemView.findViewById(R.id.tvNombrePersonaje);
            tvClase = (TextView) itemView.findViewById(R.id.tvClasePersonaje);

        }
    }

    public PersonajeAdapter(String[] dataSet){
        localDataSet=dataSet;
    }

    @NonNull
    @Override
    public PersonajeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonajeAdapter.ViewHolder holder, int position) {

        holder.g
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }


}
