package com.example.duan1.model;

public class HoaDon {
     String id;
     String imges;
     String tensanpham;
     String soluongsanpham;
     String tongtien;
     String Giagocsanpham;
     String maSanPham;

    public HoaDon() {
    }

    public HoaDon(String imges, String tensanpham, String soluongsanpham, String tongtien, String giagocsanpham, String maSanPham) {
        this.imges=imges;
        this.tensanpham = tensanpham;
        this.soluongsanpham = soluongsanpham;
        this.tongtien = tongtien;
        Giagocsanpham = giagocsanpham;
        this.maSanPham = maSanPham;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImges() {
        return imges;
    }

    public void setImges(String imges) {
        this.imges = imges;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getSoluongsanpham() {
        return soluongsanpham;
    }

    public void setSoluongsanpham(String soluongsanpham) {
        this.soluongsanpham = soluongsanpham;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getGiagocsanpham() {
        return Giagocsanpham;
    }

    public void setGiagocsanpham(String giagocsanpham) {
        Giagocsanpham = giagocsanpham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
}
