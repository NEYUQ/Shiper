package com.example.bottomnavigationbardeprecated.Model;

public class TaiXe {
    private String MaNhanVien;
    private String DiaChi;
    private String SoDienThoai;
    private String TenNhanVien;
    private String TaiKhoan;
    private String MatKhau;

    public TaiXe() {
    }

    public TaiXe(String maNhanVien, String diaChi, String soDienThoai, String tenNhanVien, String taiKhoan, String matKhau) {
        MaNhanVien = maNhanVien;
        DiaChi = diaChi;
        SoDienThoai = soDienThoai;
        TenNhanVien = tenNhanVien;
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        TenNhanVien = tenNhanVien;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
