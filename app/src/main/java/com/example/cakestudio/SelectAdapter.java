package com.example.cakestudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Cake> cakes;
    Context ctx;
    LayoutInflater inflater;

    public SelectAdapter (Context ctx,List<Cake> cakes){
        this.inflater=LayoutInflater.from(ctx);
        this.ctx=ctx;
        this.cakes=cakes;

    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.selectcard,parent,false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        final Cake cake=cakes.get(position);

        holder.cakename.setText(cake.getCake_name());
        holder.cakeprice.setText(cake.getPrice());
        holder.cakeweight.setText(cake.getWeight());
        //imageview
        Picasso.get().load(cakes.get(position).getImgUrl()).into(holder.cakeimage);

    }

    @Override
    public int getItemCount() {
        return cakes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cakename,cakeprice,cakeweight;
        ImageView cakeimage;
        Button button;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cakename=itemView.findViewById(R.id.cakename);
            cakeprice=itemView.findViewById(R.id.cakeprice);
            cakeweight=itemView.findViewById(R.id.cakeweight);
            cakeimage=itemView.findViewById(R.id.cakeimage);
            button=itemView.findViewById(R.id.addButton);


        }
    }
}
