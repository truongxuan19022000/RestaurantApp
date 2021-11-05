package Nhom4.com.Model;

public class Report {
    private int reportID;
    private String foodID;
    private String name;
    private String danhgia;
    private String nhanxet;

    public Report(){}

    @Override
    public String toString() {
        return "Tên khách: " + name + "\n" +
                "Đánh giá: " + danhgia + "\n" +
                "Nhận xét: " + nhanxet;
    }

    public Report(int reportID, String foodID, String name, String danhgia, String nhanxet) {
        this.reportID = reportID;
        this.foodID = foodID;
        this.name = name;
        this.danhgia = danhgia;
        this.nhanxet = nhanxet;
    }

    public Report(String foodID, String name, String danhgia, String nhanxet) {
        this.foodID = foodID;
        this.name = name;
        this.danhgia = danhgia;
        this.nhanxet = nhanxet;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public String getNhanxet() {
        return nhanxet;
    }

    public void setNhanxet(String nhanxet) {
        this.nhanxet = nhanxet;
    }
}
