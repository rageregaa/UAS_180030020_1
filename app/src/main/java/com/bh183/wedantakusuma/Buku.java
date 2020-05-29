package com.bh183.wedantakusuma;



public class Buku {
    private int idBuku;
    private String judul;
    private String terbit;
    private String buku;
    private String penerbit;
    private String penulis;
    private String bahasan;


    public Buku(int idBuku, String judul, String terbit, String buku, String penerbit, String penulis, String bahasan) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.terbit = terbit;
        this.buku = buku;
        this.penerbit = penerbit;
        this.penulis = penulis;
        this.bahasan = bahasan;
    }



    public Buku(int csrInt, String string, String tempDate, String string1, String string2, String string3, String string4, String string5, String string6) {
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTerbit() {
        return terbit;
    }

    public void setTerbit(String terbit) {
        this.terbit = terbit;
    }

    public String getBuku() {
        return buku;
    }

    public void setBuku(String buku) {
        this.buku = buku;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getBahasan() {
        return bahasan;
    }

    public void setBahasan(String bahasan) {
        this.bahasan = bahasan;
    }
}
