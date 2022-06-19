package net.iessochoa.danielruizhernandez.practicafinal_v_1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.Model.Personaje;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private ArrayList<Personaje> arrayList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombrePersonaje, tvClase;
        ConstraintLayout clParent;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            //Defino las views
            clParent = itemView.findViewById(R.id.parentLayout);
            tvNombrePersonaje = (TextView) itemView.findViewById(R.id.tvNombrePersonaje);
            tvClase = (TextView) itemView.findViewById(R.id.tvClasePersonaje);
        }


        public TextView getTextViewPersonaje() {
            return tvNombrePersonaje;
        }

        public TextView getTextViewClase() {
            return tvClase;
        }
        public ConstraintLayout getClParent(){
            return clParent;
        }
    }

    public PersonajeAdapter(PersonajeAdapter.OnItemClickListener listener, ArrayList<Personaje> dataSet) {
        arrayList = dataSet;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonajeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview, parent, false);
        return new ViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonajeAdapter.ViewHolder holder, int position) {
        holder.getTextViewClase().setText(arrayList.get(position).getClase());
        holder.getTextViewPersonaje().setText(arrayList.get(position).getNombre());
        holder.getClParent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

}
