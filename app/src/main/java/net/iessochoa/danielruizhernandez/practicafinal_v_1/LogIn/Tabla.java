package net.iessochoa.danielruizhernandez.practicafinal_v_1.LogIn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

public class Tabla extends AppCompatActivity {


    private Button btnSalir;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);


        btnSalir = findViewById(R.id.btnSalir);

        mAuth = FirebaseAuth.getInstance();

        btnSalir.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));

        });


        Toolbar toolbar= (Toolbar) findViewById(R.id.tbTabla);
        setSupportActionBar(toolbar);

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itLogOut:
                mAuth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
