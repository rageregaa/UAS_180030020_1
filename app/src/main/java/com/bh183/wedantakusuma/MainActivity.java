package com.bh183.wedantakusuma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Buku> dataBuku = new ArrayList<>();
    private RecyclerView rvBuku;
    private BukuAdapter BukuAdapter;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBuku = findViewById(R.id.rv_tampil_buku);
        dbHandler = new DatabaseHandler(this);
        tampilDataBuku();
    }

    private void tampilDataBuku(){
        dataBuku = dbHandler.getAllBuku();
        BukuAdapter = new BukuAdapter(this, dataBuku);
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(MainActivity.this);
        rvBuku.setLayoutManager(layManager);
        rvBuku.setAdapter(BukuAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.item_menu_tambah){
            Intent bukaInput = new Intent(getApplicationContext(), InputActivity.class);
            bukaInput.putExtra("OPERASI", "insert");
            startActivity(bukaInput);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilDataBuku();
    }
}
