package com.ttcsolutons.demologinmvp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ttcsolutons.demologinmvp.model.ObjectPass;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";


    SQLiteDatabase database;
    private static final String Database_Name = "PASS_WORD_DB.db";
    private static final String Table_Name = "PASSWORD";

    private static final String ID = "_ID";
    public static final String email = "EMAIL";
    public static final String passWord = "PASS_WORD";


    public DatabaseHelper(Context context){
        super(context, Table_Name, null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + Table_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ID + " INTEGER DEFAULT 0, " +
                email +" TEXT, " +
                passWord + " TEXT)";
        db.execSQL(createTable);


    }

    // Nếu trong bảng Note chưa có dữ liệu,
    // Trèn vào mặc định 1 bản ghi.
    public void createDefaultNotesIfNeed()  {
        int count = this.getNotesCount();
        if(count ==0 ) {
            ObjectPass objectPass = new ObjectPass(1,"admin","123");
            this.addData(objectPass);
        }
    }


    public int getNotesCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        // Create tables again
        onCreate(db);
    }

    public boolean addData(ObjectPass objectPass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean createSuccessful = false;

        contentValues.put(ID, objectPass.getID());
        contentValues.put(email, objectPass.getEmail());
        contentValues.put(passWord, objectPass.getPassWord());
        Log.d(TAG, "addData: Adding " + objectPass.toString() + " to " + Table_Name);

        // Trèn một dòng dữ liệu vào bảng.
        createSuccessful =  db.insert(Table_Name, null, contentValues) >0;
        Log.d("add", "addPeople: Thành công");
        // Đóng kết nối database.

        db.close();
        return createSuccessful;
    }

    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + Table_Name, null );
        return res;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table_Name;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}