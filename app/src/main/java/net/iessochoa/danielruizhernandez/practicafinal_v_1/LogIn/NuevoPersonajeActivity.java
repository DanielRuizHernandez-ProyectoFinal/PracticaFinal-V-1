package net.iessochoa.danielruizhernandez.practicafinal_v_1.LogIn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.Model.Personaje;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NuevoPersonajeActivity extends AppCompatActivity {

    private Spinner spClase, spRaza, spNivel;
    private TextInputLayout etNombreNuevoPersonaje;
    private Button btCompletarPersonaje;
    private EditText mtInventario;
    private EditText txtNombre;
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


        Boolean newPj = getIntent().getBooleanExtra("newPj", true);
        Personaje pj = (Personaje) getIntent().getSerializableExtra("pj");


        spClase = findViewById(R.id.spClase);
        spRaza = findViewById(R.id.spRaza);
        spNivel = findViewById(R.id.spNivel);
        etNombreNuevoPersonaje = findViewById(R.id.etNombreNuevoPersonaje);
        btCompletarPersonaje = findViewById(R.id.btCompletarPersonaje);
        mtInventario = findViewById(R.id.mtInventario);
        txtNombre = findViewById(R.id.txtNombrePersonaje);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("personajes");
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        String[] opcionesClase = {"Guerrero", "Brujo", "Clérigo", "Picaro", "Barbaro", "Explorador", "Mago", "Hechicero", "Druida", "Paladin"};
        String[] opcionesRaza = {"Humano", "Enano", "Mediano", "Elfo", "Semiorco", "Draconido"};
        String[] opcionesNivel = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

        ArrayAdapter<String> arrayAdapterOpcionesClase = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcionesClase);
        spClase.setAdapter(arrayAdapterOpcionesClase);

        ArrayAdapter<String> arrayAdapterOpcionesRaza = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcionesRaza);
        spRaza.setAdapter(arrayAdapterOpcionesRaza);

        ArrayAdapter<String> arrayAdapterOpcionesNivel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcionesNivel);
        spNivel.setAdapter(arrayAdapterOpcionesNivel);


        if (pj != null) {
            String nombrePersonaje = pj.getNombre();
            etNombreNuevoPersonaje.getEditText().setText(nombrePersonaje);
            spClase.setSelection(arrayAdapterOpcionesClase.getPosition(pj.getClase()));
            spNivel.setSelection(arrayAdapterOpcionesNivel.getPosition(pj.getNivel()));
            spRaza.setSelection(arrayAdapterOpcionesRaza.getPosition(pj.getRaza()));
            mtInventario.setText(pj.getInventario());

        }

        btCompletarPersonaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newPj) {
                    insertData();
                } else {
                    updateData(pj);
                }
            }
        });
    }

    private void updateData(Personaje pj) {

        db.collection("personajes").document(pj.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Personaje personaje= documentSnapshot.toObject(Personaje.class);

                personaje.setNombre(etNombreNuevoPersonaje.getEditText().getText().toString());
                personaje.setClase(spRaza.getSelectedItem().toString());
                personaje.setRaza(spRaza.getSelectedItem().toString());
                personaje.setInventario(mtInventario.getText().toString());
                personaje.setNivel(spNivel.getSelectedItem().toString());
                personaje.setId(pj.getId());
                personaje.setUsuario(pj.getUsuario());





                db.collection("personajes").document(pj.getId()).set(personaje).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(NuevoPersonajeActivity.this, "Personaje modificado", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

            }
        });
        /*

        fStore= FirebaseFirestore.getInstance();

        fStore.collection("users").document(userId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Usuario usuarioActual = documentSnapshot.toObject(Usuario.class);

                usuarioActual.setNombre(nombre);
                usuarioActual.setCorreo(correo);
                usuarioActual.setContraseña(contrasenya);
                usuarioActual.setTeléfono(telefono);

                fStore.collection("users").document(userId).set(usuarioActual).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(EditProfileActivity.this, "Cambios guardados", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

         */

    }

    private void insertData() {


        String nombre = etNombreNuevoPersonaje.getEditText().getText().toString();
        String clase = spClase.getSelectedItem().toString();
        String raza = spRaza.getSelectedItem().toString();
        String nivel = spNivel.getSelectedItem().toString();
        String inventario = mtInventario.getText().toString();
        String usuario = mAuth.getCurrentUser().getEmail();


        Map<String, Object> map = new HashMap<>();
        map.put("usuario", usuario);
        map.put("nombre", nombre);
        map.put("clase", clase);
        map.put("raza", raza);
        map.put("nivel", nivel);
        map.put("inventario", inventario);


        if (nombre.isEmpty()) {
            Toast.makeText(this, "Falta el nombre", Toast.LENGTH_SHORT).show();
        } else {
            db.collection("personajes").document().set(map);
            Toast.makeText(this, "Personaje creado", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}