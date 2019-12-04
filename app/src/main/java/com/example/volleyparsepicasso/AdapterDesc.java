package com.example.volleyparsepicasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterDesc extends RecyclerView.Adapter<AdapterDesc.DescViewHolder> {
    private Context mContext;
    private List<Description> descriptionList;



    class DescViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombre, textViewAltaprot;
        //ImageView imageView;



        public DescViewHolder(View itemView) {
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.textViewNameProduct);
            textViewAltaprot = itemView.findViewById(R.id.textViewDescription);

        }
    }


    public AdapterDesc(Context mContext, List<Description> usuariosList) {
        this.mContext = mContext;
        this.descriptionList = usuariosList;

    }


    @Override
    public DescViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflanter = LayoutInflater.from(mContext);
        View view = inflanter.inflate(R.layout.list_usuarios, null);

        return new DescViewHolder(view);

    }


    @Override
    public void onBindViewHolder(DescViewHolder holder, int position) {

        Description description = descriptionList.get(position);


        holder.textViewNombre.setText(description.getNombre());
        holder.textViewAltaprot.setText(description.getAltaprot());


    }

    @Override
    public int getItemCount() {
        return descriptionList.size();
    }


}


