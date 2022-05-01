package com.example.retrofitdemo.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.model.CyrptoModel;

import java.util.ArrayList;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.RowHolder> {

    ArrayList<CyrptoModel> list;
    private String[] colors={"#d3ffce","#8fce00","#fe988d","#ffa743","#85b6bb"};

    public ModelAdapter(ArrayList<CyrptoModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.rowlayout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(list.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder{
        TextView textName,textPrice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(CyrptoModel model, String[] colors, Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position%5]));
            textName=itemView.findViewById(R.id.curText);
            textPrice=itemView.findViewById(R.id.priceText);
            textName.setText(model.currency);
            textPrice.setText(model.price);


        }
    }
}
