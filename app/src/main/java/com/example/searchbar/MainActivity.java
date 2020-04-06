package com.example.searchbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    DatabaseReference ref;
    ArrayList<Tourist> list;
    RecyclerView recyclerView;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref= FirebaseDatabase.getInstance().getReference().child("Country");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);
    }
    AdapterClass.RecyclerViewClickListener listener = new AdapterClass.RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra("CountryName", list.get(position).getCountryName());
            startActivity(intent);
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        if(ref!=null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {   list= new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Tourist.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(list,listener);
                        recyclerView.setAdapter(adapterClass);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }
        if(searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {return false;}

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }
    private void search(String str){
        ArrayList<Tourist> myList = new ArrayList<>();
        for(Tourist object : list)
        {
            if(object.getCountryName().toLowerCase().contains(str.toLowerCase()))
            {myList.add(object);
            }
        }
        AdapterClass adapterClass = new AdapterClass(myList,listener);
        recyclerView.setAdapter(adapterClass);
    }
}
