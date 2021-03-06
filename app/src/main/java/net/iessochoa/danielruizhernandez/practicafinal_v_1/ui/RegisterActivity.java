package net.iessochoa.danielruizhernandez.practicafinal_v_1.ui;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
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
            txtMail.setError("Ingrese un Tel??fono");
            txtMail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            txtMail.setError("Ingrese una Contrase??a");
            txtMail.requestFocus();
        } else {


            mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    userID = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("users").document(userID);

                    Map<String, Object> user = new HashMap<>();
                    user.put("nombre", name);
                    user.put("correo", mail);
                    user.put("tel??fono", phone);
                    user.put("contrase??a", password);

                    documentReference.set(user).addOnSuccessListener(unused -> Log.d("TAG", "onSuccess: Datos registrados" + userID));
                    Toast.makeText(RegisterActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Usuario no registrado" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            });

        }
    }
}
