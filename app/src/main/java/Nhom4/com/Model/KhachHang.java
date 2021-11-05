package Nhom4.com.Model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class KhachHang {

    String  tenKH, sdt, diaChi, tenMon;
    int id, soBan, soLuong;
    double gia;
    int isFirst;
    public KhachHang() { }

    public KhachHang(int isFirst, int  id, String tenKH, String sdt, String diaChi, String tenMon, int soBan, int soLuong, double gia ) {
        this.isFirst = isFirst;
        this.id = id;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.tenMon = tenMon;
        this.soLuong = soLuong;
        this.soBan = soBan;
        this.gia = gia;
        this.diaChi = diaChi;
    }
    public KhachHang(int isFirst, String tenKH, String sdt, String diaChi, String tenMon, int soBan, int soLuong, double gia ) {
        this.isFirst = isFirst;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.tenMon = tenMon;
        this.soLuong = soLuong;
        this.soBan = soBan;
        this.gia = gia;
        this.diaChi = diaChi;
    }
    public int isFirst() {
        return isFirst;
    }

    public void setFirst(int first) {
        this.isFirst = first;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoBan() {
        return soBan;
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public double thanhTien(){
        return soLuong*gia;
    }
    public String formatPrice(double number){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(number);
        return formattedNumber;
    }

}
