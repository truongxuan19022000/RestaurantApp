package Nhom4.com.Activity.OrderByTable;

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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Nhom4.com.Activity.GoiThem.MainActivityGT;
import Nhom4.com.Activity.OrderByTable.Adapter.BacAdapter;
import Nhom4.com.Activity.OrderOnline.OrderOnlineActivity;
import Nhom4.com.Activity.Report.ReportActivity;
import Nhom4.com.Activity.ThemTheoDanhmuc.AddFoodActivity;
import Nhom4.com.Activity.TrangChu.MenuBar;
import Nhom4.com.Activity.TrangChu.MenuCategory;
import Nhom4.com.Activity.TrangChu.MenuTable;
import Nhom4.com.Model.Foods;
import Nhom4.com.Model.OrderByTable;
import Nhom4.com.MyDataSQLite;
import Nhom4.com.R;

public class OrderTable extends AppCompatActivity {

    Spinner spinBan;    //spinner ban
    Spinner spinFood;    //spinner món ăn
    EditText editNote, editCount, editPrice;
    ListView listView;
    private int pos;
    TextView tongTien;

    ArrayList<Foods> arrFood=new ArrayList<Foods>();
    ArrayAdapter<Foods> adaFood=null;

    ArrayList<Catalog_Bac> arrBan=new ArrayList<Catalog_Bac>();
    ArrayAdapter<Catalog_Bac> adaBan=null;


