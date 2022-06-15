package net.iessochoa.danielruizhernandez.practicafinal_v_1.LogIn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private DatabaseReference mDatabase;
    private Button btGuardar;
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

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();

        userId = mAuth.getCurrentUser().getUid();


        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                etNombre.setText(documentSnapshot.getString("Nombre"));
                etCorreo.setText(documentSnapshot.getString("Correo"));
                etTelefono.setText(documentSnapshot.getString("Teléfono"));
                etContrasenya.setText(documentSnapshot.getString("Contraseña"));
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

            }
        });
    }
    private void updateData(String nombre, String correo, String telefono, String contrasenya) {

       /* HashMap user= new HashMap();
        user.put("Nombre",nombre);
        user.put("Correo",correo);
        user.put("Teléfono",telefono);
        user.put("Contraseña",contrasenya);

        */


      //  mDatabase=FirebaseDatabase.getInstance().getReference("users");
      //  mDatabase.child(userId).child("Nombre").setValue(nombre);

        DatabaseReference reference ;
        //reference=FirebaseDatabase.getInstance().getReference("users");
       // reference.child("users").child(userId).child("Nombre").setValue(nombre);




    }


}





