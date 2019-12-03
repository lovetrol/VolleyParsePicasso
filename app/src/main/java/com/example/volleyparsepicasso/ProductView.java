package com.example.volleyparsepicasso;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductView extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    ImageView imageView;

    String url;
    RequestQueue queue;
    JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        Intent intent = getIntent();

        /*El nombre de getStringExtra("imag") debe ser el mismo nombre que que enviamos desde cuando damos click
        * de input.putExtra("imag") */

        String imag_url = intent.getStringExtra("imag");

        ImageView imageView = findViewById(R.id.imageView);


        Picasso.get().load(imag_url).into(imageView);


        //

        /*Bundle bundle = new Bundle();

        bundle = getIntent().getExtras();

        final long id = bundle.getLong("ID");

        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_base) + "cm/2020-1/product_detail.php?id=" + id;

        request = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(request);*/
        /*Esta parte es para consumir la descripcion del producto desde url variando el id del final ?id=6541
        aun no descubro como traerlo de esa forma, variando el id y consumir*/

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(ProductView.this, "error al cargar", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {

           /* Picasso.get()
                    .load(getResources().getString(R.string.url_base) + response.getString("imag_url"))
                    .into(imageView);

                    No entra en esta parte, queria que dar click mostrara el nombre del producto realiacionado al cardview*/

            Toast.makeText(ProductView.this, response.getString("name"), Toast.LENGTH_LONG).show();




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}




