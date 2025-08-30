package com.example.actividad29agosto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //URL
    public static final String BASE_URL = "http://192.168.1.3:3000";

    private Button btnListar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListar = findViewById(R.id.btnListar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la pantalla de listar vehículos
                Intent intent = new Intent(MainActivity.this, ListarVehiculosActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrarVehiculoActivity.class);
                startActivity(intent);
            }
        });
    }
}
