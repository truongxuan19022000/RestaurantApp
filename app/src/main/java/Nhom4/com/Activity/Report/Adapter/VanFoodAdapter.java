package Nhom4.com.Activity.Report.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Nhom4.com.Model.Foods;
import Nhom4.com.R;

public class VanFoodAdapter extends ArrayAdapter<Foods> {
    Activity context=null;
    int layoutID;
    ArrayList<Foods> arrayList=null;

    public VanFoodAdapter(Activity context, int layoutID, ArrayList<Foods> arrayList){
        super(context, layoutID, arrayList);
        this.context=context;
        this.layoutID=layoutID;
        this.arrayList=arrayList;
    }

    public View getView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        convertView=inflater.inflate(layoutID, null);
        if(arrayList.size()>0 && pos>=0){
            final TextView txtTen=(TextView)convertView.findViewById(R.id.txtFoodName);
            final Foods report=arrayList.get(pos);
            txtTen.setText(report.getTenSP());
        }
        return convertView;
    }
}
