package net.iessochoa.danielruizhernandez.practicafinal_v_1.Activities;


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

import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

public class LoginActivity extends AppCompatActivity {

    private EditText txtMail;
    private TextInputLayout txtPassword;
    private Button btnLogin;
    private TextView lblRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtMail = findViewById(R.id.txtMail);
        txtPassword = findViewById(R.id.txtPassword);
        lblRegister = findViewById(R.id.lblRegister);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            userLogin();
        });

        lblRegister.setOnClickListener(view -> openRegisterActivity());

    }

    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void userLogin() {
        String mail = txtMail.getText().toString();
        String password = txtPassword.getEditText().getText().toString();

        if (TextUtils.isEmpty(mail)) {
            txtMail.setError("Ingrese un correo");
            txtMail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
            txtPassword.requestFocus();
        } else {

            mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Bienvenid@", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, ActivityPrincipal.class));
                } else {
                    Log.w("TAG", "Error:", task.getException());
                }
            });

        }

    }


}