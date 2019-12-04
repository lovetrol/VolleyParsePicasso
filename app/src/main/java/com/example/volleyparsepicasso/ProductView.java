package com.example.volleyparsepicasso;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductView extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    ImageView imageView;
    TextView nameDesc, descProduct;


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
        nameDesc = findViewById(R.id.textViewNameProduct);
        descProduct = findViewById(R.id.textViewDescription);

        Picasso.get().load(imag_url).into(imageView);


        Bundle bundle = new Bundle();

        bundle = getIntent().getExtras();

        final String id = intent.getStringExtra("ID");



        queue = Volley.newRequestQueue(this);

        url = getResources().getString(R.string.url_base) + "cm/2020-1/product_detail.php?id=" + id;

        request = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(request);

        //propiedad para que el TextView sea scroleable!
        descProduct.setMovementMethod(new ScrollingMovementMethod());


        /*Esta parte es para consumir la descripcion del producto desde url variando el id del final ?id=6541
        */

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(ProductView.this, "error al cargar", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {



            Toast.makeText(ProductView.this, response.getString("name"), Toast.LENGTH_LONG).show();


            nameDesc.setText(response.getString("name"));

            descProduct.setText(response.getString("desc"));



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}




