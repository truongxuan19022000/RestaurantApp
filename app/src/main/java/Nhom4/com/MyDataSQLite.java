package Nhom4.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import Nhom4.com.Model.Foods;
import Nhom4.com.Model.GoiThem;
import Nhom4.com.Model.KhachHang;
import Nhom4.com.Model.OrderByTable;
import Nhom4.com.Model.Report;

import java.util.ArrayList;

public class MyDataSQLite extends SQLiteOpenHelper {
    private static final String TAG="MyDatabaseHelper";
    private static final String DATABASE_NAME="Restaurant_DB";
    private static final int DATABASE_VERSION = 1;
    // các trường bản food
    private static final String Foods = "Food";
    private static final String food_ID = "id";
    private static final String code_Food = "maSP";
    private static final String name_Food = "tenSP";
    private static final String number = "soLuong";
    private static final String description = "Mota";
    private static final String price = "Gia";
    private static final String category_id = "Category";
    private static final String CREATE_FOOD_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + Foods + " (" +
                    food_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    code_Food + " TEXT NOT NULL," +
                    name_Food + " TEXT NOT NULL," +
                    category_id + " TEXT NOT NULL," +
                    number + " TEXT NOT NULL," +
                    description + " INTEGER NOT NULL," +
                    price + " INTEGER NOT NULL" +
                    ")";
    //Các trường bảng Categories
    private static final String Categories = "Categories";
    private static final String CategoryID = "categories";
    private static final String Name ="name";
    private static final String Description = "Mota";
    private static final String CREATE_Categories_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + Categories + " (" +
                    CategoryID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    Name + " TEXT NOT NULL," +
                    Description + " TEXT NOT NULL" +
                    ")";
    private static final String Users = "Users";
    private static final String username = "username";
    private static final String password = "password";
    private static final String CREATE_User_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + Users + " (" +
                    username + " TEXT NOT NULL PRIMARY KEY ," +
                    password + " TEXT NOT NULL" +
                    ")";
    private static final String Table = "tbtable";
    private static final String Table_ID = "id";
    private static final String codeTable = "code";
    private static final String Status = "Status";
    private static final String CREATE_Table_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + Table + " (" +
                    Table_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    codeTable + " TEXT NOT NULL," +
                    Status + " TEXT NOT NULL" +
                    ")";
    //OrderbyTable(TRinhBac)
    private static final String OrderbyTable = "OrderbyTable";
    private static final String OrderID = "id";
    private static final String Table_id = "tableID";
    private static final String Order_food_id = "food_id";
    private static final String GhiChu_COLUMN = "GhiChu";
    private static final String SoLuong_COLUMN = "SoLuong";
    private static final String Gia_COLUMN = "Gia";
    private static final String CREATE_OrderByTable_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + OrderbyTable + " (" +
                    OrderID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    Table_id + " INTEGER NOT NULL," +
                    Order_food_id + " INTEGER NOT NULL," +
                    GhiChu_COLUMN + " TEXT NOT NULL," +
                    SoLuong_COLUMN + " INTEGER NOT NULL," +
                    Gia_COLUMN + " INTEGER NOT NULL" +
                    ")";
    private static final String CallMore = "GoiThem";
    private static final String CallID = "id";
    private static final String tableID = "SoBan";
    private static final String Water = "DoUong";
    private static final String numberWater = "soLuongNuoc";
    private static final String Call_food_id = "DoAn";
    private static final String numberFood = "soLuongDoAn";
    private static final String TotalByCall = "thanhTien";
    String CREATE_GoiThem_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + CallMore + "(" +
            CallID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            tableID + " TEXT NOT NULL, " +
            Water + " TEXT NOT NULL, " +
            numberWater + " INTEGER NOT NULL, " +
            Call_food_id + " TEXT NOT NULL, " +
            numberFood + " INTEGER NOT NULL, " +
            TotalByCall + " REAL NOT NULL " + ")";
    //OrderOnline (Tam)
    private static final String OrderOnline = "OrderOnline";
    private static final String Order_id = "Order_id";
    private static final String FoodName = "FoodName";
    private static final String PriceOrder = "PriceOrder";
    private static final String isFirst = "First";
    private static final String CountOrder = "CountOrder";
    private static final String CustomerName = "CustomerName";
    private static final String Address = "Address";
    private static final String PhoneNumber = "PhoneNumber";
    private static final String TableNumber = "TableNumber";
    private static final String TotalByOrder = "TotalByOrder";
    private static final String CREATE_OrderOnline_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + OrderOnline + " (" +
                    Order_id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    isFirst + " INTEGER NOT NULL, " +
                    CustomerName + " TEXT NOT NULL, " +
                    PhoneNumber + " TEXT NOT NULL, " +
                    PriceOrder + " REAL NOT NULL, " +
                    Address + " TEXT NOT NULL," +
                    FoodName + " TEXT NOT NULL," +
                    CountOrder + " INTEGER NOT NULL, " +
                    TableNumber + " INTEGER NOT NULL, " +
                    TotalByOrder + " REAL NOT NULL" +
                    ")";
    //Các trường bảng của Report(Vân)
    private static final String Report = "Report";
    private static final String Report_ID = "id";
    private static final String Report_food_ID = "food_ID";
    private static final String nameCustomer = "nameCustomer";
    private static final String DanhGia = "danhGia";
    private static final String Comment = "nhanxet";
    private static final String CREATE_Report_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + Report + " (" +
                    Report_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    Report_food_ID + " TEXT NOT NULL," +
                    nameCustomer + " TEXT NOT NULL," +
                    DanhGia + " TEXT NOT NULL," +
                    Comment + " TEXT NOT NULL" +
                    ")";
    private static MyDataSQLite MyDataSQLite;
    public static MyDataSQLite getInstance(Context context)
    {
        if(MyDataSQLite == null){
            MyDataSQLite = new MyDataSQLite(context.getApplicationContext());
        }
        return MyDataSQLite;
    }
    public MyDataSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_User_TABLE_SQL);
        db.execSQL(CREATE_Categories_TABLE_SQL);
        db.execSQL(CREATE_FOOD_TABLE_SQL);
        db.execSQL(CREATE_GoiThem_TABLE_SQL);
        db.execSQL(CREATE_OrderOnline_TABLE_SQL);
        db.execSQL(CREATE_Report_TABLE_SQL);
        db.execSQL(CREATE_Table_TABLE_SQL);
        db.execSQL(CREATE_OrderByTable_TABLE_SQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.e(TAG, "onUpgrapde: ");
        db.execSQL("DROP TABLE IF EXISTS " + Users);
        db.execSQL("DROP TABLE IF EXISTS " + Categories);
        db.execSQL("DROP TABLE IF EXISTS " + Foods);
        db.execSQL("DROP TABLE IF EXISTS " + CallMore);
        db.execSQL("DROP TABLE IF EXISTS " + OrderOnline);
        db.execSQL("DROP TABLE IF EXISTS " + Report);
        db.execSQL("DROP TABLE IF EXISTS " + Table);
        db.execSQL("DROP TABLE IF EXISTS " + OrderbyTable);
        onCreate(db);
    }
    /*Phan login*/
    public Boolean insertUsers(String username,String password){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=Mydb.insert("Users",null,contentValues);
        if(result==-1)return false;
        else
            return true;
    }
    public boolean checkusername(String username){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("Select * from Users where username= ?",new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }
    public boolean checkusernamePassword(String username,String password){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("Select * from Users where username= ? and password=?",new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }
    /*Phan them theo danh muc cua xuan*/
    //Đếm tổng số dòng trong database
    public int getTotalFood() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + Foods;
        Cursor cursor = db.rawQuery(sql, null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }
    public boolean insertFood(Foods food) {
/**
 * long insert(String table, String nullColumnHack, ContentValues values)
 * chèn một bản ghi trên các cơ sở dữ liệu. Bảng chỉ định tên bảng,
 * nullColumnHack không cho phép các giá trị hoàn toàn vô giá trị.
 * Nếu số thứ hai là null, android sẽ lưu trữ các giá trị null nếu giá
 trị
 * là trống rỗng. Đối số thứ ba xác định các giá trị được lưu trữ.
 */
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(code_Food, food.getMaSP());
        values.put(name_Food, food.getTenSP());
        values.put(number, food.getSoLuong());
        values.put(description, food.getMota());
        values.put(price, food.getGia());
        values.put(category_id, food.getCategory());
        long rowId = db.insert(Foods, null, values);
        db.close();
        if (rowId != -1)
            return true;

        return false;

    }

    /**
     * Lấy tất cả món ăn
     *
     */
    public ArrayList<Foods> getAllFood() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Foods> foods =new ArrayList<Foods>();
        String sql="SELECT * FROM "+Foods;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                foods.add(new Foods(cursor.getInt(cursor.getColumnIndex(food_ID)),
                        cursor.getString(cursor.getColumnIndex(code_Food)),
                        cursor.getString(cursor.getColumnIndex(name_Food)),
                        cursor.getString(cursor.getColumnIndex(category_id)),
                        cursor.getString(cursor.getColumnIndex(number)),
                        cursor.getString(cursor.getColumnIndex(description)),
                        cursor.getInt(cursor.getColumnIndex(price))
                       )
                );

            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return foods;
    }
    /**
     * Lấy tất cả món ăn theo category
     *
     */
    public ArrayList<Foods> getAllProductForCategory(String category) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Foods> foods = new ArrayList<Foods>();
        String sql = "SELECT * FROM " + Foods + " WHERE " + category_id + " LIKE " + "'" + category + "'";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("sql", sql);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                foods.add(new Foods(cursor.getInt(cursor.getColumnIndex(food_ID)),
                        cursor.getString(cursor.getColumnIndex(code_Food)),
                        cursor.getString(cursor.getColumnIndex(name_Food)),
                        cursor.getString(cursor.getColumnIndex(category_id)),
                        cursor.getString(cursor.getColumnIndex(number)),
                        cursor.getString(cursor.getColumnIndex(description)),
                        cursor.getInt(cursor.getColumnIndex(price))
                        )
                );
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();

        return foods;
    }
    /**
     * int update(String table, ContentValues values, String whereClause,
     String[] whereArgs)
     * @param food
     * @return
     */
    public int updateFood(Foods food) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(code_Food, food.getMaSP());
        values.put(name_Food, food.getTenSP());
        values.put(number, food.getSoLuong());
        values.put(description, food.getMota());
        values.put(price, food.getGia());
        values.put(category_id, food.getCategory());
        int rowEffect=db.update(Foods,values,food_ID+"= ?",
                new String[]{String.valueOf(food.getmID())});
        db.close();
        return rowEffect;
    }
    public int deleteAllFood() {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(Foods, null,null);
        db.close();
        return rowEffect;
    }
    public int deleteFood(Foods food) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(Foods, food_ID + " = ?", new
                String[]{String.valueOf(food.getmID())});
        db.close();
        return rowEffect;
    }
    /* End Phan them theo danh muc cua xuan*/

    /* Phần gọi thêm của sơn*/
    public boolean insertValue(GoiThem GT){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(tableID, GT.getSoBan());
        v.put(Water, GT.getTenDoUong());
        v.put(numberWater, GT.getSoluongNuoc());
        v.put(Call_food_id, GT.getTenDoAnKem());
        v.put(numberFood, GT.getSoluongDoAn());
        v.put(TotalByCall,GT.tinhTT());
        long rowID = db.insert(CallMore, null, v);
        db.close();
        if(rowID != -1)
            return true;
        return false;
    }
    public ArrayList<GoiThem> getAll(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<GoiThem> listGoiThem = new ArrayList<GoiThem>();
        try{
            String sql = "SELECT * FROM " + CallMore;
            Cursor cursor = db.rawQuery(sql, null);
            if( cursor != null && cursor.moveToFirst()){
                do{
                    listGoiThem.add(new GoiThem(
                            cursor.getInt(cursor.getColumnIndex(CallID)),
                            cursor.getString(cursor.getColumnIndex(tableID)),
                            cursor.getString(cursor.getColumnIndex(Water)),
                            cursor.getInt(cursor.getColumnIndex(numberWater)),
                            cursor.getString(cursor.getColumnIndex(Call_food_id)),
                            cursor.getInt(cursor.getColumnIndex(numberFood))));
                }while (cursor.moveToNext());
                cursor.close();
            }
        }catch (Exception e){
            Log.e(TAG, e.toString());
        }
        db.close();
        return listGoiThem;
    }
    public ArrayList<GoiThem> getAllGoiThem(String SOBAN){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<GoiThem> listGoiThem = new ArrayList<GoiThem>();
        try{
            String sql = "SELECT * FROM " + CallMore+ " WHERE " + tableID + " LIKE " + "'" + SOBAN + "'";
            Cursor cursor = db.rawQuery(sql, null);
            if( cursor != null && cursor.moveToFirst()){
                do{
                    listGoiThem.add(new GoiThem(
                            cursor.getInt(cursor.getColumnIndex(CallID)),
                            cursor.getString(cursor.getColumnIndex(tableID)),
                            cursor.getString(cursor.getColumnIndex(Water)),
                            cursor.getInt(cursor.getColumnIndex(numberWater)),
                            cursor.getString(cursor.getColumnIndex(Call_food_id)),
                            cursor.getInt(cursor.getColumnIndex(numberFood))));
                }while (cursor.moveToNext());
                cursor.close();
            }
        }catch (Exception e){
            Log.e(TAG, e.toString());
        }
        db.close();
        return listGoiThem;
    }

    public int getTotalWord() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + CallMore;
        Cursor cursor = db.rawQuery(sql, null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }
    public int updateValue(GoiThem GT) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(tableID, GT.getSoBan());
        v.put(Water, GT.getTenDoUong());
        v.put(numberWater, GT.getSoluongNuoc());
        v.put(Call_food_id, GT.getTenDoAnKem());
        v.put(numberFood, GT.getSoluongDoAn());
        v.put(TotalByCall,GT.tinhTT());
        int rowEffect = db.update(CallMore, v, CallID + " = ?", new String[]{String.valueOf(GT.getId())});
        db.close();
        return rowEffect;
    }

    public int deleteAllGT(GoiThem GT) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(CallMore,null,null);
        db.close();
        return rowEffect;
    }
    public int deleteValue(int id) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(CallMore, CallID + " = ? ", new String[]{String.valueOf(id)});
        db.close();
        return rowEffect;
    }

    /* End Phần gọi thêm của sơn*/
    //    Order Online (Tam)
    public boolean insertOrderOnline(KhachHang k){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put(isFirst, k.isFirst());
        v.put(CustomerName, k.getTenKH());
        v.put(PhoneNumber, k.getSdt());
        v.put(Address, k.getDiaChi());
        v.put(FoodName, k.getTenMon());
        v.put(CountOrder, k.getSoLuong());
        v.put(PriceOrder, k.getGia());
        v.put(TableNumber, k.getSoBan());
        v.put(TotalByOrder, k.thanhTien());
        long rowID = db.insert(OrderOnline, null, v);
        db.close();

        if(rowID != -1)
            return true;
        return false;

    }

    public ArrayList<KhachHang> getAllOrderOnline(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        try{
            String sql = "SELECT * FROM " + OrderOnline;

            Cursor cursor = db.rawQuery(sql, null);
            if( cursor != null && cursor.moveToFirst()){
                do{
                    list.add(new KhachHang(
                            cursor.getInt(cursor.getColumnIndex(isFirst)),
                            cursor.getInt(cursor.getColumnIndex(Order_id)),
                            cursor.getString(cursor.getColumnIndex(CustomerName)),
                            cursor.getString(cursor.getColumnIndex(PhoneNumber)),
                            cursor.getString(cursor.getColumnIndex(Address)),
                            cursor.getString(cursor.getColumnIndex(FoodName)),
                            cursor.getInt(cursor.getColumnIndex(TableNumber)),
                            cursor.getInt(cursor.getColumnIndex(CountOrder)),
                            cursor.getDouble(cursor.getColumnIndex(PriceOrder))));
                }while (cursor.moveToNext());
                cursor.close();
            }
        }catch (Exception e){
            Log.e("all data", "error all data");
        }
        db.close();
        return list;
    }
    public int updateOrderOnline(KhachHang k) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(isFirst, k.isFirst());
        v.put(FoodName, k.getTenKH());
        v.put(PhoneNumber, k.getSdt());
        v.put(Address, k.getDiaChi());
        v.put(FoodName, k.getTenMon());
        v.put(CountOrder, k.getSoLuong());
        v.put(PriceOrder, k.getGia());
        v.put(TableNumber, k.getSoBan());
        v.put(TotalByOrder, k.thanhTien());
        int rowEffect = db.update(OrderOnline, v, Order_id + " = ?",
                new String[]{String.valueOf(k.getId())});
        db.close();
        return rowEffect;
    }

    public int deleteOrderOnline(KhachHang k) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(OrderOnline, Order_id + " = ?", new
                String[]{String.valueOf(k.getId())});
        db.close();
        return rowEffect;
    }
