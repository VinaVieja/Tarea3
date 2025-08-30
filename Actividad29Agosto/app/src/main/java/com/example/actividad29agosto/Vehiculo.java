package com.example.actividad29agosto;

public class Vehiculo {
    private int id;
    private String marca;
    private String modelo;
    private String color;
    private double precio;
    private String placa;

    public Vehiculo(int id, String marca, String modelo, String color, double precio, String placa) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
        this.placa = placa;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public double getPrecio() {
        return precio;
    }

    public String getPlaca() {
        return placa;
    }
}
