package net.iessochoa.danielruizhernandez.practicafinal_v_1.LogIn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

public class Tabla extends Activity {



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

    }//End onCreate

}