//    END TAM
    //Các hàm report(Vân)
public ArrayList<Foods> getFoodByName(String mon){
    ArrayList<Foods> ds=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase=getReadableDatabase();
    String sql = "SELECT tenSP FROM " + Foods + " WHERE " + name_Food + " LIKE " + "'%"+ mon +"%'";
    Cursor cursor=sqLiteDatabase.rawQuery(sql, null);
    if (cursor != null && cursor.moveToFirst()) {
        do {
            ds.add(new Foods(
                            cursor.getString(cursor.getColumnIndex(name_Food))
                    )
            );
        } while (cursor.moveToNext());
        cursor.close();
    }
    return  ds;
}
public ArrayList<Foods> getFoodByID(){
    ArrayList<Foods> ds=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase=getReadableDatabase();
    String sql = "SELECT tenSP FROM " + Foods;
    Cursor cursor=sqLiteDatabase.rawQuery(sql, null);
    if (cursor != null && cursor.moveToFirst()) {
        do {
            ds.add(new Foods(
                            cursor.getString(cursor.getColumnIndex(name_Food))
                    )
            );
        } while (cursor.moveToNext());
        cursor.close();
    }
    return  ds;
}

    public boolean insertReport(Report report) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Report_food_ID, report.getFoodID());
        values.put(nameCustomer, report.getName());
        values.put(DanhGia, report.getDanhgia());
        values.put(Comment, report.getNhanxet());
        Log.e("insert ", String.valueOf(0));
        long rowId = db.insert(Report, null, values);
        db.close();
        if (rowId != -1)
            return true;

        return false;
    }

    public ArrayList<Report> getAllReport() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Report> reports =new ArrayList<Report>();
        String sql="SELECT * FROM "+ Report;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                reports.add(new Report(cursor.getInt(cursor.getColumnIndex(Report_ID)),
                                cursor.getString(cursor.getColumnIndex(Report_food_ID)),
                                cursor.getString(cursor.getColumnIndex(nameCustomer)),
                                cursor.getString(cursor.getColumnIndex(DanhGia)),
                                cursor.getString(cursor.getColumnIndex(Comment))
                        )
                );

            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return reports;
    }

    public ArrayList<Report> getAllReportByFood(String name) {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Report> reports =new ArrayList<Report>();
        String sql="SELECT * FROM "+ Report + " WHERE " + Report_food_ID + " LIKE " + "'" + name + "'";
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                reports.add(new Report(cursor.getInt(cursor.getColumnIndex(Report_ID)),
                                cursor.getString(cursor.getColumnIndex(Report_food_ID)),
                                cursor.getString(cursor.getColumnIndex(nameCustomer)),
                                cursor.getString(cursor.getColumnIndex(DanhGia)),
                                cursor.getString(cursor.getColumnIndex(Comment))
                        )
                );

            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return reports;
    }

    public int updateReport(Report food) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Report_food_ID, food.getFoodID());
        values.put(nameCustomer, food.getName());
        values.put(DanhGia, food.getDanhgia());
        values.put(Comment, food.getNhanxet());
        int rowEffect=db.update(Report,values,Report_ID+"= ?",
                new String[]{String.valueOf(food.getReportID())});
        db.close();
        return rowEffect;
    }

    public int deleteAllReport() {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(Report, null,null);
        db.close();
        return rowEffect;
    }
    public int deleteReport(Report food) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(Report, Report_ID + " = ?", new
                String[]{String.valueOf(food.getReportID())});
        db.close();
        return rowEffect;
    }
    //Các hàm phần order by table(trinhbac)

    public boolean insertProduct(OrderByTable product) {
        Log.e("RUN INSERT 1", "insert");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Order_food_id, product.getMaSP());
        values.put(Table_id, product.getBan());
        values.put(GhiChu_COLUMN, product.getGhiChu());
        values.put(SoLuong_COLUMN, product.getSoLuong());
        values.put(Gia_COLUMN, product.gia());
        Log.e("RUN INSERT 2", "insert");
        long rowId = db.insert(OrderbyTable, null, values);
        Log.e("RUN INSERT 3", "insert");
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }

    public int getGiaByFoodID(String ma){
        int gia=0;
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String sql = "SELECT Gia FROM " + Foods + " WHERE " + name_Food + " LIKE " + "'" + ma + "'";
        Cursor cursor=sqLiteDatabase.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()){
                gia += cursor.getInt(cursor.getColumnIndex(price));
            }
        }
        return  gia;
    }

    public ArrayList<OrderByTable> getAllProduct() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<OrderByTable> products = new ArrayList<OrderByTable>();
        String sql = "SELECT * FROM " + OrderbyTable;
        Log.e("sql", sql);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                products.add(new OrderByTable(cursor.getString(cursor.getColumnIndex(Order_food_id)),
                        cursor.getString(cursor.getColumnIndex(Table_id)),
                        cursor.getString(cursor.getColumnIndex(GhiChu_COLUMN)),
                        cursor.getInt(cursor.getColumnIndex(SoLuong_COLUMN)),
                        cursor.getInt(cursor.getColumnIndex(Gia_COLUMN)))
                );
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();

        return products;
    }

    public ArrayList<OrderByTable> getAllProductPerBan(String ban) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<OrderByTable> products = new ArrayList<OrderByTable>();
        String sql = "SELECT * FROM " + OrderbyTable + " WHERE " + Table_id + " LIKE " + "'" + ban + "'";
        Log.e("sql", sql);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                products.add(new OrderByTable(cursor.getString(cursor.getColumnIndex(Order_food_id)),
                        cursor.getString(cursor.getColumnIndex(Table_id)),
                        cursor.getString(cursor.getColumnIndex(GhiChu_COLUMN)),
                        cursor.getInt(cursor.getColumnIndex(SoLuong_COLUMN)),
                        cursor.getInt(cursor.getColumnIndex(Gia_COLUMN)))
                );
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();

        return products;
    }
    public int getTotalProduct() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + OrderbyTable;
        Cursor cursor = db.rawQuery(sql, null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }


    public int updateProduct(OrderByTable product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Order_food_id, product.getMaSP());
        values.put(Table_id, product.getBan());
        values.put(GhiChu_COLUMN, product.getGhiChu());
        values.put(SoLuong_COLUMN, product.getSoLuong());
        values.put(Gia_COLUMN, product.gia());
        int rowEffect = db.update(OrderbyTable, values, Table_id + " = ? AND " + Order_food_id + " = ?",
                new String[]{String.valueOf(product.getBan()), String.valueOf(product.getMaSP())});
        db.close();
        return rowEffect;
    }

    public int deleteAllProduct() {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(OrderbyTable, null, null);
        db.close();
        return rowEffect;
    }

    public int deleteProduct(OrderByTable product) {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(OrderbyTable, Table_id + " = ? AND " + Order_food_id + " = ?",
                new String[]{String.valueOf(product.getBan()), String.valueOf(product.getMaSP())});
        db.close();
        return rowEffect;
    }
}
