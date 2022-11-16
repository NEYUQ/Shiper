package com.example.bottomnavigationbardeprecated.Model;

public class Food {
    private String ID;
    private String TenMonAn;
    private String Loai;
    private String Gia;
    private String HinhAnh;

    public Food() {
    }

    public Food(String ID, String tenMonAn, String loai, String gia, String hinhAnh) {
        this.ID = ID;
        TenMonAn = tenMonAn;
        Loai = loai;
        Gia = gia;
        HinhAnh = hinhAnh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
