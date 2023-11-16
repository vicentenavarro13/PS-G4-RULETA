package com.example.caprichosa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModficacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modficacion);
        Button exitButton = findViewById(R.id.exitbutton);
        Context self = this;
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, MainActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] result = {"Test111111111111111111111111111111111111111111111111111111","TEST","TEST","TEST","TEST","TEST","TEST","TEST","TEST","TEST","TEST"};
        CustomAdapter customAdapter = new CustomAdapter(result);
        recyclerView.setAdapter(customAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}