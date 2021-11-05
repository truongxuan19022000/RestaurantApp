package Nhom4.com.Activity.OrderByTable;


import java.util.ArrayList;

import Nhom4.com.Model.OrderByTable;

public class Catalog_Bac {
    String maDM, tenDM;
    ArrayList<OrderByTable> listProduct = null;

    public Catalog_Bac(String maDM, String tenDM) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.listProduct = new ArrayList<OrderByTable>();
    }
    public boolean isProduct(OrderByTable p){
        for (OrderByTable item: listProduct){
            if(item.getMaSP().trim().equalsIgnoreCase(p.getMaSP().trim()))
                return true;
        }
        return false;
    }
    public boolean addProduct(OrderByTable p){
        if(!isProduct(p)){
            listProduct.add(p);
            return true;
        }
        return false;
    }



    public ArrayList<OrderByTable> getDsSanPham() {return listProduct;}

    @Override
    public String toString() {
        return tenDM;
    }
}
