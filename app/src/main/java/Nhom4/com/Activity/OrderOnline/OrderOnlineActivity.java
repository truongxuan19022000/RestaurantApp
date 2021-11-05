package Nhom4.com.Activity.OrderOnline;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Nhom4.com.Activity.OrderOnline.Adapter.MyArrayAdapterOrderOnline;
import Nhom4.com.Activity.Report.ReportActivity;
import Nhom4.com.Activity.TrangChu.MenuBar;
import Nhom4.com.Activity.TrangChu.MenuCategory;
import Nhom4.com.Activity.TrangChu.MenuTable;
import Nhom4.com.Model.KhachHang;
import Nhom4.com.MyDataSQLite;
import Nhom4.com.R;

public class OrderOnlineActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private int posOrder = 0;
    private int id = 0;

    ArrayList<KhachHang> listOrder = new ArrayList<KhachHang>();
    TextView showTongTienOrder;
    MyArrayAdapterOrderOnline adapter;
    RecyclerView listViewOrder = null;
    //    Button btnThem, btnSua, btnThoat;
    EditText  editTenOrder, editSDTOrder,  editDiaChiOrder, editSoLuongOrder, editSoBanOrder, editGiaOrder, editThanhTienOrder;
    Spinner spinnerOrder;
    MyDataSQLite db = null;
    KhachHang k = new KhachHang();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_online);
        this.setTitle("Đặt trước");
        getControlsOrder();
        getSpinnerOrder();
        db = MyDataSQLite.getInstance(this);
        setUpViewPager();
        LinearLayoutManager linearOrder = new LinearLayoutManager(this);
        listViewOrder.setLayoutManager(linearOrder);
        refreshOrder();
        showTongTienOrder.setText("TỔNG TIỀN: " + formatPriceOrder(tongTienOrder()));
        Log.e("cus", "okokok");

    }
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterOrder = getMenuInflater();
        inflaterOrder.inflate(R.menu.menu_order_online, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnThemOrder:
                insertOrder();
                return true;
            case R.id.btnSuaOrder:
                editOrder();
                return true;
            case R.id.btnThoatOrder:
                exitOrder();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void getControlsOrder(){
        editTenOrder = findViewById(R.id.editTenOrder);
        editSDTOrder = findViewById(R.id.editSDTOrder);
        editDiaChiOrder = findViewById(R.id.editDiaChiOrder);
        editSoLuongOrder = findViewById(R.id.editSoLuongOrder);
        editSoBanOrder = findViewById(R.id.editSoBanOrder);
        editGiaOrder = findViewById(R.id.editGiaOrder);
        editThanhTienOrder = findViewById(R.id.editThanhTienOrder);
        spinnerOrder = findViewById(R.id.spinnerOrder);
        

        showTongTienOrder = findViewById(R.id.showTongTienOrder);
        listViewOrder = findViewById(R.id.recyclerview);
    }

    public void getSpinnerOrder(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrder.setAdapter(adapter);
        spinnerOrder.setOnItemSelectedListener(this);
        spinnerOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddFoodPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
    private void AddFoodPrice(){
        try {
            String value = spinnerOrder.getSelectedItem().toString();
            if(value.equals("Phở bò"))
                editGiaOrder.setText("25000");
            else if(value.equals("Phở gà"))
                editGiaOrder.setText("15000");
            else if (value.equals("Phở tái"))
                editGiaOrder.setText("20000");
            else if (value.equals("Phở chín"))
                editGiaOrder.setText("20000");
            else if (value.equals("Cơm dưa bò"))
                editGiaOrder.setText("20000");
            else if (value.equals("Cơm cải bò"))
                editGiaOrder.setText("25000");
            else if (value.equals("Cơm rang thập cẩm"))
                editGiaOrder.setText("30000");
            else if (value.equals("Mì sào hải sản"))
                editGiaOrder.setText("30000");
            else if (value.equals("Mì sào thượng hạng")){
                editGiaOrder.setText("40000");
            }
            else {
                editGiaOrder.setText("35000");
            }
        }catch (Exception e){
            Log.e("Err", e.toString());
        }
    }
    public boolean isSDT(String sdt){
        sdt = editSDTOrder.getText().toString();
        try {
            Double.parseDouble(sdt);
            if(sdt.trim().length() == 10)
                return true;
            return false;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    private void refreshOrder(){
        listOrder = db.getAllOrderOnline();
        adapter = new MyArrayAdapterOrderOnline(listOrder, new OnItemClickListener() {
            @Override
            public void OnItemClick( int posOrder) {
                clickedRowOrder(posOrder);
            }
            @Override
            public void OnLongItemClick(KhachHang k, int pos) {
                deleteOrder(k);
            }
        });
        listViewOrder.setAdapter(adapter);
        try {
            for(int j=0;j<listOrder.size();j++){
                if(listOrder.size() !=0){
                    listOrder.get(0).setFirst(1);
                }
            }
        }catch (Exception e){
            Log.e("full", "full err");
        }
        adapter.notifyDataSetChanged();

    }
    public void deleteOrder(KhachHang k){
        AlertDialog.Builder AlertDialog=new AlertDialog.Builder(this);
        AlertDialog.setTitle("Xoá");
        AlertDialog.setMessage("Bạn có chắc chắn muốn xoá không?");
        AlertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listOrder.remove(posOrder);
                Toast.makeText(OrderOnlineActivity.this, "Xoá thành công ", Toast.LENGTH_LONG).show();
                db.deleteOrderOnline(k);
                refreshOrder();
                showTongTienOrder.setText("TỔNG TIỀN: " + formatPriceOrder(tongTienOrder()));
                try {
                    for(int j=0;j<listOrder.size();j++){
                        if(listOrder.size() !=0){
                            listOrder.get(0).setFirst(1);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }catch (Exception e){
                    Log.e("full", "full err");
                }
            }
        });
        AlertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog.create().show();
    }
public void insertOrder(){
    int isFirst;
    if(listOrder.size() == 0){
        isFirst = 1;
    }
    else
        isFirst = 0;
    try {
        String tenMon = spinnerOrder.getSelectedItem().toString();
        String price = editGiaOrder.getText().toString();
        double gia = Double.parseDouble(price);
        String ten = editTenOrder.getText().toString();
        String sdt = editSDTOrder.getText().toString();
        String diaChi = editDiaChiOrder.getText().toString();
        if( ten.isEmpty()|| sdt.isEmpty()|| diaChi.isEmpty() || editSoBanOrder.getText().toString().isEmpty()||editSoLuongOrder.getText().toString().isEmpty() )
            Toast.makeText(OrderOnlineActivity.this, "Bạn chưa nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
        else{
            if(!isSDT(sdt)){
                Toast.makeText(OrderOnlineActivity.this, "Số điện thoại không hợp lệ!", Toast.LENGTH_LONG).show();
            }else{
                KhachHang k = new KhachHang();
                int soLuong = Integer.parseInt(editSoLuongOrder.getText()+"");
                int soBan = Integer.parseInt(editSoBanOrder.getText()+"");

                k.setFirst(isFirst);
                k.setTenKH(ten);
                k.setSdt(sdt);
                k.setDiaChi(diaChi);
                k.setTenMon(tenMon);
                k.setSoBan(soBan);
                k.setSoLuong(soLuong);
                k.setGia(gia);
                editThanhTienOrder.setText(formatPriceOrder(k.thanhTien()));
                db.insertOrderOnline(k);
                refreshOrder();
                adapter.notifyDataSetChanged();

                showTongTienOrder.setText("TỔNG TIỀN: " + formatPriceOrder(tongTienOrder()));
                Toast.makeText(OrderOnlineActivity.this, "Đặt thành công", Toast.LENGTH_LONG).show();

            }
        }

    }catch (Exception ex){
        Log.e("Error", "Error input");
    }
}
    public double tongTienOrder(){
        double tien = 0;
        for(int i=0; i<listOrder.size(); i++){
            tien += listOrder.get(i).thanhTien();
        }
        return tien;
    }

  
    public void editOrder(){
        String price = editGiaOrder.getText().toString();

        try {
            if( editTenOrder.getText().toString().isEmpty()|| editSDTOrder.getText().toString().isEmpty()|| editDiaChiOrder.getText().toString().isEmpty() || editSoBanOrder.getText().toString().isEmpty()||editSoLuongOrder.getText().toString().isEmpty() )
                Toast.makeText(OrderOnlineActivity.this, "Bạn vui lòng chọn dòng muốn sửa!", Toast.LENGTH_LONG).show();
            else {
                if(!isSDT(editSDTOrder.getText().toString())){
                    Toast.makeText(OrderOnlineActivity.this, "Số điện thoại không hợp lệ!", Toast.LENGTH_LONG).show();
                }
                else {
                    for(int i= listViewOrder.getChildCount()-1; i>=0;i--){
                        listOrder.get(i).setTenKH(editTenOrder.getText().toString());
                        listOrder.get(i).setSdt(editSDTOrder.getText().toString());
                        listOrder.get(i).setDiaChi(editDiaChiOrder.getText().toString());
                        listOrder.get(i).setSoBan(Integer.parseInt(editSoBanOrder.getText().toString()));
                        if(posOrder == i){
                            try {
                                double gia = Double.parseDouble(price);
                                int soLuong = Integer.parseInt(editSoLuongOrder.getText()+"");
                                listOrder.get(i).setGia(gia);
                                listOrder.get(i).setTenMon(spinnerOrder.getSelectedItem().toString());
                                listOrder.get(i).setSoLuong(soLuong);
                            }catch (Exception e){
                                Log.e("UPDATE ERROR", "ERROR UPDATE");
                            }
                            editThanhTienOrder.setText(formatPriceOrder(listOrder.get(i).thanhTien()));
                        }
                        db.updateOrderOnline(listOrder.get(i));
                    }
                    refreshOrder();
                    showTongTienOrder.setText("TỔNG TIỀN: " + formatPriceOrder(tongTienOrder()));
                    Toast.makeText(OrderOnlineActivity.this, "Sửa thành công", Toast.LENGTH_LONG).show();
                }

            }
        }catch (Exception e){
            Log.e("update err: ", "error update empty");
        }

    }
    public void clickedRowOrder(int i){
        KhachHang e = listOrder.get(i);
        editSoLuongOrder.setText(e.getSoLuong()+"");
        selectedValueSpinner(spinnerOrder, e.getTenMon());
        editThanhTienOrder.setText(formatPriceOrder(e.thanhTien()));
        try{
            editTenOrder.setText(listOrder.get(i).getTenKH()+"");
            editSDTOrder.setText(listOrder.get(i).getSdt()+"");
            editDiaChiOrder.setText(listOrder.get(i).getDiaChi()+"");
            editSoBanOrder.setText(listOrder.get(i).getSoBan()+"");
        }catch (Exception ex){
            Log.e("error", "error row");
        }
        posOrder = i;
    }
    public void selectedValueSpinner(Spinner sp, Object value){
        for(int i=0;i<sp.getCount();i++){
            if(sp.getItemAtPosition(i).equals(value)){
                sp.setSelection(i);
                break;
            }
        }
    }

    public String formatPriceOrder(double number){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(number);
        return formattedNumber;
    }

    public void exitOrder(){
        AlertDialog.Builder AlertDialog=new AlertDialog.Builder(this);
        AlertDialog.setTitle("Thông báo!");
        AlertDialog.setIcon(R.mipmap.ic_launcher);
        AlertDialog.setMessage("Bạn có chắc chắn muốn thoát?");
        AlertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog.create().show();
    }
    //xác định view menu nào
    private void setUpViewPager(){
        BottomNavigationView navigationView=findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.action_food);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), MenuCategory.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_table:
                        startActivity(new Intent(getApplicationContext(), MenuTable.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_food:

                        return true;
                    case R.id.action_comment:
                        startActivity(new Intent(getApplicationContext(), ReportActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });
    }
}