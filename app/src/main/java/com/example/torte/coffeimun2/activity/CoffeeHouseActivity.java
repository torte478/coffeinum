package com.example.torte.coffeimun2.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.torte.coffeimun2.DataBaseModel;
import com.example.torte.coffeimun2.R;
import com.example.torte.coffeimun2.adapter.CoffeHouseAdapter;
import com.example.torte.coffeimun2.interfaces.RecyclerViewClickListener;
import com.example.torte.coffeimun2.model.CoffeHouseModel;
import com.example.torte.coffeimun2.model.Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.torte.coffeimun2.AdditiveItemAdapter.WonderfulMagicMethod;

public class CoffeeHouseActivity extends AppCompatActivity {

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_house);
        LoadMarkers("54321");

    }



    private void LoadMarkers(String id)

    {    ArrayList<Menu> list = new ArrayList<>();
        ArrayList<String> additives = new ArrayList<>();;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase
                .child(DataBaseModel.COFFEE_HOUSE)
                .orderByChild("id")
                .equalTo(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        CoffeHouseModel coffeeHouse = null;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                             coffeeHouse = snapshot.getValue(CoffeHouseModel.class);
                            for (String key: coffeeHouse.menu.keySet()) {
                                Log.d("key : " , key);
                                Log.d("value : " , coffeeHouse.menu.get(key).name);
                                list.add(coffeeHouse.menu.get(key));
                            }
                            recyclesView(list);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Error, when load markers" , Toast.LENGTH_SHORT).show();

                    }
                });
    }
    private void recyclesView(ArrayList<Menu> list) {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = (view, menu) -> {
            WonderfulMagicMethod(menu, getApplicationContext(), this);
        };
        CoffeHouseAdapter adapter = new CoffeHouseAdapter(getApplicationContext(), list, listener);
        recyclerView.setAdapter(adapter);
    }
}
