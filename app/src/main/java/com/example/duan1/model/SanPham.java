package com.example.duan1.model;

public class SanPham {
     String id;
     String ha;
     String masanpham;
     String tensp;
     String tenloai;
     int gia;
    Double soluong;
    String mota;

    public SanPham() {
    }

    public SanPham(String ha, String masanpham, String tensp, String tenloai, int gia, Double soluong, String mota) {
        this.ha = ha;
        this.masanpham = masanpham;
        this.tensp = tensp;
        this.tenloai = tenloai;
        this.gia = gia;
        this.soluong = soluong;
        this.mota = mota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHa() {
        return ha;
    }

    public void setHa(String ha) {
        this.ha = ha;
    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public Double getSoluong() {
        return soluong;
    }

    public void setSoluong(Double soluong) {
        this.soluong = soluong;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
