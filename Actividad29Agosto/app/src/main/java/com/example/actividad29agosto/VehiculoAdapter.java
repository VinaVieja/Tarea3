package com.example.actividad29agosto;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder> {

    private List<Vehiculo> vehiculoList;
    private Context context;

    public VehiculoAdapter(List<Vehiculo> vehiculoList, Context context) {
        this.vehiculoList = vehiculoList;
        this.context = context;
    }

    @NonNull
    @Override
    public VehiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehiculo, parent, false);
        return new VehiculoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Vehiculo vehiculo = vehiculoList.get(position);
        holder.tvMarcaModelo.setText(vehiculo.getMarca() + " - " + vehiculo.getModelo());
        holder.tvColorPrecio.setText(vehiculo.getColor() + " - $" + String.format("%.2f", vehiculo.getPrecio()));
        holder.tvPlaca.setText("Placa: " + vehiculo.getPlaca());

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditarVehiculoActivity.class);
                intent.putExtra("vehiculo_id", vehiculo.getId());
                intent.putExtra("vehiculo_marca", vehiculo.getMarca());
                intent.putExtra("vehiculo_modelo", vehiculo.getModelo());
                intent.putExtra("vehiculo_color", vehiculo.getColor());
                intent.putExtra("vehiculo_precio", vehiculo.getPrecio());
                intent.putExtra("vehiculo_placa", vehiculo.getPlaca());
                context.startActivity(intent);
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoConfirmacionEliminar(vehiculo.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehiculoList.size();
    }

    public static class VehiculoViewHolder extends RecyclerView.ViewHolder {
        TextView tvMarcaModelo, tvColorPrecio, tvPlaca;
        Button btnEditar, btnEliminar;

        public VehiculoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMarcaModelo = itemView.findViewById(R.id.tvMarcaModelo);
            tvColorPrecio = itemView.findViewById(R.id.tvColorPrecio);
            tvPlaca = itemView.findViewById(R.id.tvPlaca);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    private void mostrarDialogoConfirmacionEliminar(final int vehiculoId, final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Confirmar Eliminación")
                .setMessage("¿Estás seguro de que quieres eliminar este vehículo?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarVehiculo(vehiculoId, position);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void eliminarVehiculo(int vehiculoId, final int position) {
        String url = MainActivity.BASE_URL + "/vehiculos/" + vehiculoId;

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        vehiculoList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Vehículo eliminado exitosamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error al eliminar vehículo: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
