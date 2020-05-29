package com.bh183.wedantakusuma;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.BukuViewHolder> {

    private Context context;
    private ArrayList<Buku> dataBuku;


    public BukuAdapter(Context context, ArrayList<Buku> dataBuku) {
        this.context = context;
        this.dataBuku = dataBuku;
    }

    @NonNull
    @Override
    public BukuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_buku, parent, false);
        return new BukuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BukuViewHolder holder, int position) {
        Buku tempBuku = dataBuku.get(position);
        holder.idBuku = tempBuku.getIdBuku();
        holder.judul.setText(tempBuku.getJudul());
        holder.bahasan.setText(tempBuku.getBahasan());
        holder.terbit.setText(tempBuku.getTerbit());
        holder.buku = tempBuku.getBuku();
        holder.penerbit.setText(tempBuku.getPenerbit());
        holder.penulis.setText(tempBuku.getPenulis());


        try {
            File file = new File(holder.buku);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgBuku.setImageBitmap(bitmap);
            holder.imgBuku.setContentDescription(holder.buku);
        } catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "Gagal mengambil gambar dalam media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {

        return dataBuku.size();
    }

    public class BukuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public int idBuku;
        private ImageView imgBuku;
        private TextView judul;
        private TextView bahasan;
        private TextView penerbit;
        private TextView penulis;
        private TextView terbit;
        private String buku;

        public BukuViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBuku = itemView.findViewById(R.id.iv_buku);
            judul = itemView.findViewById(R.id.tv_judul_buku);
            terbit = itemView.findViewById(R.id.tv_tgl_terbit);
            penerbit = itemView.findViewById(R.id.tv_penerbit);
            penulis = itemView.findViewById(R.id.tv_penulis);

            bahasan = itemView.findViewById(R.id.tv_bahasan);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent bukaBuku = new Intent(context, TampilActivity.class);
            bukaBuku.putExtra("ID", idBuku);
            bukaBuku.putExtra("JUDUL", judul.getText().toString());
            bukaBuku.putExtra("TERBIT", terbit.getText().toString());
            bukaBuku.putExtra("BUKU", buku);
            bukaBuku.putExtra("PENERBIT", penerbit.getText().toString());
            bukaBuku.putExtra("PENULIS", penulis.getText().toString());
            bukaBuku.putExtra("BAHASAN", bahasan.getText().toString());
            context.startActivity(bukaBuku);
        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI", "update");
            bukaInput.putExtra("ID", idBuku);
            bukaInput.putExtra("JUDUL", judul.getText().toString());
            bukaInput.putExtra("TERBIT", terbit.getText().toString());
            bukaInput.putExtra("BUKU", buku);
            bukaInput.putExtra("PENERBIT", penerbit.getText().toString());
            bukaInput.putExtra("PENULIS", penulis.getText().toString());
            bukaInput.putExtra("BAHASAN", bahasan.getText().toString());
            context.startActivity(bukaInput);
            return true;
        }
    }
}
