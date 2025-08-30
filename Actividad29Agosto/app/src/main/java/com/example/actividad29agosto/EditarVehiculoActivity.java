package com.example.actividad29agosto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EditarVehiculoActivity extends AppCompatActivity {

    private EditText etMarca, etModelo, etColor, etPrecio, etPlaca;
    private Button btnGuardar;
    private int vehiculoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_vehiculo);

        etMarca = findViewById(R.id.etMarca);
        etModelo = findViewById(R.id.etModelo);
        etColor = findViewById(R.id.etColor);
        etPrecio = findViewById(R.id.etPrecio);
        etPlaca = findViewById(R.id.etPlaca);
        btnGuardar = findViewById(R.id.btnGuardar);

        vehiculoId = getIntent().getIntExtra("vehiculo_id", -1);
        etMarca.setText(getIntent().getStringExtra("vehiculo_marca"));
        etModelo.setText(getIntent().getStringExtra("vehiculo_modelo"));
        etColor.setText(getIntent().getStringExtra("vehiculo_color"));
        etPrecio.setText(String.valueOf(getIntent().getDoubleExtra("vehiculo_precio", 0)));
        etPlaca.setText(getIntent().getStringExtra("vehiculo_placa"));

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marca = etMarca.getText().toString();
                String modelo = etModelo.getText().toString();
                String color = etColor.getText().toString();
                String precio = etPrecio.getText().toString();
                String placa = etPlaca.getText().toString();

                if (!marca.isEmpty() && !modelo.isEmpty() && !color.isEmpty() && !precio.isEmpty() && !placa.isEmpty()) {
                    actualizarVehiculo(vehiculoId, marca, modelo, color, precio, placa);
                } else {
                    Toast.makeText(EditarVehiculoActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void actualizarVehiculo(int id, String marca, String modelo, String color, String precio, String placa) {
        String url = MainActivity.BASE_URL + "/vehiculos/" + id;  // URL

        try {
            JSONObject params = new JSONObject();
            params.put("marca", marca);
            params.put("modelo", modelo);
            params.put("color", color);
            params.put("precio", precio);
            params.put("placa", placa);

            // PUT
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(EditarVehiculoActivity.this, "Vehículo actualizado correctamente", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EditarVehiculoActivity.this, "Error al actualizar: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                public byte[] getBody() {
                    return params.toString().getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };

            // Volley
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
