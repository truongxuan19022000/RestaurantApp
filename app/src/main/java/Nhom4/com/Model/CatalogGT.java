package Nhom4.com.Model;

import java.util.ArrayList;

public class CatalogGT {
    String maDMBan, tenDMBan;
    ArrayList<GoiThem> list = null;

    public CatalogGT(String maDM, String tenDM) {
        this.maDMBan = maDM;
        this.tenDMBan = tenDM;
        this.list = new ArrayList<GoiThem>();
    }
    public boolean isGoiThem(GoiThem GT){
        for (GoiThem item: list){
            if(item.getTenDoUong().trim().equalsIgnoreCase(GT.getTenDoUong().trim()))
                return true;
        }
        return false;
    }
    public boolean addProduct(GoiThem GT){
        if(!isGoiThem(GT)){
            list.add(GT);
            return true;
        }
        return false;
    }



    public ArrayList<GoiThem> getDsSanPham() {return list;}

    @Override
    public String toString() {
        return tenDMBan;
    }
}
