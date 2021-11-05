package Nhom4.com.Activity.GoiThem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Nhom4.com.Activity.OrderOnline.OrderOnlineActivity;
import Nhom4.com.Activity.Report.ReportActivity;
import Nhom4.com.Activity.TrangChu.MenuCategory;
import Nhom4.com.Activity.TrangChu.MenuTable;
import Nhom4.com.Model.CatalogGT;
import Nhom4.com.Model.GoiThem;
import Nhom4.com.MyDataSQLite;
import Nhom4.com.R;

//son2
public class MainActivityGT extends AppCompatActivity{
    ListView lv;
    TextView txtson,txtThanhTien,txtTongTien;
//    Button btnThem,btnSua,btnDong,btnTinh;
    EditText editSoLuongNuoc,editSoLuongDoAn;
    Spinner spinBan,spinDoUong,spinDoAnKem;
    static final String TAG="son";
    int pos=0;
    public static final int GIA=15000;
    int a=0;int b=0;

    MyDataSQLite db=null;
    GoiThem GT=new GoiThem();

    int curIndexSelected = -1;

    ArrayList<CatalogGT> listDanhmucban=new ArrayList<CatalogGT>();
    ArrayAdapter<CatalogGT> adapterDanhmucban=null;

    ArrayList<GoiThem> listGoiThem=new ArrayList<GoiThem>();
    ArrayAdapter<GoiThem> adapterGoiThem=null;

//    List<String> listDanhmucban = Arrays.asList("Bàn số 01","Bàn số 02","Bàn số 03","Bàn số 04","Bàn số 05");
//    ArrayAdapter<String> adapterDanhmucban = null;

    List<String> listDoUong = Arrays.asList("-Không-","Rượu","Bia","Coca","Nước cam");
    ArrayAdapter<String> adapterDoUong = null;

    List<String> listDoAnKem = Arrays.asList("-Không-", "Bánh mỳ", "Quẩy", "Xúc xích");
    ArrayAdapter<String> adapterDoAnKem = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gt);
        this.setTitle("Gọi thêm đồ");
        setUpViewPager();
        getControl();
        getSpinner();
        LayDuLieuDataog();
        db = MyDataSQLite.getInstance(this);
