package com.example.hp.thi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp.thi.model.GhiChu;

import java.util.ArrayList;
import java.util.List;

public class Sqlite extends SQLiteOpenHelper {
    public static final String DBNAME = "GhiChu";
    public static final int VERSION = 1;
    private final String CREATE_TABLE = "CREATE TABLE GhiChu (name VARCHAR PRIMARY KEY, content NVARCHAR, time NVARCHAR)";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_CONTENT = "content";
    private final String COLUMN_TIME = "time";
    private final String TABLE_NAME = "GhiChu";
    public Sqlite dbHelper;
    public SQLiteDatabase db;

    public Sqlite(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
    }

    public long insertGhiChu(GhiChu ghiChu){
        //xin quyền
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //ghép cặp dữ liệu
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,ghiChu.getName());
        contentValues.put(COLUMN_CONTENT,ghiChu.getContent());
        contentValues.put(COLUMN_TIME,ghiChu.getTime());

        //sử dụng lệnh insert
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        //đóng kết nối
        sqLiteDatabase.close();

        return result;
    }

    public int deleteGhiChu(String name){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        int result = sqLiteDatabase.delete(TABLE_NAME,COLUMN_NAME + "=?",new String[]{name});
        sqLiteDatabase.close();
        return result;
    }

    public long updateProduct(GhiChu ghiChu){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,ghiChu.getName());
        values.put(COLUMN_CONTENT,ghiChu.getContent());
        values.put(COLUMN_TIME,ghiChu.getTime());

        long result = sqLiteDatabase.update(TABLE_NAME,values,COLUMN_NAME+"=?", new String[]{ghiChu.getName()});
        sqLiteDatabase.close();
        return result;
    }

    public List<GhiChu> getAllGhiChu(){
        List<GhiChu> ghiChuList = new ArrayList<>();
        //Xin quyền
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //viết câu lệnh select
        String select = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {
                GhiChu ghiChu = new GhiChu();
                ghiChu.setName(cursor.getString(0));
                ghiChu.setContent(cursor.getString(1));
                ghiChu.setTime(cursor.getString(2));

                ghiChuList.add(ghiChu);
            } while (cursor.moveToNext());

            cursor.close();
        }
        //đóng kết nối DB
        sqLiteDatabase.close();
        return ghiChuList;
    }
}


