package com.example.actividad29agosto;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListarVehiculosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VehiculoAdapter adapter;
    private List<Vehiculo> vehiculoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_vehiculos);

        recyclerView = findViewById(R.id.recyclerViewVehiculos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        vehiculoList = new ArrayList<>();
        adapter = new VehiculoAdapter(vehiculoList, this);
        recyclerView.setAdapter(adapter);

        listarVehiculos(); // listar los vehiculos
    }

    private void listarVehiculos() {
        String url = MainActivity.BASE_URL + "/vehiculos"; // URL

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        vehiculoList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                int id = jsonObject.getInt("id");
                                String marca = jsonObject.getString("marca");
                                String modelo = jsonObject.getString("modelo");
                                String color = jsonObject.getString("color");
                                double precio = jsonObject.getDouble("precio");
                                String placa = jsonObject.getString("placa");

                                Vehiculo vehiculo = new Vehiculo(id, marca, modelo, color, precio, placa);
                                vehiculoList.add(vehiculo);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter.notifyDataSetChanged(); // Notificacion sobre datos que se han cambiado
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListarVehiculosActivity.this, "Error al obtener vehículos: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
