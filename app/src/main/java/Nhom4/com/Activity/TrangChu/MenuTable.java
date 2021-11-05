package Nhom4.com.Activity.TrangChu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import Nhom4.com.Activity.GoiThem.MainActivityGT;
import Nhom4.com.Activity.OrderByTable.OrderTable;
import Nhom4.com.R;

public class MenuTable extends AppCompatActivity {
CardView ban1,ban2,ban3,ban4,ban5,ban6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_table);
        ban1=findViewById(R.id.ban1);
        ban2=findViewById(R.id.ban2);
        ban3=findViewById(R.id.ban3);
        ban4=findViewById(R.id.ban4);
        ban5=findViewById(R.id.ban5);
        ban6=findViewById(R.id.ban6);
        ban1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuTable.this, OrderTable.class);
                startActivity(myIntent);
            }
        });
        ban2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuTable.this, OrderTable.class);
                startActivity(myIntent);
            }
        });
        ban3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuTable.this, OrderTable.class);
                startActivity(myIntent);
            }
        });
        ban4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuTable.this, OrderTable.class);
                startActivity(myIntent);
            }
        });
        ban5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuTable.this, OrderTable.class);
                startActivity(myIntent);
            }
        });
        ban6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(MenuTable.this, OrderTable.class);
                startActivity(myIntent);
            }
        });

    }
}