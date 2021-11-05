package Nhom4.com.Model;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Nhom4.com.Model.Foods;


public class categories extends AppCompatActivity {
    private String maDM,tenDM;
    private ArrayList<Foods> dsSanPham=null;
    public categories(String ma, String ten){
        maDM=ma;tenDM=ten;
        dsSanPham=new ArrayList<Foods>();
    }
    //    Kiểm tra sp đã tồn tại trong danh mục chưa
    public boolean KiemTRaSp(Foods p){
        for (Foods p1:dsSanPham){
            if(p1.getTenSP().trim().equalsIgnoreCase(p.getTenSP().trim()))
                return true;
        }
        return false;
    }
    //Them 1 sp vào danh mục
    public boolean addSP(Foods p){
        boolean kiemtra=KiemTRaSp(p);
        if(!kiemtra){
            dsSanPham.add(p);
            return true;
        }
        else return false;
    }
    public ArrayList<Foods> getDsSanPham(){
        return dsSanPham;
    }


    @Override
    public String toString() {
        return tenDM;
    }
}
