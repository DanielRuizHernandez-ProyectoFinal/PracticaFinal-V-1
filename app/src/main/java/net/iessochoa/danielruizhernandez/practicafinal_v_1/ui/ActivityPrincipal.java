package net.iessochoa.danielruizhernandez.practicafinal_v_1.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.Adapter.PersonajeAdapter;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.Model.Personaje;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

import java.util.ArrayList;

public class ActivityPrincipal extends AppCompatActivity implements PersonajeAdapter.OnItemClickListener {


    private FirebaseAuth mAuth;
    FloatingActionButton btAnyadir;
    private FirebaseFirestore fStore;
    private RecyclerView recyclerView;
    private ArrayList<Personaje> personajeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_retrieve);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.id_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btAnyadir = findViewById(R.id.fabAnyadir);


        btAnyadir.setOnClickListener(e -> nuevoPersonaje());


    }


    @Override
    protected void onResume() {
        super.onResume();

        fStore.collection("personajes").whereEqualTo("usuario", mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    personajeArrayList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("CAPAPAPA", "olaaa");
                        String idPersonaje = document.getId();
                        Personaje personaje = new Personaje(document.getData().get("nombre").toString(), document.getData().get("clase").toString(), document.getData().get("raza").toString(), document.getData().get("nivel").toString(), document.getData().get("inventario").toString(), document.getData().get("usuario").toString(), idPersonaje);
                        personajeArrayList.add(personaje);
                    }
                    PersonajeAdapter a = new PersonajeAdapter(ActivityPrincipal.this, personajeArrayList);
                    recyclerView.setAdapter(a);
                }
            }
        });
    }

    private void datosFiltrados(String busquedaNombre) {

        if (busquedaNombre.isEmpty()) {
            fStore.collection("personajes").whereEqualTo("usuario", mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    Toast.makeText(ActivityPrincipal.this, "Base de datos cargada", Toast.LENGTH_SHORT).show();
                    if (task.isSuccessful()) {
                        personajeArrayList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("CAPAPAPA", "olaaa");
                            String idPersonaje = document.getId();
                            Personaje personaje = new Personaje(document.getData().get("nombre").toString(), document.getData().get("clase").toString(), document.getData().get("raza").toString(), document.getData().get("nivel").toString(), document.getData().get("inventario").toString(), document.getData().get("usuario").toString(), idPersonaje);
                            personajeArrayList.add(personaje);
                        }
                        PersonajeAdapter a = new PersonajeAdapter(ActivityPrincipal.this, personajeArrayList);
                        recyclerView.setAdapter(a);
                    }
                }
            });

        } else {
            fStore.collection("personajes").whereEqualTo("nombre", busquedaNombre).whereEqualTo("usuario", mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        personajeArrayList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("CAPAPAPA", "olaaa");
                            String idPersonaje = document.getId();
                            Personaje personaje = new Personaje(document.getData().get("nombre").toString(), document.getData().get("clase").toString(), document.getData().get("raza").toString(), document.getData().get("nivel").toString(), document.getData().get("inventario").toString(), document.getData().get("usuario").toString(), idPersonaje);
                            personajeArrayList.add(personaje);
                        }
                        PersonajeAdapter a = new PersonajeAdapter(ActivityPrincipal.this, personajeArrayList);
                        recyclerView.setAdapter(a);
                    }
                }
            });
        }
    }


    private void nuevoPersonaje() {
        Intent i = new Intent(this, NuevoPersonajeActivity.class);
        i.putExtra("newPj", true);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itLogOut:
                mAuth.signOut();
                Intent intent2=new Intent(this,LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.itEditarPerfil:
                Intent intent = new Intent(this, EditProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.mostrarTodos:
                mostrarTodos();
                break;
            case R.id.mostrarMisPersonajes:
                mostrarMisPersonajes();
                break;
        }
        return true;
    }

    private void mostrarMisPersonajes() {
        fStore.collection("personajes").whereEqualTo("usuario", mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Toast.makeText(ActivityPrincipal.this, "Tus personajes", Toast.LENGTH_SHORT).show();
                if (task.isSuccessful()) {
                    personajeArrayList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("CAPAPAPA", "olaaa");
                        String idPersonaje = document.getId();
                        Personaje personaje = new Personaje(document.getData().get("nombre").toString(), document.getData().get("clase").toString(), document.getData().get("raza").toString(), document.getData().get("nivel").toString(), document.getData().get("inventario").toString(), document.getData().get("usuario").toString(), idPersonaje);
                        personajeArrayList.add(personaje);
                    }
                    PersonajeAdapter a = new PersonajeAdapter(ActivityPrincipal.this, personajeArrayList);
                    recyclerView.setAdapter(a);
                }
            }
        });
    }

    private void mostrarTodos() {

        fStore.collection("personajes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Toast.makeText(ActivityPrincipal.this, "Cargados todos los personajes", Toast.LENGTH_SHORT).show();
                if (task.isSuccessful()) {
                    personajeArrayList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("CAPAPAPA", "olaaa");
                        String idPersonaje = document.getId();
                        Personaje personaje = new Personaje(document.getData().get("nombre").toString(), document.getData().get("clase").toString(), document.getData().get("raza").toString(), document.getData().get("nivel").toString(), document.getData().get("inventario").toString(), document.getData().get("usuario").toString(), idPersonaje);
                        personajeArrayList.add(personaje);
                    }
                    PersonajeAdapter a = new PersonajeAdapter(ActivityPrincipal.this, personajeArrayList);
                    recyclerView.setAdapter(a);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_buscar)
                .getActionView();
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                datosFiltrados(newText);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                datosFiltrados(query);
                return false;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, NuevoPersonajeActivity.class);
        intent.putExtra("newPj", false);
        intent.putExtra("pj", personajeArrayList.get(position));
        startActivity(intent);

    }

    @Override
    public void onDeleteClick(int position) {

        String variable = personajeArrayList.get(position).getId();

        DocumentReference document = fStore.collection("personajes").document(variable);
        if (document != null) {
            document.delete().addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        personajeArrayList.remove(personajeArrayList.get(position));
                        Toast.makeText(ActivityPrincipal.this, "Borrado", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

}

