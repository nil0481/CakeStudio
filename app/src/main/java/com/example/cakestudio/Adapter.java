package com.example.cakestudio;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Cake> cakes;
    ArrayList<Cake> cartcakes;
    Context ctx;

    FirebaseDatabase rootnode;
    DatabaseReference reference;
    public Adapter (Context ctx,List<Cake> cakes){
        this.inflater=LayoutInflater.from(ctx);
        this.ctx=ctx;
        this.cakes=cakes;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_file,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final Cake cake=cakes.get(i);
        holder.cakename.setText(cake.getCake_name());
        holder.cakeprice.setText(cake.getPrice());
        holder.cakeweight.setText(cake.getWeight());
        //imageview
        Picasso.get().load(cakes.get(i).getImgUrl()).into(holder.cakeimage);

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode=FirebaseDatabase.getInstance();
                String cake_name=cake.getCake_name();
                String weight=cake.getWeight();
                String price=cake.getPrice();
                String imgUrl=cake.getImgUrl();
                reference=rootnode.getReference("cartcakes").child(cake_name);
                Cake cakecart=new Cake(cake_name,weight,price,imgUrl);
                reference.setValue(cakecart);
//                Log.d("tagg", "onClick: "+cake.getCake_name());

            }
        });

    }

    @Override
    public int getItemCount() {
        return cakes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView cakename,cakeprice,cakeweight;
        ImageView cakeimage;
        Button addButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cakename=itemView.findViewById(R.id.cakename);
            cakeprice=itemView.findViewById(R.id.cakeprice);
            cakeweight=itemView.findViewById(R.id.cakeweight);
            cakeimage=itemView.findViewById(R.id.cakeimage);
            addButton=itemView.findViewById(R.id.addButton);


        }
    }
}
