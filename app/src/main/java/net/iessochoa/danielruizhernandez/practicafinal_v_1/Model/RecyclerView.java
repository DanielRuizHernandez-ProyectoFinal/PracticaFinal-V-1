package net.iessochoa.danielruizhernandez.practicafinal_v_1.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import net.iessochoa.danielruizhernandez.practicafinal_v_1.R;

public class RecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
    }

    public class Adapter<T> {
    }
}