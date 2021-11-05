package Nhom4.com.Activity.ThemTheoDanhmuc;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import Nhom4.com.Activity.OrderOnline.OrderOnlineActivity;
import Nhom4.com.Activity.Report.ReportActivity;
import Nhom4.com.Activity.TrangChu.MenuBar;
import Nhom4.com.Activity.TrangChu.MenuCategory;
import Nhom4.com.Activity.ThemTheoDanhmuc.Adapter.MyArrayAdapter;
import Nhom4.com.Activity.TrangChu.MenuTable;
import Nhom4.com.Model.categories;
import Nhom4.com.Model.Foods;
import Nhom4.com.MyDataSQLite;
import Nhom4.com.R;

public class
AddFoodActivity extends AppCompatActivity implements TextWatcher {
    private static int mon=0;

    final static String Tag="xuan";
    AutoCompleteTextView editTensp;
    Spinner spinDMuc;
    EditText editSL,editGia,editMota;
    Button btnNhap,btnsua,btnXoahet;
    ListView lvSanPham=null;
    ImageButton btnThoat;
    ImageView imgaDelelte;
    //Khai báo cặp đối tượng dùng cho spiner
    ArrayList<categories> arraySpinner=new ArrayList<categories>();
    ArrayAdapter<categories> adapterSpinner=null;
    //Khai báo cpawj đối tượng dùng cho listview
    //custom adapter cho listview
    ArrayList<Foods> arrayListView=new ArrayList<Foods>();
    MyArrayAdapter adapterListView=null;
    //khoi tao du lieu gia autotext
    String arr[]={"Phở bò","Phở gà","Phở tái","Phở chín","Com dưa bò","Com cải bò","Com rang thập cập",
            "Mi sào hải sản","Mi sào thập cẩm","Mi sào thượng hạng"
    };
    int position=0;
    //database
    MyDataSQLite db=null;
    Foods FoodSelected=new Foods();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themmonan_theodanhmuc);
        this.setTitleColor(Color.parseColor("#00C853"));

        this.setTitle("Thêm món ăn theo danh mục");
        db= MyDataSQLite.getInstance(this);
        if (db.getTotalFood()==0)fakeData();
        refreshDataForListView();
        refreshDataForListView();
        getWidgetsControl();
        fakeDataCatelog();
        addEventsFormWidgets();
        setUpViewPager();

    }
    private void fakeData() {
        db.insertFood(new Foods("MS8", "Mì sào hải sản","2","Them hair san",30000,"Mì sào"));
        db.insertFood(new Foods("MS9", "Mì sào thượng hạng","3","Them hair san",30000,"Mì sào"));
        db.insertFood(new Foods("MS10", "Mì sào thập cẩm","4","Them hair san",30000,"Mì sào"));
    }
    private void getWidgetsControl(){
        spinDMuc=findViewById(R.id.spinner);
        editTensp=findViewById(R.id.editTen);
        editSL=findViewById(R.id.editSolg);
        editGia=findViewById(R.id.editgia);
        editMota=findViewById(R.id.editMota);
        lvSanPham=findViewById(R.id.lvSanPham);
        //lấy đối tượng Tên món ra
        editTensp=(AutoCompleteTextView) findViewById(R.id.editTen);
        //Thiết lập để lắng nghe TextChange
        editTensp.addTextChangedListener(this);
        //Thiết lập ArrayADapter
        editTensp.setAdapter(
                new ArrayAdapter<String>
                        (
                                this,
                                android.R.layout.simple_list_item_1,
                                arr
                        ));

        //Cấu hình cho spinner
        adapterSpinner=new ArrayAdapter<categories>(
                this,
                android.R.layout.simple_spinner_item,
                arraySpinner
        );
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDMuc.setAdapter(adapterSpinner);
        //Cấu hình cho Listview
        adapterListView=new MyArrayAdapter(
                this,
                R.layout.custom_food_list,
                arrayListView
        );
        lvSanPham.setAdapter(adapterListView);
    }
    //Hàm giả dữ liệu tạo 4 danh mục mặc định cho spinner
    private void fakeDataCatelog(){
        categories cat1=new categories("1","Phở");
        categories cat2=new categories("2","Cơm rang");
        categories cat3=new categories("3","Mì sào");
        categories cat4=new categories("4","Gọi thêm");

        adapterSpinner.add(cat1);
        adapterSpinner.add(cat2);
        adapterSpinner.add(cat3);
        adapterSpinner.add(cat4);
        adapterSpinner.notifyDataSetChanged();
    }
    /*    Hàm xử lý sự kiện*/
    private void addEventsFormWidgets(){

        spinDMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                AddFoodPrice();
                String category=spinDMuc.getSelectedItem().toString();
                loadListProductByCatalog(arraySpinner.get(position),category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lvSanPham.setOnItemClickListener(new MyListViewEvent());
        lvSanPham.setOnItemLongClickListener(new MyListViewEvent());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

/*hàm xóa 1 đồ ăn*/
    public void deleteAllFood(){
        AlertDialog.Builder AlertDialog=new AlertDialog.Builder(this);
        AlertDialog.setTitle("Xoá");
//        AlertDialog.setIcon(R.mipmap.ic_launcher);
        AlertDialog.setMessage("Bạn có chắc chắn muốn xoá tất cả không?");
        AlertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               db.deleteAllFood();
               refreshDataForListView();
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
    /**
     * Class sự kiện của ListView
     * @author xuan
     *
     */

    private class MyListViewEvent implements
            AdapterView.OnItemClickListener,
            AdapterView.OnItemLongClickListener
    {

        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
            //Xóa vị trí thứ arg2
            refreshDataForListView();
            arrayListView.remove(arg2);
            db.deleteFood(FoodSelected);
            Toast.makeText(AddFoodActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int vitri, long l) {
            FoodSelected=arrayListView.get(vitri);
            editSL.setText(FoodSelected.getSoLuong());
            editTensp.setText(FoodSelected.getTenSP());
            editMota.setText(FoodSelected.getMota());
            position=vitri;
        }
    }
    /*Load listview lay du lieu*/
    private void refreshDataForListView(){
        try {
            String category=spinDMuc.getSelectedItem().toString();
            arrayListView=db.getAllProductForCategory(category);
            adapterListView=new MyArrayAdapter(
                    this,
                    R.layout.custom_food_list,
                    arrayListView);
            lvSanPham.setAdapter(adapterListView);
            adapterListView.notifyDataSetChanged();
        }catch (Exception e){
            System.out.println("có lỗi: " +e.toString());
        }
    }
    /**
     * hàm thêm 1 món ăn theo danh mục
     * @author xuan
     *
     */
    private void addProductForCatalog(){

        try {
            String maMon="MS"+FoodSelected.getmID();
            Foods p= new Foods();
            if(editTensp.getText().toString().isEmpty()||editMota.getText().toString().isEmpty()||editSL.getText().toString().isEmpty()){
                Toast.makeText(this, "Không được bỏ trống các trường!", Toast.LENGTH_SHORT).show();
            }
            else {
                categories c = (categories) spinDMuc.getSelectedItem();
                String Category=spinDMuc.getSelectedItem().toString();
                p.setMaSP(maMon);
                p.setTenSP(editTensp.getText()+"");
                p.setCategory(Category);
                p.setSoLuong(editSL.getText()+"");
                p.setMota(editMota.getText()+"");
                p.setGia(Integer.parseInt(editGia.getText()+""));
                c.addSP(p);
                db.insertFood(p);
                //Cập nhật lại list view
                loadListProductByCatalog(c,Category);
                refreshDataForListView();
                //Nhập xong xóa các trường
                editTensp.setText("");
                editSL.setText("");
                editMota.setText("");
                editTensp.requestFocus();
            }
        }catch (Exception e){
            Log.e(Tag,e.toString());
            Toast.makeText(this, "Lỗi nhập dữ liệu", Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * Lọc lại danh sách sp theo danh mục và update lại listview theo danh mục
     * @author xuan
     *
     */
    private void loadListProductByCatalog(categories c, String category){
        //Xóa ds sp cũ
        arrayListView.clear();
        //Lấy lại ds mới từ cattalog trong spinner
        arrayListView.addAll(db.getAllProductForCategory(category));
        //Cập nhật lại listview
        adapterListView.notifyDataSetChanged();
    }
    /**
     * xác nhận có thoát
     * @author xuan
     *
     */
    private void XacNhanThoat(){
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

            }
        });
        AlertDialog.create().show();
    }
    /*Set giá cho món ăn ở list view*/
    private void AddFoodPrice(){
        try {
            String value = spinDMuc.getSelectedItem().toString();
            if(value.equals("Phở"))
                editGia.setText("25000");
            else if(value.equals("Cơm rang")) //neu la nam
                editGia.setText("30000");
            else if (value.equals("Mì sào")){
                editGia.setText("35000");
            }else if (value.equals("Gọi thêm")){
                editGia.setText("25000");
            }
            else {
                editGia.setText("25000");
            }
        }catch (Exception e){
            Log.e("Xuan",e.toString());
        }
    }
    private void PriceForNameFood() {
        try {

            String NameFood = editTensp.getText().toString();
            if (NameFood.equals("Phở bò"))
                editGia.setText("30000");
            else if (NameFood.equals("Phở gà"))
                editGia.setText("35000");
            else if (NameFood.equals("Phở tái"))
                editGia.setText("25000");
            else if (NameFood.equals("Phở chín"))
                editGia.setText("26000");
            else if (NameFood.equals("Com dưa bò"))
                editGia.setText("30000");
            else if (NameFood.equals("Com cải bò"))
                editGia.setText("30000");
            else if (NameFood.equals("Com rang thap cam"))
                editGia.setText("25000");
            else {
                editGia.setText("25000");
            }

        } catch (Exception e) {
            Log.e("Xuan", e.toString());
        }
    }
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem, menu);
        return true;
    }
    /**
     * menu xử lý các su
     * @author xuan
     *
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnThem:
                PriceForNameFood();
                addProductForCatalog();
                return true;
            case R.id.btnSua:
                Foods food=FoodSelected;
                food.setTenSP(editTensp.getText().toString());
                food.setSoLuong(editSL.getText().toString());
                food.setMota(editMota.getText().toString());
                food.setGia(Integer.parseInt(editGia.getText().toString()));
                db.updateFood(food);
                Toast.makeText(AddFoodActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                refreshDataForListView();
                return true;
            case R.id.btnXoahet:
                deleteAllFood();
                return true;
            case R.id.btnThoat:
                XacNhanThoat();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setUpViewPager(){
        BottomNavigationView navigationView=findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.action_home);
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