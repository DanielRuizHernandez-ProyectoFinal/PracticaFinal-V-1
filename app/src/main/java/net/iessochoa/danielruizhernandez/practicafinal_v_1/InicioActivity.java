package net.iessochoa.danielruizhernandez.practicafinal_v_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.ui.LoginActivity;
import net.iessochoa.danielruizhernandez.practicafinal_v_1.ui.ActivityPrincipal;

public class InicioActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(InicioActivity.this, LoginActivity.class));
        } else {
            startActivity(new Intent(InicioActivity.this, ActivityPrincipal.class));
        }
        finish();
    }


}