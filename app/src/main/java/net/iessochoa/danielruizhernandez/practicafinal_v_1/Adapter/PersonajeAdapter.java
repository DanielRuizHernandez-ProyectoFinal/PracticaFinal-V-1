package net.iessochoa.danielruizhernandez.practicafinal_v_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;




import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.Model.PersonajeModel;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

import java.util.List;


public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.ViewHolder> {

    Context context;
    List<PersonajeModel> personajeModelList;

    public PersonajeAdapter(Context context, List<PersonajeModel> personajeModelList) {
        this.context = context;
        this.personajeModelList = personajeModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview,parent,false);
        // conectividad aquí

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PersonajeModel personajeModel=personajeModelList.get(position);
      //  holder.tvNombrePersonaje("")

    }

    @Override
    public int getItemCount() {
        return personajeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // aqui declaro el diseño

        ImageView ivImagenPersonaje;
        TextView tvNombreJugador,tvNombrePersonaje,tvClaseJugador;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImagenPersonaje=itemView.findViewById(R.id.ivImagenPersonaje);
            tvNombreJugador=itemView.findViewById(R.id.tvNombreJugador);
            tvNombrePersonaje=itemView.findViewById(R.id.tvNombrePersonaje);
            tvClaseJugador=itemView.findViewById(R.id.tvClasePersonaje);
        }
    }
}
