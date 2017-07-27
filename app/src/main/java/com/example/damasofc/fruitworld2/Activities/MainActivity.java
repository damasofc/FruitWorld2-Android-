package com.example.damasofc.fruitworld2.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.damasofc.fruitworld2.Adapters.MyAdapter;
import com.example.damasofc.fruitworld2.Models.Fruit;
import com.example.damasofc.fruitworld2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int cont = 1;
    private List<Fruit> frutas;
    private	RecyclerView.Adapter	mAdapter;
    private	RecyclerView.LayoutManager	mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frutas = new ArrayList<>();
        frutas.add(new Fruit("Strawberry","Strawberry description",R.drawable.strawberry_bg));
        frutas.add(new Fruit("Orange","Orange description",R.drawable.orange_bg));
        frutas.add(new Fruit("Apple","Apple description",R.drawable.apple_bg));
        frutas.add(new Fruit("Banana","Banana description",R.drawable.banana_bg));
        frutas.add(new Fruit("Cherry","Cherry description",R.drawable.cherry_bg));
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        mAdapter = new MyAdapter(frutas, R.layout.activity_item__model, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {

            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_fruit:
                frutas.add(new Fruit("Plum "+ cont++,"Fruit added by the user",R.drawable.plum_bg));
                mAdapter.notifyItemInserted(frutas.size()-1);
                mLayoutManager.scrollToPosition(frutas.size()-1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
