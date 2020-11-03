package com.example.a12drawerlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawLayout;
    private Fruit[] fruits = {new Fruit("Apple",R.drawable.local),new Fruit("banana",R.drawable.monitor),new Fruit("Apple",R.drawable.local),new Fruit("banana",R.drawable.monitor)};
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.local);
        }
        navView.setCheckedItem(R.id.nav_local);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawLayout.closeDrawers();
                return true;
            }
        });

//        给悬浮按钮加点击事件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                Log.d("=====", "onClick: 点击悬浮按钮");
                Snackbar.make(view,"Data deleted", Snackbar.LENGTH_INDEFINITE).setAction("Undo",new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(MainActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                    }

                }).show();
            }
        });

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MainActivity", "onCreateOptionsMenu: addMenu");
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.local:
                Toast.makeText(this, "local", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_log:
                Toast.makeText(this, "user_log", Toast.LENGTH_SHORT).show();
                break;
            case R.id.monitor:
                Toast.makeText(this, "monitor", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    public void initFruits() {
        fruitList.clear();
        for (int i = 0; i<50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
}