// tạo 3 dữ liệu có sẵn
        /*if(db.getTotalWord()==0){
            db.insertValue(new GoiThem("Bàn 1","Bia",1,"Quẩy",3));
            db.insertValue(new GoiThem("Bàn 3","Rượu",2,"Xúc xích",2));
            db.insertValue(new GoiThem("Bàn 4","Bia",1,"Quẩy",2));
        }*/

        refresh();
        processEvents();
    }

    public void getControl(){
        lv=findViewById(R.id.lv);

//        btnDong=findViewById(R.id.btnDong);
//        btnThem=findViewById(R.id.btnThem);
//        btnSua=findViewById(R.id.btnSua);
//        btnTinh=findViewById(R.id.btnTinh);

        txtThanhTien = findViewById(R.id.txtThanhTien);
        txtTongTien=findViewById(R.id.txtTongTien);
        editSoLuongDoAn=findViewById(R.id.editSoLuongDoAn);
        editSoLuongNuoc=findViewById(R.id.editSoLuongNuoc);
        txtson=findViewById(R.id.txtson);
        spinDoAnKem=findViewById(R.id.spinDoAnKem);
        spinBan=findViewById(R.id.spinBan);
        spinDoUong=findViewById(R.id.spinDoUong);

        listGoiThem = new ArrayList<GoiThem>();
        adapterGoiThem = new ArrayAdapter<GoiThem>(MainActivityGT.this, android.R.layout.simple_list_item_1,listGoiThem);
        lv.setAdapter(adapterGoiThem);
    }
    private void LayDuLieuDataog() {
        CatalogGT catPro1= new CatalogGT("001", "Bàn 1");
        CatalogGT catPro2= new CatalogGT("002", "Bàn 2");
        CatalogGT catPro3= new CatalogGT("003", "Bàn 3");
        CatalogGT catPro4= new CatalogGT("004", "Bàn 4");
        CatalogGT catPro5= new CatalogGT("005", "Bàn 5");
        adapterDanhmucban.add(catPro1);
        adapterDanhmucban.add(catPro2);
        adapterDanhmucban.add(catPro3);
        adapterDanhmucban.add(catPro4);
        adapterDanhmucban.add(catPro5);
        adapterDanhmucban.notifyDataSetChanged();
    }
    public void getSpinner(){
//        adapterDanhmucban = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,listDanhmucban);
//        adapterDanhmucban.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinBan.setAdapter(adapterDanhmucban);
        adapterDanhmucban = new ArrayAdapter<CatalogGT>(this, android.R.layout.simple_spinner_item, listDanhmucban);
        adapterDanhmucban.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinBan.setAdapter(adapterDanhmucban);
//        spinner.setOnItemSelectedListener(this);

        spinBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String SOBAN=spinBan.getSelectedItem().toString();
                loadListProductByCatalog(listDanhmucban.get(position),SOBAN);
                txtThanhTien.setText(Double.toString(tinh()));
                txtTongTien.setText(Double.toString(TongTienCaBan()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterDoUong = new ArrayAdapter<String>(MainActivityGT.this, android.R.layout.simple_spinner_item,listDoUong);
        adapterDoUong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDoUong.setAdapter(adapterDoUong);

        adapterDoAnKem = new ArrayAdapter<String>(MainActivityGT.this, android.R.layout.simple_spinner_item,listDoAnKem);
        adapterDoAnKem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDoAnKem.setAdapter(adapterDoAnKem);
    }


    void processEvents(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickedRow(i);
            }

        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int agr2, long agr3) {
                setBtnXoa(listGoiThem.get(agr2).getId());
                return false;
            }
        });

    }
    public void setBtnXoa(int id){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Chú ý");
        builder.setMessage("Bạn có chắc muốn xóa không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                listGoiThem.remove(pos);
                db.deleteValue(id);
                refresh();
                txtTongTien.setText(Double.toString(TongTienCaBan())+"đ");

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.optionmenu_gt,menu);
        return true;
    }
    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.mnuThem:
                them();
                break;
            case R.id.mnuSua:
                sua();
                break;
            case R.id.mnudong:
                closeApp();
                break;
            case R.id.mnuXoa:
                deleteAll();
                break;
            case R.id.mnuTinh:
                tinhtien();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    void them(){
        try {
//            String soBan = spinBan.getSelectedItem().toString();
            CatalogGT c = (CatalogGT) spinBan.getSelectedItem();
            String SOBAN=spinBan.getSelectedItem().toString();
            String tenNuocUong = spinDoUong.getSelectedItem().toString();
            String tenDoAn = spinDoAnKem.getSelectedItem().toString();
            if( editSoLuongDoAn.getText().toString().isEmpty() || editSoLuongNuoc.getText().toString().isEmpty() )
                Toast.makeText(MainActivityGT.this, "Bạn chưa nhập số lượng!", Toast.LENGTH_LONG).show();
            else{
                GoiThem GT = new GoiThem();
                int soLuongDoAn = Integer.parseInt(editSoLuongDoAn.getText()+"");
                int soLuongNuoc = Integer.parseInt(editSoLuongNuoc.getText()+"");
                GT.setSoBan(SOBAN);
                GT.setTenDoUong(tenNuocUong);
                GT.setSoLuongNuoc(soLuongNuoc);
                GT.setTenDoAnKem(tenDoAn);
                GT.setSoluongDoAn(soLuongDoAn);
                txtThanhTien.setText("");
//                        listGoiThem.add(GT);

                loadListProductByCatalog(c,SOBAN);
                db.insertValue(GT);
                refresh();
                adapterGoiThem.notifyDataSetChanged();
                txtTongTien.setText(Double.toString(TongTienCaBan())+"đ");
            }

        }catch (Exception ex){
            Log.e(TAG, ex.toString());
        }

    }
    void sua(){
        try{
            GoiThem goiThem=GT;
            goiThem.setSoBan(spinBan.getSelectedItem().toString());
            goiThem.setTenDoUong(spinDoUong.getSelectedItem().toString());
            goiThem.setSoLuongNuoc(Integer.parseInt(editSoLuongNuoc.getText().toString()));
            goiThem.setTenDoAnKem(spinDoAnKem.getSelectedItem().toString());
            goiThem.setSoluongDoAn(Integer.parseInt(editSoLuongDoAn.getText().toString()));
            db.updateValue(goiThem);
            refresh();
            txtTongTien.setText(Double.toString(TongTienCaBan())+"đ");
            Toast.makeText(MainActivityGT.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Log.e(TAG, ex.toString());
        }
    }

    private void refresh() {
        try{
            String SOBAN=spinBan.getSelectedItem().toString();
            listGoiThem=new ArrayList<>();
            listGoiThem=db.getAllGoiThem(SOBAN);
            adapterGoiThem=new ArrayAdapter<GoiThem>(this, android.R.layout.simple_list_item_1, listGoiThem);
            lv.setAdapter(adapterGoiThem);
            adapterGoiThem.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadListProductByCatalog(CatalogGT c, String SOBAN){
        //Xóa ds sp cũ
        listGoiThem.clear();
        //Lấy lại ds mới từ cattalog trong spinner
        listGoiThem.addAll(db.getAllGoiThem(SOBAN));
        //Cập nhật lại listview
        adapterGoiThem.notifyDataSetChanged();
    }


public void deleteAll(){
    AlertDialog.Builder AlertDialog=new AlertDialog.Builder(this);
    AlertDialog.setTitle("Xoá");
//        AlertDialog.setIcon(R.mipmap.ic_launcher);
    AlertDialog.setMessage("Bạn có chắc chắn muốn xoá tất cả không?");
    AlertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            db.deleteAllGT(GT);
            refresh();
            txtTongTien.setText("");
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
    public void closeApp() {

        AlertDialog.Builder x = new AlertDialog.Builder(MainActivityGT.this);
        x.setTitle("Thoát");
        x.setMessage("Bạn có muốn thoát không?");
        x.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        x.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        x.create().show();
    }
    public void clickedRow(int i){
        GT = listGoiThem.get(i);
        editSoLuongNuoc.setText(GT.getSoluongNuoc()+"");
        editSoLuongDoAn.setText(GT.getSoluongDoAn()+"");
//        spinBan.setSelection(adapterDanhmucban.getPosition(GT.getSoBan()));
        spinDoUong.setSelection(adapterDoUong.getPosition(GT.getTenDoUong()));
        spinDoAnKem.setSelection(adapterDoAnKem.getPosition(GT.getTenDoAnKem()));

        pos = i;

    }

    public long tinh(){
        a=Integer.parseInt(editSoLuongDoAn.getText()+"");
        b=Integer.parseInt(editSoLuongNuoc.getText()+"");
        return (GIA*a)+(GIA*b);
    }
    public double TongTienCaBan(){
        double tien = 0;
        for(int i=0; i<listGoiThem.size(); i++){
            tien += listGoiThem.get(i).tinhTT();
        }
        return tien;
    }
    public void tinhtien(){
        try {
                    Toast.makeText(MainActivityGT.this,"tinh",Toast.LENGTH_LONG).show();
                    DecimalFormat df=new DecimalFormat("###,##0");
                    txtThanhTien.setText(df.format(tinh()));
                }
                catch (Exception ex){
                    Log.e(TAG,ex.toString());
                }

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v, menuInfo);

        getMenuInflater().inflate(R.menu.optionmenu_gt,menu);
    }
    private void setUpViewPager(){
        BottomNavigationView navigationView=findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.action_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                       
                        return true;
                    case R.id.action_table:
                        startActivity(new Intent(getApplicationContext(), MenuTable.class));
                        overridePendingTransition(0,0);
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