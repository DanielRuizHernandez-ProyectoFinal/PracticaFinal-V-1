package net.iessochoa.danielruizhernandez.practicafinal_v_1.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.Model.Personaje;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private ArrayList<Personaje> arrayList;
    private ArrayList<Personaje> arrayListBuscarEntera;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombrePersonaje, tvClase,tvNivel;
        ConstraintLayout clParent;
        Button btBorrar;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            //Defino las views
            clParent = itemView.findViewById(R.id.parentLayout);
            tvNombrePersonaje = (TextView) itemView.findViewById(R.id.tvNombrePersonaje);
            tvClase = (TextView) itemView.findViewById(R.id.tvClasePersonaje);
            btBorrar = itemView.findViewById(R.id.btBorrar);
            tvNivel=itemView.findViewById(R.id.tvNivel);



        }

        public TextView getTextViewNivel(){
            return tvNivel;
        }

        public TextView getTextViewPersonaje() {
            return tvNombrePersonaje;
        }

        public TextView getTextViewClase() {
            return tvClase;
        }

        public ConstraintLayout getClParent() {
            return clParent;
        }


    }

    public PersonajeAdapter(PersonajeAdapter.OnItemClickListener listener, ArrayList<Personaje> arrayList) {
        this.arrayList = arrayList;
        this.listener = listener;
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


    }



    @NonNull
    @Override
    public PersonajeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recyclerview, parent, false);
        return new ViewHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull PersonajeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.getTextViewNivel().setText("Nivel: " + arrayList.get(position).getNivel());
        holder.getTextViewClase().setText("Clase: " +arrayList.get(position).getClase());
        holder.getTextViewPersonaje().setText("Nombre: "+arrayList.get(position).getNombre());
        holder.getClParent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });

        holder.btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(position);

                mFirestore.collection("personajes").document(arrayList.get(position).getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            notifyDataSetChanged();
                            Toast.makeText(v.getContext(), "Borrado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface OnItemClickListener {
        void onClick(int position);

        void  onDeleteClick(int position);


    }


}
