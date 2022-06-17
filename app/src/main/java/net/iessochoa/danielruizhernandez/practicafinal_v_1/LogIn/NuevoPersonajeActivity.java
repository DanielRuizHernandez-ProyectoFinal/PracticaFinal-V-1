package net.iessochoa.danielruizhernandez.practicafinal_v_1.LogIn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

public class NuevoPersonajeActivity extends AppCompatActivity {

    private Spinner spClase, spRaza;
    private EditText etNombreNuevoPersonaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_personaje);

        spClase = findViewById(R.id.spClase);
        spRaza = findViewById(R.id.spRaza);
        etNombreNuevoPersonaje = findViewById(R.id.etNombreNuevoPersonaje);

        String[] opcionesClase = {"Guerrero","Brujo","Clérigo","Picaro","Barbaro","Explorador","Mago"};
        String[] opcionesRaza={"Humano","Enano","Mediano","Elfo","Semiorco","Draconido"};

        ArrayAdapter<String> arrayAdapterOpcionesClase = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcionesClase);
        spClase.setAdapter(arrayAdapterOpcionesClase);

        ArrayAdapter<String> arrayAdapterOpcionesRaza = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,opcionesRaza);
        spRaza.setAdapter(arrayAdapterOpcionesRaza);
    }
}