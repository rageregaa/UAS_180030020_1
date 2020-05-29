package com.bh183.wedantakusuma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_buku";
    private final static String TABLE_BUKU ="t_buku";
    private final static String KEY_ID_BUKU = "ID_buku";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_TGL = "Tgl_Terbit";
    private final static String KEY_BUKU = "Buku";
    private final static String KEY_PENERBIT = "Penerbit";
    private final static String KEY_PENULIS = "Penulis";
    private final static String KEY_BAHASAN = "Bahasan";
    private Context context;


    public DatabaseHandler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BUKU = "CREATE TABLE " + TABLE_BUKU
                + "(" + KEY_ID_BUKU + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_TGL + " DATE, "
                + KEY_BUKU + " TEXT, " + KEY_PENERBIT + " TEXT, "
                + KEY_PENULIS + " TEXT, " + KEY_BAHASAN + " TEXT);";

        db.execSQL(CREATE_TABLE_BUKU);
        inisialisasiBukuAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_BUKU;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahBuku(Buku dataBuku){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_TGL, dataBuku.getTerbit());
        cv.put(KEY_BUKU, dataBuku.getBuku());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_BAHASAN, dataBuku.getBahasan());


        db.insert(TABLE_BUKU, null, cv);
        db.close();
    }

    public void tambahBuku(Buku dataBuku, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_TGL, dataBuku.getTerbit());
        cv.put(KEY_BUKU, dataBuku.getBuku());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_BAHASAN, dataBuku.getBahasan());
        db.insert(TABLE_BUKU, null, cv);
    }

    public void editBuku(Buku dataBuku){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_TGL, dataBuku.getTerbit());
        cv.put(KEY_BUKU, dataBuku.getBuku());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_BAHASAN, dataBuku.getBahasan());

        db.update(TABLE_BUKU, cv, KEY_ID_BUKU + "=?", new String[]{String.valueOf(dataBuku.getIdBuku())});
        db.close();
    }

    public void hapusBuku(int idBuku){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_BUKU, KEY_ID_BUKU + "=?", new String[]{String.valueOf(idBuku)});
        db.close();
    }

    public ArrayList<Buku> getAllBuku(){
        ArrayList<Buku> dataBuku = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BUKU;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if(csr.moveToFirst()){
            do {
                Buku tempBuku = new Buku(
                        csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6)


                );

                dataBuku.add(tempBuku);
            } while (csr.moveToNext());
        }

        return dataBuku;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiBukuAwal(SQLiteDatabase db){
        int idBuku = 0;

        // Menambahkan data film ke 1
        Buku buku1 = new Buku(
                idBuku,
                "Trisandhya & Panca Sembah",
                "1995",
                storeImageFile(R.drawable.buku1),
                "Percetakan Offset & Toko Buku Ria",
                "Penulis Nyoka",
                "Membahas mengenai tata cara dalam melakukan Tri Sandhya, Panca Sembah dan persembahyangan lainnya"
        );

        tambahBuku(buku1, db);
        idBuku++;

        // Menambahkan data film ke 2
        Buku buku2 = new Buku(
                idBuku,
                "E-Bussiness & E-Commerce",
                "September 2013",
                storeImageFile(R.drawable.buku2),
                "Penerbit Andi & STIKOM Bali",
                "Penulis Candra Ahmadi & Dadang Hermawan",
                "Membahas mengenai pengaruh TIK pada E-Commerce dan E-Bussiness, lingkungannya, serta strategi dan implementasinya"
        );

        tambahBuku(buku2, db);
        idBuku++;

        // Menambahkan data film ke 3
        Buku buku3 = new Buku(
                idBuku,
                "Komunikasi Data",
                "2018",
                storeImageFile(R.drawable.buku3),
                "Penerbit Gava Media",
                "Penulis Ikhwan Taufik",
                "Membahas tentang sejarah sistem telekomunikasi serta konsep protokol dan standar komunikasi data"
        );

        tambahBuku(buku3, db);
        idBuku++;

    }
}
