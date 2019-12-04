package com.example.volleyparsepicasso;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray>, Adaptador.OnItemClickListener {

    private static final String URL_Usuarios = "https://www.serverbpw.com/cm/2020-1/products.php";

    CardView cardView;

    ArrayList<Imagen> imagenes;
    List<Productos> productList;

    RecyclerView recyclerView;
    Adaptador adaptadorImag;
    ImageView imageViewRight;

    String url;
    RequestQueue queue;
    JsonArrayRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = findViewById(R.id.card_view);
        imageViewRight = findViewById(R.id.imageViewRight);

        productList = new ArrayList<>();



        imagenes = new ArrayList<Imagen>();
        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_base) + "cm/2020-1/products.php";
        request = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(request);


        loadUsers();

        // // //

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("RESPUESTA", error.toString());
    }

    @Override
    public void onResponse(JSONArray response) {
        Log.d("RESPUESTA", response.toString());

        JSONObject jsonObject;

        try {
            for(int i=0;i<response.length();i++){

                jsonObject = response.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String thumbnail_url = jsonObject.getString("thumbnail_url");

                Imagen imagen = new Imagen(id, thumbnail_url);

                imagenes.add(imagen);


            }

            Toast.makeText(MainActivity.this, "Prueba", Toast.LENGTH_LONG).show();

            AdaptadorImage adaptadorImage = new AdaptadorImage(MainActivity.this,imagenes);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void loadUsers() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Usuarios,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject producto = array.getJSONObject(i);

                                productList.add(new Productos(
                                        producto.getString("id"),
                                        producto.getString("name"),
                                        producto.getString("price"),
                                        producto.getString("delivery"),
                                        producto.getString("provider"),
                                        producto.getString("thumbnail_url")
                                ));



                                Toast.makeText(MainActivity.this, "hola"+productList, Toast.LENGTH_SHORT).show();
                            }



                            Adaptador adaptador = new Adaptador(MainActivity.this, productList);
                            recyclerView = findViewById(R.id.recyclerView);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                            recyclerView.setAdapter(adaptador);

                            adaptador.setOnClickListener(MainActivity.this);


                        } catch (JSONException error) {
                            Toast.makeText(MainActivity.this, "hola"+error, Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "hola"+error, Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);


    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ProductView.class);
        //Imagen clickItem = Imagen.get(position)
        Productos clickItem = productList.get(position);
        Toast.makeText(MainActivity.this, "aqui", Toast.LENGTH_LONG).show();
        intent.putExtra("ID",clickItem.getId());
        intent.putExtra("imag", clickItem.getImage());

        startActivity(intent);
    }
}
