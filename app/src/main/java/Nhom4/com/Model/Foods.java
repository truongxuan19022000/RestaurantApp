package Nhom4.com.Model;

public class Foods {
    private int mID;
    private String maSP;
    private String tenSP;
    private String category;
    private String soLuong;
    private String Mota;
    private int Gia;
    public Foods() {
    }
    public Foods(String tenSP) {
        this.tenSP = tenSP;
    }
    public Foods(int mID, String maSP, String tenSP, String category, String soLuong, String mota, int gia) {
        this.mID = mID;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.category = category;
        this.soLuong = soLuong;
        Mota = mota;
        Gia = gia;
    }

    public Foods(String maSP, String tenSP, String soLuong, String mota, int gia, String category) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        Mota = mota;
        Gia = gia;
        this.category=category;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return tenSP;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + mID;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Foods other = (Foods) obj;
        if (mID != other.mID)
            return false;
        return true;
    }
}
