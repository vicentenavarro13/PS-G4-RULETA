package com.example.caprichosa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

public class ModficacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modficacion);
        Button exitButton = findViewById(R.id.exitbutton);
        Button button3 = findViewById(R.id.button3);
        Context self = this;
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> arrayList_to_send = new ArrayList<String>();
        CustomAdapter customAdapter = new CustomAdapter(arrayList);
        recyclerView.setAdapter(customAdapter);
        EditText editText = findViewById(R.id.editTextText);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customAdapter.update(editText.getText().toString());
                arrayList_to_send.add(editText.getText().toString());
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, MainActivity.class);
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra("list", arrayList_to_send);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}