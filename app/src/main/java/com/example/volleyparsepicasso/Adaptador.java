package com.example.volleyparsepicasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.UsuariosViewHolder> {

    private Context mContext;
    private List<Productos> productosList;


    //por si ocupaba dar click en el cardView o alguna otra posición en el que se pudiera agragar un item
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;

    }


    public static class UsuariosViewHolder extends RecyclerView.ViewHolder  {

        TextView textViewNombre, textViewAltaprot, textViewPrecio, textViewEnvio;
        //Button buttonVer;
        ImageView imageView;
        public ImageView imageViewRight;


        public UsuariosViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewAltaprot = itemView.findViewById(R.id.textViewProvider);
            textViewPrecio = itemView.findViewById(R.id.textViewPrice);
            textViewEnvio = itemView.findViewById(R.id.textViewEnvio);
           // buttonVer = itemView.findViewById(R.id.buttonVer);
            imageView = itemView.findViewById(R.id.profile_image);
            imageViewRight = itemView.findViewById(R.id.imageViewRight);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });


        }

    }


    public Adaptador(Context mContext, List<Productos> productosList) {
        this.mContext = mContext;
        this.productosList = productosList;

    }


    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflanter = LayoutInflater.from(mContext);
        View view = inflanter.inflate(R.layout.list_usuarios, null);

        return new UsuariosViewHolder(view, mListener);

    }


    @Override
    public void onBindViewHolder(UsuariosViewHolder holder, int position) {

        Productos productos = productosList.get(position);


        Picasso.get()
                .load(productos.getImage())
                .into(holder.imageView);

        holder.textViewNombre.setText(productos.getNombre());
        holder.textViewPrecio.setText(productos.getPrecio());
        holder.textViewEnvio.setText(productos.getEnvio());
        holder.textViewAltaprot.setText(productos.getProvider());






    }

    @Override
    public int getItemCount() {
        return productosList.size();

    }





}
