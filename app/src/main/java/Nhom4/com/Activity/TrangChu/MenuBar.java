package Nhom4.com.Activity.TrangChu;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Nhom4.com.Activity.GoiThem.MainActivityGT;
import Nhom4.com.Activity.OrderByTable.OrderTable;
import Nhom4.com.Activity.OrderOnline.OrderOnlineActivity;
import Nhom4.com.Activity.Report.ReportActivity;
import Nhom4.com.Activity.ThemTheoDanhmuc.AddFoodActivity;
import Nhom4.com.MainActivity;
import Nhom4.com.R;

public class MenuBar extends AppCompatActivity {
    RelativeLayout Pho,Comrang,Misao,GoiThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_category);
        setUpViewPager();
        Pho=findViewById(R.id.pho);
        Comrang=findViewById(R.id.comrang);
        Misao=findViewById(R.id.misao);
        GoiThem=findViewById(R.id.goithem);
        this.setTitle("Danh mục món ăn");
        this.setTitleColor(Color.parseColor("#000000"));
        Pho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuBar.this,AddFoodActivity.class);
                startActivity(myIntent);
                Toast.makeText(MenuBar.this, "Hãy chọn các món phở", Toast.LENGTH_SHORT).show();
            }
        });

        Comrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuBar.this,AddFoodActivity.class);
                startActivity(myIntent);
                Toast.makeText(MenuBar.this, "Hãy chọn các món cơm rang", Toast.LENGTH_SHORT).show();
            }
        });
        Misao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuBar.this, AddFoodActivity.class);
                startActivity(myIntent);
                Toast.makeText(MenuBar.this, "Hãy chọn các món mì sào", Toast.LENGTH_SHORT).show();
            }
        });
        GoiThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuBar.this,MainActivityGT.class);
                startActivity(myIntent);
                Toast.makeText(MenuBar.this, "Gọi thêm đồ và thanh toán", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setUpViewPager(){
        BottomNavigationView navigationView=findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.action_home);  //xác định xem đang ở view nào
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        return true;
                    case R.id.action_table:
                        startActivity(new Intent(getApplicationContext(),MenuTable.class));
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.btnThoat:
                XacNhanThoat();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}