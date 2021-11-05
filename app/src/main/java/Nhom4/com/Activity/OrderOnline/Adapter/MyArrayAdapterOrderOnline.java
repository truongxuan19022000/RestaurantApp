package Nhom4.com.Activity.OrderOnline.Adapter;


import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Nhom4.com.Activity.OrderOnline.OnItemClickListener;
import Nhom4.com.Model.KhachHang;
import Nhom4.com.R;

public class MyArrayAdapterOrderOnline extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static int TYPE_ONE = 1;
    private static int TYPE_TWO= 2;
    ArrayList<KhachHang> list = null;
    private OnItemClickListener clickItem;
    Activity context = null;

    public MyArrayAdapterOrderOnline(ArrayList<KhachHang> list, OnItemClickListener clickItem){
        this.list = list;
        this.clickItem = clickItem;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        if(TYPE_ONE == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_info_customer_order, parent,false);
            return new VIEWONE(view);
        }
        else if(TYPE_TWO == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_foods_order,parent, false);
            return new VIEWTWO(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        KhachHang k = list.get(position);
        if(k == null)
            return;
        if(TYPE_ONE == holder.getItemViewType()){
            VIEWONE one = (VIEWONE) holder;
            one.name.setText(k.getTenKH() + "");
            one.phone.setText(k.getSdt() + "");
            one.address.setText(k.getDiaChi() + "");
            one.countTable.setText(k.getSoBan() + "");
            one.food.setText(k.getTenMon() + "");
            one.count.setText(k.getSoLuong() + "");
            one.price.setText(k.formatPrice(k.getGia()));
            one.money.setText(k.formatPrice(k.thanhTien()));
            ((VIEWONE) holder).itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        clickItem.OnItemClick(position);
                    }catch (Exception e){
                        Log.e("Error" + position, "Pos = " + position);
                    }
                }
            });
            ((VIEWONE) holder).itemLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    clickItem.OnLongItemClick( k, position);
                    return false;
                }
            });
        }
        else if(TYPE_TWO == holder.getItemViewType()){
            VIEWTWO two = (VIEWTWO) holder;
            two.food.setText(k.getTenMon() + "");
            two.count.setText(k.getSoLuong() + "");
            two.price.setText(k.formatPrice(k.getGia()));
            two.money.setText(k.formatPrice(k.thanhTien()));
            ((VIEWTWO) holder).item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        clickItem.OnItemClick(position);
                    }catch (Exception e){
                        Log.e("Error" + position, e.toString());
                    }
                }
            });
            ((VIEWTWO) holder).item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    clickItem.OnLongItemClick(k, position);
                    return false;
                }
            });
            if(position % 2 != 0)
                ((VIEWTWO) holder).item.setBackgroundColor(Color.YELLOW);
        }

    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        KhachHang k = list.get(position);
        if(k.isFirst() == 1)
            return TYPE_ONE;
        else
            return TYPE_TWO;
    }

    public class VIEWONE extends RecyclerView.ViewHolder{
        TextView name, phone, address, countTable, food, count, price, money;
        TableLayout itemLayout;
        public VIEWONE(@NonNull View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.itemLayout);
            name   = itemView.findViewById(R.id.name);
            phone  = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            countTable = itemView.findViewById(R.id.countTable);
            food = itemView.findViewById(R.id.food);
            count = itemView.findViewById(R.id.count);
            price = itemView.findViewById(R.id.price);
            money = itemView.findViewById(R.id.money);
        }
    }
    public class VIEWTWO extends RecyclerView.ViewHolder{
        TextView food, count, price, money;
        TableRow item;
        public VIEWTWO(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            food = itemView.findViewById(R.id.food);
            count = itemView.findViewById(R.id.count);
            price = itemView.findViewById(R.id.price);
            money = itemView.findViewById(R.id.money);
        }
    }
}
