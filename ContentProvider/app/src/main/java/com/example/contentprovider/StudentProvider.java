package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class StudentProvider extends ContentProvider
{
    static final String PROVIDER_NAME = "com.example.MyApplication.StudentsProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/students";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String GRADE = "grade";

    private static HashMap<String , String> STUDENTS_PROJECTION_MAP;

    static final int STUDENTS = 1;
    static final int STUDENT_ID = 2;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"students",STUDENTS);
        uriMatcher.addURI(PROVIDER_NAME,"students/#",STUDENT_ID);

    }

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "College";
    static final String STUDENTS_TABLE_NAME = "students";
    static  final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            "CREATE TABLE " + STUDENTS_TABLE_NAME +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " name TEXT NOT NULL, " + "grade TEXT NOT NULL);";
    private String sortOrder;

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null,DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            sqLiteDatabase.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
        {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME);
            onCreate(sqLiteDatabase);

        }
    }

    @Override
    public boolean onCreate()
    {
        Context context =getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1)
    {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(STUDENTS_TABLE_NAME);

        switch(uriMatcher.match(uri))
        {
            case STUDENTS:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;

            case STUDENT_ID:
                qb.appendWhere(STUDENT_ID + "=" + uri.getPathSegments().get(1));
             break;

            default:
        }
        if (sortOrder == null || sortOrder == "")
        {
            sortOrder = NAME;
        }

        Cursor c = qb.query(db, strings, s,strings1, null,null, s1);
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri)
    {
        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.students";

            case STUDENT_ID:
                return "vnd.android.cursor.dir/vnd.example.students";

            default:
                throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues)
    {
        long rowID = db.insert( STUDENTS_TABLE_NAME,"",contentValues);

        if (rowID > 0)
        {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri,null);
            return  _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings)
    {
        int count = 0;
        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                count = db.delete(STUDENTS_TABLE_NAME,s , strings);
             break;

            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(STUDENTS_TABLE_NAME, STUDENT_ID + " = " + id + (!TextUtils.isEmpty(s) ? "AND (" + s + ')' : ""),strings);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings)
    {
        int count = 0;
        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                count = db.update(STUDENTS_TABLE_NAME, contentValues,s,strings);
             break;

            case STUDENT_ID:
                count = db.update(STUDENTS_TABLE_NAME,contentValues, STUDENT_ID + " = " + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(s) ? " AND (" +s + ')' : ""), strings);
            break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);



        }
        return count;
    }
}
