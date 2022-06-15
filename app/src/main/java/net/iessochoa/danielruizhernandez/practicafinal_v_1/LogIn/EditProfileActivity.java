package net.iessochoa.danielruizhernandez.practicafinal_v_1.LogIn;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText etNombre, etCorreo,etTelefono,etContrasenya;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        etContrasenya = findViewById(R.id.etContrasenya);


        mAuth = FirebaseAuth.getInstance();

    }

}
