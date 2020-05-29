package com.bh183.wedantakusuma;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgBuku;
    private TextView tvJudul, tvTerbit, tvPenerbit, tvPenulis, tvBahasan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgBuku = findViewById(R.id.iv_buku);
        tvJudul = findViewById(R.id.tv_judul_buku);
        tvTerbit = findViewById(R.id.tv_tgl_terbit);
        tvPenerbit = findViewById(R.id.tv_penerbit);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvBahasan = findViewById(R.id.tv_bahasan);

        Intent terimaData = getIntent();
        tvJudul.setText(terimaData.getStringExtra("JUDUL"));
        tvTerbit.setText(terimaData.getStringExtra("TERBIT"));
        tvPenerbit.setText(terimaData.getStringExtra("PENERBIT"));
        tvPenulis.setText(terimaData.getStringExtra("PENULIS"));
        tvBahasan.setText(terimaData.getStringExtra("BAHASAN"));
        String imgLocation = terimaData.getStringExtra("BUKU");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgBuku.setImageBitmap(bitmap);
            imgBuku.setContentDescription(imgLocation);
        } catch (FileNotFoundException er) {
            er.printStackTrace();
            Toast.makeText(this, "Gagal dalam mengambil gambar", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}

