package com.example.volleyparsepicasso;

public class Productos {

    public String id;
    private String nombre;
    private String precio;
    private String envio;
    private String provider;
    private String image;


    public Productos(String id, String nombre, String precio, String envio, String provider, String image) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.envio = envio;
        this.provider = provider;
        this.image = image;
    }
    public Productos() {

    }


    public String getId() { return id; }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {return precio; }

    public String getEnvio() {return envio;}

    public String getProvider() { return provider; }

    public String getImage() {
        return image;
    }
}