    BacAdapter adapter = null;
    ArrayList<OrderByTable> list = new ArrayList<OrderByTable>();
    MyDataSQLite db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_table);
        setUpViewPager();
        getControls();
        LayDuLieuDataog();
        loadSpinBan();
        this.setTitle("Gọi theo yêu cầu");
        db=MyDataSQLite.getInstance(this);
        loadSpinFood();
        refreshDataForListView();

        addEvents();
    }

    private void addEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickedRow(i);
            }
        });

        //giữ lâu để xoá
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteValue(i);
                return false;
            }
        });
    }

    private void deleteValue(int i) {
        AlertDialog.Builder AlertDialog=new AlertDialog.Builder(this);
        AlertDialog.setTitle("Xác nhận xoá ?");
        AlertDialog.setIcon(R.mipmap.ic_launcher);
        AlertDialog.setMessage("Bạn có muốn xoá không?");
        AlertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                list.remove(i);
                db.deleteProduct(list.get(pos));
                adapter.notifyDataSetChanged();
                refreshDataForListView();
                tongTien.setText("Tổng tiền : " + formatPrice(tinhTien()) + " đ");
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

    private void clickedRow(int i) {
        OrderByTable e = list.get(i);
        editCount.setText(e.getSoLuong() + "");
        editNote.setText(e.getGhiChu() + "");
        pos = i;

        selectValueSpinner(spinFood, e.getMaSP());
    }
    private void selectValueSpinner(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
    private void refreshDataForListView() {
        String ban=spinBan.getSelectedItem().toString();
        list=db.getAllProductPerBan(ban);
        adapter=new BacAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    private void loadSpinFood() {
        arrFood=db.getFoodByID();
        adaFood = new ArrayAdapter<Foods>(this, android.R.layout.simple_spinner_item, arrFood);
        adaFood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinFood.setAdapter(adaFood);

        spinFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ma= spinFood.getSelectedItem().toString();
                editPrice.setText(String.valueOf(db.getGiaByFoodID(ma)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadSpinBan() {
        adaBan=new ArrayAdapter<Catalog_Bac>(this, android.R.layout.simple_spinner_item, arrBan);
        adaBan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinBan.setAdapter(adaBan);

        spinBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ban= spinBan.getSelectedItem().toString();
                loadListProductByCatalog(arrBan.get(position), ban);
                tongTien.setText("Tổng tiền : " + formatPrice(tinhTien()) + " đ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadListProductByCatalog(Catalog_Bac catalog_bac, String ban) {
        list.clear();
//        list.addAll(c.getDsSanPham());
        list.addAll(db.getAllProductPerBan(ban));
//        adapter.notifyDataSetChanged();
        refreshDataForListView();
    }

    private void LayDuLieuDataog() {
        try{
            Catalog_Bac catPro1= new Catalog_Bac("001", "Bàn 1");
            Catalog_Bac catPro2= new Catalog_Bac("002", "Bàn 2");
            Catalog_Bac catPro3= new Catalog_Bac("003", "Bàn 3");
            Catalog_Bac catPro4= new Catalog_Bac("004", "Bàn 4");
            arrBan.add(catPro1);
            arrBan.add(catPro2);
            arrBan.add(catPro3);
            arrBan.add(catPro4);
            adaBan.notifyDataSetChanged();
        }catch (Exception e){
            e.toString();
        }
    }
    public void getControls(){
        spinBan = findViewById(R.id.spinner);
        spinFood = findViewById(R.id.spinnerFood);
        editNote = findViewById(R.id.editNote);
        editCount = findViewById(R.id.editCount);
        editPrice = findViewById(R.id.editPrice);
        listView = findViewById(R.id.listView);
        tongTien = findViewById(R.id.tongTien);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bac_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuthem:
                inputValue();
                return true;
            case R.id.menusua:
                editValue();
                return true;
            case R.id.goithemdo:
                Intent myIntent=new Intent(OrderTable.this, MainActivityGT.class);
                startActivity(myIntent);
                return true;
            case R.id.menuMon:
                Intent myintent=new Intent(OrderTable.this, AddFoodActivity.class);
                startActivity(myintent);
                return true;
            case R.id.menudong:
                exitApp();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void inputValue() {
        OrderByTable e;
        String ban = spinBan.getSelectedItem().toString();
        String id = spinFood.getSelectedItem().toString();
        int soLuong = Integer.parseInt(editCount.getText().toString());
        int gia = Integer.parseInt(editPrice.getText().toString());
        String note = editNote.getText().toString();
        if(note.isEmpty()){
            note = "(trống)";
        }
        if( id.isEmpty()|| editCount.getText().toString().isEmpty())
            Toast.makeText(this, "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
        else{
//            boolean check =
            if(!checkExitedProduct(id)) {
                e = new OrderByTable(id, ban, note, soLuong, soLuong * gia);
                Catalog_Bac c = (Catalog_Bac) spinBan.getSelectedItem();
                c.addProduct(e);
                db.insertProduct(e);    //loi o day
                tongTien.setText("Tổng tiền : " + formatPrice(tinhTien() + soLuong * gia) + " đ");
                loadListProductByCatalog(c, ban);
                refreshDataForListView();
                Toast.makeText(this, "Thêm thành công !", Toast.LENGTH_LONG).show();
            }
        }

        editCount.setText("");
        editNote.setText("");
        editCount.requestFocus();
    }

    public double tinhTien(){
        double tien = 0;
        for(int i=0; i<list.size(); i++){
            tien += list.get(i).gia();
            Log.e("Err", String.valueOf(tien));
        }
        return tien;
    }

    public String formatPrice(double number){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(number);
        return formattedNumber;
    }

    public boolean checkExitedProduct(String id){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMaSP().equalsIgnoreCase(id)){
                Toast.makeText(this, "Món ăn đã tồn tại ở bàn này !", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    private void editValue() {
        for(int i= listView.getChildCount()-1; i>=0;i--){
            if(pos == i){
                list.get(i).setMaSP(spinFood.getSelectedItem().toString());
                list.get(i).setSoLuong(Integer.parseInt(editCount.getText().toString()));
                list.get(i).setGhiChu(editNote.getText().toString());
                list.get(i).setBan(spinBan.getSelectedItem().toString());
                db.updateProduct(list.get(i));
                refreshDataForListView();
            }
        }
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Sửa thành công !", Toast.LENGTH_LONG).show();
    }
    private void exitApp() {
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
    private void setUpViewPager(){
        BottomNavigationView navigationView=findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.action_table);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), MenuCategory.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_table:
                        return true;
                    case R.id.action_food:
                        startActivity(new Intent(getApplicationContext(), OrderOnlineActivity.class));
                        overridePendingTransition(0,0);
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