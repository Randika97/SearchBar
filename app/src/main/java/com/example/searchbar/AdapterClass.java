package com.example.searchbar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{

    ArrayList<Tourist> list;
    private RecyclerViewClickListener mListener;
    public AdapterClass(ArrayList<Tourist> list,RecyclerViewClickListener listener)
    {this.list=list; mListener = listener;}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder, parent, false);
        return new MyViewHolder(view,mListener);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //Picasso.with(context).load(list.get(position).getCountryImg()).into(holder.countryImg);
        holder.countryName.setText(list.get(position).getCountryName());
        holder.desc.setText(list.get(position).getCapitalCity());
    }

    @Override
    public int getItemCount() {return list.size();}

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView countryName,desc;
        public ImageView countryImg;
        private RecyclerViewClickListener mListener;
        public MyViewHolder(@NonNull View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            countryImg = (ImageView)itemView.findViewById(R.id.countryImg);
            countryName =(TextView)itemView.findViewById(R.id.countryName);
            desc=(TextView) itemView.findViewById(R.id.desciption);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }
    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }
}
