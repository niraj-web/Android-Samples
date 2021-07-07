package com.example.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountDB extends SQLiteOpenHelper  {

    private static String dbName = "accountDB";
    private static String tableName = "account";
    private static String idColumnName = "id";
    private static String firstName = "firstname";
    private static String lastName = "lastname";
    private static String password = "password";
    private static String userName = "username";
    private static String dob = "DOB";
    private static String securityQuestion = "securityQuestion";
    private static String securityAnswer = "securityAnswer";
    private static String address = "address";

    public AccountDB(Context context)
    {
        super(context, dbName, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + tableName + "(" +
                idColumnName + " integer primary key autoincrement, " +
                firstName + " text, " +
                lastName + " text, " +
                password + " text, " +
                userName + " text, " +
                dob + " text, " +
                securityQuestion + " text, " +
                securityAnswer + " text, " +
                address + " text " +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        onCreate(sqLiteDatabase);

    }

    public boolean create(Account account)
    {
        boolean result = true;
        try {

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(firstName, account.getFirstname());
            contentValues.put(lastName, account.getLastname());
            contentValues.put(password, account.getPassword());
            contentValues.put(userName, account.getUsername());
            contentValues.put(dob, account.getDOB());
            contentValues.put(securityQuestion, account.getSecurityQuestion());
            contentValues.put(securityAnswer, account.getSecurityAnswer());
            contentValues.put(address, account.getAddress());
           result = sqLiteDatabase.insert(tableName , null,contentValues) > 0;

        }
        catch (Exception e)
        {
          result = false;
        }
        return result;
    }

    public boolean update(Account account)
    {
        boolean result = true;
        try {

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(firstName, account.getFirstname());
            contentValues.put(lastName, account.getLastname());
            contentValues.put(password, account.getPassword());
            contentValues.put(userName, account.getUsername());
            contentValues.put(dob, account.getDOB());
            contentValues.put(securityQuestion, account.getSecurityQuestion());
            contentValues.put(securityAnswer, account.getSecurityAnswer());
            contentValues.put(address, account.getAddress());
            result = sqLiteDatabase.update(tableName , contentValues, idColumnName + " = ?",new String[] { String.valueOf(account.getId())}) > 0;

        }
        catch (Exception e)
        {
            result = false;
        }
        return result;
    }

    public Account login(String userName, String password)
    {
        Account account = null;
        try
        {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ? and password = ?",new String[] {userName,password});
            if (cursor.moveToFirst())
            {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setFirstname(cursor.getString(1));
                account.setLastname(cursor.getString(2));
                account.setPassword(cursor.getString(3));
                account.setUsername(cursor.getString(4));
                account.setDOB(cursor.getString(5));
                account.setSecurityQuestion(cursor.getString(6));
                account.setSecurityAnswer(cursor.getString(7));
            }

        }
        catch (Exception e)
        {
            account = null;
        }
        return account;
    }

    public Account checkUsername(String userName)
    {
        Account account = null;
        try
        {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ?",new String[] {userName });
            if (cursor.moveToFirst())
            {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setFirstname(cursor.getString(1));
                account.setLastname(cursor.getString(2));
                account.setPassword(cursor.getString(3));
                account.setUsername(cursor.getString(4));
                account.setDOB(cursor.getString(5));
                account.setSecurityQuestion(cursor.getString(6));
                account.setSecurityAnswer(cursor.getString(7));
            }

        }
        catch (Exception e)
        {
            account = null;
        }
        return account;
    }

    public Account find(int id)
    {
        Account account = null;
        try
        {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where id = ?",new String[] {String.valueOf(id) });
            if (cursor.moveToFirst())
            {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setFirstname(cursor.getString(1));
                account.setLastname(cursor.getString(2));
                account.setPassword(cursor.getString(3));
                account.setUsername(cursor.getString(4));
                account.setDOB(cursor.getString(5));
                account.setSecurityQuestion(cursor.getString(6));
                account.setSecurityAnswer(cursor.getString(7));
            }

        }
        catch (Exception e)
        {
            account = null;
        }
        return account;
    }
}
