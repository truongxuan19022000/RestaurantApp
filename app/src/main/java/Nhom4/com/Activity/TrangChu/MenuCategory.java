package Nhom4.com.Activity.TrangChu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Nhom4.com.Activity.GoiThem.MainActivityGT;
import Nhom4.com.Activity.OrderByTable.OrderTable;
import Nhom4.com.Activity.ThemTheoDanhmuc.AddFoodActivity;
import Nhom4.com.MainActivity;
import Nhom4.com.R;

public class MenuCategory extends AppCompatActivity {
    RelativeLayout Pho,Comrang,Misao,GoiThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_category);
        this.setTitle("Menu Food");
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.action_table);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(),MenuBar.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_table:
                        startActivity(new Intent(getApplicationContext(),OrderTable.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_food:
                        startActivity(new Intent(getApplicationContext(), AddFoodActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_comment:
                        return true;
                }
                return false;
            }
        });
        Pho=findViewById(R.id.pho);
        Comrang=findViewById(R.id.comrang);
        Misao=findViewById(R.id.misao);
        GoiThem=findViewById(R.id.goithem);
        this.setTitle("Danh mục món ăn");
        this.setTitleColor(Color.parseColor("#000000"));
       /* Pho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuCategory.this,AddFoodActivity.class);
                startActivity(myIntent);
                Toast.makeText(MenuCategory.this, "Hãy chọn các món phở", Toast.LENGTH_SHORT).show();
            }
        });
        Comrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuCategory.this,AddFoodActivity.class);
                startActivity(myIntent);
                Toast.makeText(MenuCategory.this, "Hãy chọn các món cơm rang", Toast.LENGTH_SHORT).show();
            }
        });
        Misao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuCategory.this, AddFoodActivity.class);
                startActivity(myIntent);
                Toast.makeText(MenuCategory.this, "Hãy chọn các món mì sào", Toast.LENGTH_SHORT).show();
            }
        });
        GoiThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuCategory.this,MainActivityGT.class);
                startActivity(myIntent);
                Toast.makeText(MenuCategory.this, "Gọi thêm đồ và thanh toán", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

}