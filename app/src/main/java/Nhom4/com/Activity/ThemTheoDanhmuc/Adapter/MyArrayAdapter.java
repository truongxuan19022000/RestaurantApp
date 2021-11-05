package Nhom4.com.Activity.ThemTheoDanhmuc.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import Nhom4.com.Model.Foods;
import Nhom4.com.R;

public class MyArrayAdapter extends ArrayAdapter<Foods> {
    Activity context=null;
    ArrayList<Foods> myArray=null;
    int layoutld;

    public MyArrayAdapter(Activity  context, int layoutld, ArrayList<Foods> arr) {
        super(context, layoutld, arr);
        this.context=context;
        this.layoutld=layoutld;
        this.myArray=arr;
    }
    @NonNull
    @Override
    //Tạo 1 lớp custom view ở listview
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        convertView=inflater.inflate(layoutld,null);
        if(myArray.size()>0 && position>=0){
            //dòng lệnh lấy textview ra để hiện thị mã và tên lên
            final Spinner spinner=convertView.findViewById(R.id.spinner);
            final TextView txtMaMon,txtTenMon,txtMota;
            txtTenMon=convertView.findViewById(R.id.txtTenMon);
         /*   txtTenmon=convertView.findViewById(R.id.txtTenmon);*/
            txtMota=convertView.findViewById(R.id.txtMota);
            final Foods food=myArray.get(position);
            txtTenMon.setText(food.getTenSP()+"("+food.getSoLuong()+")");
            txtMota.setText("MS"+food.getmID()+"("+food.getMota()+")"+",Giá:"+food.getGia()+"$");
            //Lấy imgaveow ra để thiết lập hình ảnh cho đúng nam hay nữ
           /* final ImageView imgitem;
            imgitem=convertView.findViewById(R.id.imgItem);
            try {
                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //Lấy giá trị spinner
                        String value = spinner.getSelectedItem().toString();
                        if(value.equals("Phở"))
                            imgitem.setImageResource(R.drawable.pho);
                        else if(value.equals("Cơm rang")) //neu la nam
                            imgitem.setImageResource(R.drawable.comrang);
                        else if (value.equals("Mì sào")){
                            imgitem.setImageResource(R.drawable.misao);
                        }else if (value.equals("Gọi thêm")){
                            imgitem.setImageResource(R.drawable.misao);
                        }
                        else {
                            imgitem.setImageResource(R.drawable.pho);
                        }
                    }
                });
            }catch (Exception e){
                Log.e("Lỗi",e.toString());
            }*/


        }
        return convertView;
        //trả về view này là trả về thông số thay đổi
    }
}
