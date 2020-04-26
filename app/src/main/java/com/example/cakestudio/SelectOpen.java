package com.example.cakestudio;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class SelectOpen extends AppCompatActivity {


    RecyclerView selectcart;
    SelectAdapter adapter;
    ArrayList<Cake> cakes, key_cakes;
    DatabaseReference reference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedcart);
        Toast.makeText(SelectOpen.this, "Drag to move & swipe to delete", Toast.LENGTH_LONG).show();
        selectcart = findViewById(R.id.selectcart);
        selectcart.setLayoutManager(new LinearLayoutManager(this));
        cakes = new ArrayList<Cake>();

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
//                adapter.notifyDataSetChanged();
//                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("selectcakes")
//                        .child(cakes.get(viewHolder.getAdapterPosition()).getCake_name());
//                databaseReference.removeValue();
                adapter.notifyDataSetChanged();
            }
        });
        helper.attachToRecyclerView(selectcart);
    }

    private void extractCakes() {
        reference = FirebaseDatabase.getInstance().getReference("selectcakes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Cake c = dataSnapshot1.getValue(Cake.class);
                    cakes.add(c);
                }
                adapter = new SelectAdapter(getApplicationContext(), cakes);
                selectcart.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SelectOpen.this, "Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
