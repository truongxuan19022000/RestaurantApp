package Nhom4.com.Model;

public class GoiThem {
    private String soBan;
    private String tenDoUong;
    private int soluongNuoc;
    private String tenDoAnKem;
    private int soluongDoAn;
    private int id;

    public static final int GIA=15000;


//    public GoiThem(  int  id, String soBan, String tenDoUong, int soluongNuoc, String tenDoAnKem, int soluongDoAn ) {
//        this.soBan = soBan;
//        this.tenDoUong = tenDoUong;
//        this.soluongNuoc = soluongNuoc;
//        this.tenDoAnKem = tenDoAnKem;
//        this.soluongDoAn = soluongDoAn;
//    }
    public GoiThem(int id, String soBan, String tenDoUong, int soluongNuoc, String tenDoAnKem, int soluongDoAn) {
        this.id=id;
        this.soBan = soBan;
        this.tenDoUong = tenDoUong;
        this.soluongNuoc = soluongNuoc;
        this.tenDoAnKem = tenDoAnKem;
        this.soluongDoAn = soluongDoAn;
}
    public GoiThem(String soBan, String tenDoUong, int soluongNuoc, String tenDoAnKem, int soluongDoAn) {
        this.soBan = soBan;
        this.tenDoUong = tenDoUong;
        this.soluongNuoc = soluongNuoc;
        this.tenDoAnKem = tenDoAnKem;
        this.soluongDoAn = soluongDoAn;
    }


    public GoiThem() {

    }

//    public GoiThem( String string,String string1, int int1, String string2, int int2) {
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getSoluongDoAn() { return soluongDoAn; }

    public void setSoluongDoAn(int soluongDoAn) { this.soluongDoAn = soluongDoAn; }

    public String getTenDoAnKem() {
        return tenDoAnKem;
    }

    public void setTenDoAnKem(String tenDoAnKem) {
        this.tenDoAnKem = tenDoAnKem;
    }

    public int getSoluongNuoc() { return soluongNuoc; }

    public void setSoLuongNuoc(int soluongNuoc) { this.soluongNuoc = soluongNuoc; }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    public String getSoBan() {
        return soBan;
    }

    public void setSoBan(String soBan) {
        this.soBan = soBan;
    }

    public long tinhTT(){
        return ((GIA*getSoluongDoAn())+(GIA*getSoluongNuoc()));
    }

    public String toString(){
        return "Đồ uống: "+this.tenDoUong+"\n"+
                "Số lượng: " +this.soluongNuoc+"\n"+
                "Đồ ăn kèm: "+this.tenDoAnKem+"\n"+
                "Số lượng: "+this.soluongDoAn+"\n"+
                "Tổng tiền đồ gọi thêm: "+this.tinhTT()+"đ";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        GoiThem other = (GoiThem) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
