package com.example.volleyparsepicasso;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorImage extends BaseAdapter {

    Context contexto;
    ArrayList<Imagen> imagenes;

    public ImageView imageView;




    public AdaptadorImage(Context contexto, ArrayList<Imagen> imagenes) {
        this.contexto = contexto;
        this.imagenes = imagenes;

    }

    @Override
    public int getCount() {
        return imagenes.size();
    }

    @Override
    public Object getItem(int position) {
        return imagenes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return imagenes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(contexto);


        Picasso.get()
                .load(contexto.getResources().getString(R.string.url_base)+imagenes.get(position).getThumb_url())
                .into(imageView);

        return imageView;
    }
}
