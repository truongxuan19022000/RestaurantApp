package Nhom4.com.Model;

public class OrderByTable {
    String maSP, ban, ghiChu;
    int soLuong, gia;

    public OrderByTable() {
    }

    public OrderByTable(String maSP, String ban, String ghiChu, int soLuong, int gia) {
        this.maSP = maSP;
        this.ban = ban;
        this.ghiChu = ghiChu;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public OrderByTable(String maSP, String ban, String ghiChu, int soLuong) {
        this.maSP = maSP;
        this.ban = ban;
        this.ghiChu = ghiChu;
        this.soLuong = soLuong;
    }


    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int gia() {
        return gia;
    }

    public void gia(int gia) {
        this.gia = gia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "Món ăn : " + maSP + "\n" +"Bàn : " + ban + "\n" +"Số lượng : " + soLuong + "\n" + "Tổng : " + gia + "\n" + "Ghi chú : " + ghiChu ;
    }
}
