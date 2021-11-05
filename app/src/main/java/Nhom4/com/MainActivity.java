package Nhom4.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Nhom4.com.Activity.OrderOnline.OrderOnlineActivity;
import Nhom4.com.Activity.Report.InsertReport;
import Nhom4.com.Activity.Report.ReportActivity;
import Nhom4.com.Activity.ThemTheoDanhmuc.AddFoodActivity;
import Nhom4.com.Activity.TrangChu.MenuBar;
import Nhom4.com.Activity.TrangChu.Register;
import Nhom4.com.Model.Foods;

public class MainActivity extends AppCompatActivity {
    EditText userName,password;
    ImageButton imageOnline,imageReport;
    Button Login,Register;
    MyDataSQLite db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=MyDataSQLite.getInstance(this);
        userName=findViewById(R.id.userName1);
        password=findViewById(R.id.password1);
        Login=findViewById(R.id.btnSingIn1);
        Register=findViewById(R.id.btnSingUP1);
        imageOnline=findViewById(R.id.imageOnline);
        imageReport=findViewById(R.id.imgageReport);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=userName.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this, "Nhập thông tin!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass=db.checkusernamePassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), MenuBar.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Nhom4.com.Activity.TrangChu.Register.class);
                startActivity(intent);
            }
        });
        imageReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), InsertReport.class);
                startActivity(intent);
            }
        });
    }


}