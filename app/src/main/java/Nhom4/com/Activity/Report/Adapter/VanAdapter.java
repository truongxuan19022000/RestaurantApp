package Nhom4.com.Activity.Report.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Nhom4.com.Model.Report;
import Nhom4.com.R;

public class VanAdapter extends ArrayAdapter<Report> {
    Activity context=null;
    int layoutID;
    ArrayList<Report> arrayList=null;

    public VanAdapter(Activity context, int layoutID, ArrayList<Report> arrayList){
        super(context, layoutID, arrayList);
        this.context=context;
        this.layoutID=layoutID;
        this.arrayList=arrayList;
    }

    public View getView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        convertView=inflater.inflate(layoutID, null);
        if(arrayList.size()>0 && pos>=0){
            final TextView txtTen=(TextView)convertView.findViewById(R.id.txtTen);
            final TextView txtDanhGia=(TextView)convertView.findViewById(R.id.txtDanhGia);
            final TextView txtNhanXet=(TextView)convertView.findViewById(R.id.txtThongTin);
            final Report report=arrayList.get(pos);
            txtTen.setText(report.getName());
            txtDanhGia.setText(report.getDanhgia());
            txtNhanXet.setText(report.getNhanxet());
        }
        return convertView;
    }
}
