package com.example.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper
{
    static String DATABASE_NAME="UserDataBase";

    public static final String TABLE_NAME="UserTable";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Email="email";

    public static final String Table_Column_3_Password="password";
    private SQLiteOpenHelper helper;
    private String oldName;

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Password+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public long insertData(String t1, String t2)
    {
        SQLiteDatabase dbb = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.Table_Column_1_Name,t1);
        contentValues.put(SQLiteHelper.Table_Column_2_Email,t2);
        long id = dbb.insert(helper.getDatabaseName(),null, contentValues);
        return id;

    }

    public String getData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {SQLiteHelper.Table_Column_1_Name,SQLiteHelper.Table_Column_2_Email};
        Cursor cursor = db.query(SQLiteHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name));
            String name = cursor.getString(cursor.getColumnIndex(Table_Column_2_Email));
            buffer.append(cid+" " + name + " "  +" \n");
        }
        return buffer.toString();


    }

    public int updateName(String u1, String u2)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.Table_Column_1_Name,u1);
        String[] whereArgs= {oldName};
        int count =db.update(SQLiteHelper.TABLE_NAME,contentValues, helper.getDatabaseName()+" = ?",whereArgs );
        return count;
    }

    public int delete(String uname)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(SQLiteHelper.TABLE_NAME ,helper.getDatabaseName()+" = ?",whereArgs);
        return  count;

    }
}
