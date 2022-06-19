package net.iessochoa.danielruizhernandez.practicafinal_v_1.LogIn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.Model.Personaje;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

import java.util.ArrayList;

public class ActivityPrincipal extends AppCompatActivity {


    private FirebaseAuth mAuth;
    FloatingActionButton btAnyadir;
    private FirebaseFirestore fStore;
    private RecyclerView recyclerView;
    private ArrayList<Personaje> personajeArrayListl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_retrieve);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.id_recyclerView);

        btAnyadir = findViewById(R.id.fabAnyadir);


        btAnyadir.setOnClickListener(e -> nuevoPersonaje());

       /* DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                etNombre.setText(documentSnapshot.getString("nombre"));
                etCorreo.setText(documentSnapshot.getString("correo"));
                etTelefono.setText(documentSnapshot.getString("teléfono"));
                etContrasenya.setText(documentSnapshot.getString("contraseña"));
            }
        });
        ;
        */

        fStore.collection("personajes").whereEqualTo("usuario", mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Toast.makeText(ActivityPrincipal.this, "ACABOO", Toast.LENGTH_SHORT).show();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("CAPAPAPA", "olaaa");
                      //  document.getData().get()
                        Personaje personaje = new Personaje(document.getData().get("nombre").toString(),document.getData().get("clase").toString(),document.getData().get("raza").toString(),document.getData().get("nivel").toString(),document.getData().get("inventario").toString(),document.getData().get("usuario").toString());
                        personajeArrayListl.add(personaje);

                    }
                }
            }
        });

    }

    private void nuevoPersonaje() {
        Intent i = new Intent(this, NuevoPersonajeActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itLogOut:
                mAuth.signOut();
                finish();
                break;
            case R.id.itEditarPerfil:
                Intent intent = new Intent(this, EditProfileActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
