package Nhom4.com.Activity.Report;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Nhom4.com.Activity.OrderOnline.OrderOnlineActivity;
import Nhom4.com.Activity.Report.Adapter.VanAdapter;
import Nhom4.com.Activity.Report.Adapter.VanFoodAdapter;
import Nhom4.com.Activity.TrangChu.MenuBar;
import Nhom4.com.Activity.TrangChu.MenuCategory;
import Nhom4.com.Activity.TrangChu.MenuTable;
import Nhom4.com.Model.Foods;
import Nhom4.com.Model.Report;
import Nhom4.com.MyDataSQLite;
import Nhom4.com.R;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    ListView lvHienThi, lvMonAn;
    TextView txtThongKe;

    EditText editSearch;

    ArrayList<Foods> arrFood=new ArrayList<Foods>();
    ArrayList<Foods> dsMon=new ArrayList<Foods>();
    VanFoodAdapter adaDS=null;

    ArrayList<Report> arrReport= new ArrayList<Report>();
    VanAdapter adaReport= null;
    int pos;
    MyDataSQLite db=null;
    Report report=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setUpViewPager();
        getByID();
        db=MyDataSQLite.getInstance(this);
        this.setTitle("Đánh giá món ăn");
        loadListViewFood();
        refreshDataForListView();
        Events();
        insert();
    }

    private void insert() {
        try{
            Intent data=getIntent();
            Bundle bundle=data.getBundleExtra("MyPackage");
            String maMon=bundle.getString("maMon");
            String tenKhach=bundle.getString("tenKhach");
            String danhgia=bundle.getString("danhgia");
            String nx=bundle.getString("nx");

            report=new Report(maMon, tenKhach, danhgia, nx);
            db.insertReport(report);
            refreshDataForListView();
        }catch (Exception e){
            e.toString();
        }
    }


    private void Events() {
        lvHienThi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteValue(position);
                return false;
            }
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dsMon.clear();
                dsMon.addAll(db.getFoodByName(editSearch.getText().toString()));

                if (editSearch.getText().toString().equals("")) {
                    dsMon=db.getFoodByID();
                }
                adaDS.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void deleteValue(int position) {
        AlertDialog.Builder AlertDialog=new AlertDialog.Builder(this);
        AlertDialog.setTitle("Xác nhận xoá ?");
        AlertDialog.setIcon(R.mipmap.ic_launcher);
        AlertDialog.setMessage("Bạn có muốn xoá không?");
        AlertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteReport(arrReport.get(position));
                adaReport.notifyDataSetChanged();
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


    private void refreshDataForListView() {
        try{
            String mon=dsMon.get(pos).getTenSP();
            txtThongKe.setText(String.valueOf(db.getAllReportByFood(mon).size()));

            arrReport=db.getAllReportByFood(mon);
            adaReport=new VanAdapter(ReportActivity.this, R.layout.custom_list_report, arrReport);
            lvHienThi.setAdapter(adaReport);
            adaReport.notifyDataSetChanged();
        }catch (Exception e){
            e.toString();
        }
    }

    private void loadListViewFood() {
        dsMon=db.getFoodByID();
        adaDS=new VanFoodAdapter(ReportActivity.this, R.layout.van_food_adapter, dsMon);
        lvMonAn.setAdapter(adaDS);
        adaDS.notifyDataSetChanged();

        lvMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos=position;
                String ma= dsMon.get(position).getTenSP();
                loadListBySpinner(dsMon.get(position), ma);
            }
        });
    }

    private void loadListBySpinner(Foods foods, String mon) {
        arrReport.clear();
        arrReport.addAll(db.getAllReportByFood(mon));
        refreshDataForListView();
    }

    private void getByID() {
        lvHienThi=(ListView)findViewById(R.id.lvDanhSach);
        txtThongKe=(TextView)findViewById(R.id.txtThongKe);
        lvMonAn=(ListView)findViewById(R.id.lvMonAn);
        editSearch=(EditText)findViewById(R.id.edtSearch);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.commnet_menu, menu);
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.commnet_menu, menu);
    }
    public boolean onContextItemSelected(MenuItem menuItem){
        doSomething(menuItem);
        return super.onContextItemSelected(menuItem);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        doSomething(item);
        return super.onOptionsItemSelected(item);
    }
    private void doSomething(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnThem:
                xuliThem();
                break;
            case R.id.btnThoat:
                doThoat();
                break;
        }
    }

    private void doThoat(){
        finish();
    }

    private void xuliThem() {
        Intent intent2=new Intent(ReportActivity.this, InsertReport.class);
        startActivity(intent2);
    }
    private void setUpViewPager(){
        BottomNavigationView navigationView=findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.action_comment);
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

                        return true;
                }
                return true;
            }
        });
    }
}