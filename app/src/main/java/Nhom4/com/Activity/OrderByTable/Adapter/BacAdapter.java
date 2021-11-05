package Nhom4.com.Activity.OrderByTable.Adapter;

import android.app.Activity;
import android.widget.ArrayAdapter;


import java.util.ArrayList;

import Nhom4.com.Model.OrderByTable;

public class BacAdapter extends ArrayAdapter<OrderByTable> {
    Activity context = null;
    ArrayList<OrderByTable> list = null;
    int layoutID;
    public BacAdapter(Activity context, int layoutID, ArrayList<OrderByTable> list){
        super(context, layoutID, list);
        this.context = context;
        this.list = list;
        this.layoutID = layoutID;
    }
}
