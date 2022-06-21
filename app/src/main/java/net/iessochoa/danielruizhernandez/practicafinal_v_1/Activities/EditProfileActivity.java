package net.iessochoa.danielruizhernandez.practicafinal_v_1.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.Model.Usuario;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private DatabaseReference mDatabase;
    private Button btGuardar , btSalir;
    private EditText etNombre, etCorreo, etTelefono, etContrasenya;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        etContrasenya = findViewById(R.id.etContrasenya);
        btGuardar=findViewById(R.id.btGuardar);
        btSalir=findViewById(R.id.btSalir);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();

        userId = mAuth.getCurrentUser().getUid();


        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                etNombre.setText(documentSnapshot.getString("nombre"));
                etCorreo.setText(documentSnapshot.getString("correo"));
                etTelefono.setText(documentSnapshot.getString("teléfono"));
                etContrasenya.setText(documentSnapshot.getString("contraseña"));
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre= etNombre.getText().toString();
                String correo= etCorreo.getText().toString();
                String telefono= etTelefono.getText().toString();
                String contrasenya= etContrasenya.getText().toString();

                updateData(nombre,correo,telefono,contrasenya);

                fStore.getInstance().collection("users");

            }
        });

        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    private void updateData(String nombre, String correo, String telefono, String contrasenya) {


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




    }


}





