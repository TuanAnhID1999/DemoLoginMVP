package com.ttcsolutons.demologinmvp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ttcsolutons.demologinmvp.model.ObjectPass;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";


    SQLiteDatabase database;
    private static final String Database_Name = "PASS_WORD_DB.db";
    private static final String Table_Name = "PASSWORD";

    private static final String ID = "_ID";
    public static final String EMAIL = "EMAIL";
    public static final String PASS_WORD = "PASS_WORD";


    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + Table_Name + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EMAIL + " TEXT, "
                + PASS_WORD + " TEXT" + ")";
        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        // Create tables again
        onCreate(db);
    }

    // Nếu trong bảng Note chưa có dữ liệu,
    // Trèn vào mặc định 1 bản ghi.
    public void createDefaultNotesIfNeed() {
        ObjectPass objectPass = new ObjectPass(0, "admin", "123");
        boolean isInserted = this.addData(objectPass);
        if (isInserted == true)
            Log.i("addData", "Add thanh công ");
        else
            Log.i("addData", "add faild ");
    }


    public int getNotesCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... ");

        String countQuery = "SELECT  * FROM " + Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public boolean addData(ObjectPass objectPass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, objectPass.getID());
        contentValues.put(EMAIL, objectPass.getEmail());
        contentValues.put(PASS_WORD, objectPass.getPassWord());
        Log.d(TAG, "addData: Adding " + objectPass.toString() + " to " + Table_Name);

        long result = db.insert(Table_Name, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name, null);
        return res;
    }

    public boolean updateData(ObjectPass objectPass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, objectPass.getID());
        contentValues.put(EMAIL, objectPass.getEmail());
        contentValues.put(PASS_WORD, objectPass.getPassWord());

        db.update(Table_Name, contentValues, "ID = ?", new String[]{String.valueOf(objectPass.getID())});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name, "ID = ?", new String[]{id});
    }
}