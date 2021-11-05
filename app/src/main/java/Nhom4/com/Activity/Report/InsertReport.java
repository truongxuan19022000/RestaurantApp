package Nhom4.com.Activity.Report;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Nhom4.com.Model.Foods;
import Nhom4.com.MyDataSQLite;
import Nhom4.com.R;

public class InsertReport extends AppCompatActivity {

    Spinner spinMa, spinDanhGia;
    EditText editNhanXet, editTenKhach;
    Button btnThem;

    String arr[] ={"Rất ngon", "Ngon", "Bình thường", "Dở"};
    ArrayAdapter<String> adaDG=null;

    ArrayList<Foods> dsMon=new ArrayList<Foods>();
    ArrayAdapter<Foods> adaDS=null;
    MyDataSQLite db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_report);

        db=MyDataSQLite.getInstance(this);
        getControl();
        loadSpiner();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddReprt();
            }
        });
    }

    private void loadSpiner() {
        dsMon=db.getFoodByID();
        adaDS=new ArrayAdapter<Foods>(InsertReport.this, android.R.layout.simple_spinner_item, dsMon);
        adaDS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMa.setAdapter(adaDS);
        adaDS.notifyDataSetChanged();

        adaDG=new ArrayAdapter<String>(InsertReport.this, android.R.layout.simple_spinner_item, arr);
        adaDG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDanhGia.setAdapter(adaDG);
        adaDG.notifyDataSetChanged();
    }

    private void getControl() {
        btnThem=(Button)findViewById(R.id.btnThemMoi);
        editTenKhach=(EditText)findViewById(R.id.editTenKhach);
        editNhanXet=(EditText)findViewById(R.id.editNX);
        spinMa=(Spinner)findViewById(R.id.spinMa);
        spinDanhGia=(Spinner)findViewById(R.id.spinDanhGia);
    }

    private void AddReprt() {
        try{
            Intent intent=new Intent(InsertReport.this, ReportActivity.class);
            Bundle bundle=new Bundle();

            bundle.putString("maMon", spinMa.getSelectedItem().toString());
            bundle.putString("tenKhach", editTenKhach.getText().toString());
            bundle.putString("danhgia", spinDanhGia.getSelectedItem().toString());
            bundle.putString("nx", editNhanXet.getText().toString());
            intent.putExtra("MyPackage", bundle);
            startActivity(intent);

            InsertReport.this.finish();
        }catch (Exception e){
            Toast.makeText(this, "Lỗi nhập dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }
}