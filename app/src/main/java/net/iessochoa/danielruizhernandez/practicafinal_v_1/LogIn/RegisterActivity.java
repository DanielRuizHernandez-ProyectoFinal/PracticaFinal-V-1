package net.iessochoa.danielruizhernandez.practicafinal_v_1.LogIn;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    private EditText txtUser;
    private EditText txtMail;
    private EditText txtPhone;
    private TextInputLayout txtPassword;
    private Button btnRegister;
    private TextView lblLogin;

    private String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        txtUser = findViewById(R.id.txtUser);
        txtMail = findViewById(R.id.txtMail);
        txtPhone = findViewById(R.id.txtPhone);
        txtPassword = findViewById(R.id.txtPassword);
        lblLogin = findViewById(R.id.lblLogin);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(view -> {
            createuser();
        });


        lblLogin.setOnClickListener(view -> openLoginActivity());

    }


    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void createuser() {

        String name = txtUser.getText().toString();
        String mail = txtMail.getText().toString();
        String phone = txtPhone.getText().toString();
        String password = txtPassword.getEditText().getText().toString();

        if (TextUtils.isEmpty(name)) {
            txtMail.setError("Ingrese un Nombre");
            txtMail.requestFocus();
        } else if (TextUtils.isEmpty(mail)) {
            txtMail.setError("Ingrese un Correo");
            txtMail.requestFocus();
        } else if (TextUtils.isEmpty(phone)) {
            txtMail.setError("Ingrese un Teléfono");
            txtMail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            txtMail.setError("Ingrese una Contraseña");
            txtMail.requestFocus();
        } else {

            mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    userID = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("users").document(userID);

                    Map<String, Object> user = new HashMap<>();
                    user.put("Nombre", name);
                    user.put("Correo", mail);
                    user.put("Teléfono", phone);
                    user.put("Contraseña", password);

                    documentReference.set(user).addOnSuccessListener(unused -> Log.d("TAG", "onSuccess: Datos registrados" + userID));
                    Toast.makeText(RegisterActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Usuario no registrado" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            });

        }
    }


    private void isUser(){

        String userUsuario=txtUser.getEditableText().toString().trim();
        String userContrasenya=txtUser.getEditableText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");

        Query checkUser=reference.orderByChild("Nombre").equalTo(userUsuario);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    String contrasenyaDB=snapshot.child(userUsuario).child("Contraseña").getValue(String.class);

                    if (contrasenyaDB.equals(userContrasenya)){

                        String nombreDB=snapshot.child(userUsuario).child("Nombre").getValue(String.class);
                        String correoDB=snapshot.child(userUsuario).child("Correo").getValue(String.class);
                        String telefonoDB=snapshot.child(userUsuario).child("Teléfono").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class);

                        intent.putExtra("Nombre",nombreDB);
                        intent.putExtra("Contraseña",contrasenyaDB);
                        intent.putExtra("Correo",correoDB);
                        intent.putExtra("Teléfono",telefonoDB);

                        startActivity(intent);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
