package net.iessochoa.danielruizhernandez.practicafinal_v_1.LogIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.Model.PersonajeModel;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

import java.util.HashMap;
import java.util.Map;

public class NuevoPersonajeActivity extends AppCompatActivity {

    private Spinner spClase, spRaza, spNivel;
    private TextInputLayout etNombreNuevoPersonaje;
    private Button btCompletarPersonaje;
    private EditText mtInventario;
    FirebaseStorage mStorage;
    DatabaseReference mRef;
    FirebaseDatabase mDatabase;
    FirebaseFirestore db;
    private String userId;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_personaje);

        spClase = findViewById(R.id.spClase);
        spRaza = findViewById(R.id.spRaza);
        spNivel = findViewById(R.id.spNivel);
        etNombreNuevoPersonaje = findViewById(R.id.etNombreNuevoPersonaje);
        btCompletarPersonaje = findViewById(R.id.btCompletarPersonaje);
        mtInventario = findViewById(R.id.mtInventario);

        mDatabase = FirebaseDatabase.getInstance();
        mRef=FirebaseDatabase.getInstance().getReference().child("personajes");
        db=FirebaseFirestore.getInstance();

        //     userID=mAuth.getUid();

        String[] opcionesClase = {"Guerrero", "Brujo", "Clérigo", "Picaro", "Barbaro", "Explorador", "Mago"};
        String[] opcionesRaza = {"Humano", "Enano", "Mediano", "Elfo", "Semiorco", "Draconido"};
        String[] opcionesNivel = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

        ArrayAdapter<String> arrayAdapterOpcionesClase = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcionesClase);
        spClase.setAdapter(arrayAdapterOpcionesClase);

        ArrayAdapter<String> arrayAdapterOpcionesRaza = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcionesRaza);
        spRaza.setAdapter(arrayAdapterOpcionesRaza);

        ArrayAdapter<String> arrayAdapterOpcionesNivel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcionesNivel);
        spNivel.setAdapter(arrayAdapterOpcionesNivel);


        btCompletarPersonaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();

                // DatabaseReference newPost=mRef.push();

                /*
                HashMap objetosNuevoPersonaje= new HashMap();

                objetosNuevoPersonaje.put("clase",clase);
                objetosNuevoPersonaje.put("raza",raza);
                objetosNuevoPersonaje.put("nivel",nivel);

                 */

             /*
             newPost.child("nombrepersonaje").setValue(etNombreNuevoPersonaje.getEditText());
                newPost.child("clase").setValue(clase);
                newPost.child("raza").setValue(raza);
                newPost.child("nivel").setValue(nivel);

              */


            }
        });
    }

    private void insertData() {

        String nombre = etNombreNuevoPersonaje.getEditText().getText().toString();
        String clase = spClase.getSelectedItem().toString();
        String raza = spRaza.getSelectedItem().toString();
        String nivel = spNivel.getSelectedItem().toString();
        String inventario = mtInventario.getText().toString();


        Map<String,Object> map = new HashMap<>();
        map.put("nombre",nombre);
        map.put("clase",clase);
        map.put("raza",raza);
        map.put("nivel",nivel);
        map.put("inventario",inventario);


        db.collection("personajes").document().set(map);
        Toast.makeText(this, "Personaje creado", Toast.LENGTH_SHORT).show();


    }
}