package com.example.cakestudio;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CakeOpen extends AppCompatActivity {
    private static final String TAG = "CakeOpen";

    Button cartToSelect;
    RecyclerView cartRecycler;
    CartAdapter adapter;
    ArrayList<Cake> cakes,key_cakes;
    DatabaseReference reference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cakeopened);
        Toast.makeText(CakeOpen.this, "Drag to move & swipe to delete", Toast.LENGTH_LONG).show();
        cartRecycler=findViewById(R.id.cartRecycler);

        cartRecycler.setLayoutManager(new LinearLayoutManager(this));
        cakes=new ArrayList<Cake>();
        cartToSelect=findViewById(R.id.cartToSelect);
        cartToSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CakeOpen.this,SelectOpen.class);
                startActivity(intent);
            }
        });
        extractCakes();
        ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int pos_drag=viewHolder.getAdapterPosition();
                int pos_tar=target.getAdapterPosition();
                Collections.swap(cakes,pos_drag,pos_tar);
                adapter.notifyItemMoved(pos_drag,pos_tar);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                cakes.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        });
        helper.attachToRecyclerView(cartRecycler);
    }



    private void extractCakes(){
        reference= FirebaseDatabase.getInstance().getReference("cartcakes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Cake c= dataSnapshot1.getValue(Cake.class);
                    cakes.add(c);
                }
                adapter= new CartAdapter(getApplicationContext(),cakes);
                cartRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CakeOpen.this, "Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }
    


}
