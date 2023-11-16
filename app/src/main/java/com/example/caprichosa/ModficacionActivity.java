package com.example.caprichosa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ModficacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modficacion);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        String[] result = {"Test"};
        CustomAdapter customAdapter = new CustomAdapter(result);
        recyclerView.setAdapter(customAdapter);
    }
}