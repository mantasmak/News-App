package com.newsapp.android.newsapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myDB.db";
    public static final String NEWS_TABLE_NAME = "news";
    public static final String NEWS_COLUMN_ID = "id";
    public static final String NEWS_COLUMN_IMG = "img";
    public static final String NEWS_COLUMN_TITLE = "title";
    public static final String NEWS_COLUMN_PUBLISHED = "published";
    public static final String NEWS_COLUMN_AUTHOR = "author";
    public static final String NEWS_COLUMN_CONTENT = "content";
    public static final String NEWS_COLUMN_URL = "url";
    public static final String NEWS_COLUMN_SOURCE = "source";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table news " +
                        "(id integer primary key, img text,title text,published text, author text,content text, url text, source text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS news");
        onCreate(db);
    }

    public boolean insertNews (String img, String title, String published, String author,String content, String url, String source) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("img", img);
        contentValues.put("title", title);
        contentValues.put("published", published);
        contentValues.put("author", author);
        contentValues.put("content", content);
        contentValues.put("url", url);
        contentValues.put("source", source);
        db.insert("news", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from news where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, NEWS_TABLE_NAME);
        return numRows;
    }

    public boolean updateNews (Integer id, String img, String title, String published, String author,String content, String url, String source) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("img", img);
        contentValues.put("title", title);
        contentValues.put("published", published);
        contentValues.put("author", author);
        contentValues.put("content", content);
        contentValues.put("url", url);
        contentValues.put("source", source);
        db.update("news", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteNews (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("news",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<NewsModel> getAllNews() {
        ArrayList<NewsModel> array_list = new ArrayList<NewsModel>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from news", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new NewsModel(res.getString(res.getColumnIndex(NEWS_COLUMN_IMG)), res.getString(res.getColumnIndex(NEWS_COLUMN_TITLE)),
                    res.getString(res.getColumnIndex(NEWS_COLUMN_PUBLISHED)), res.getString(res.getColumnIndex(NEWS_COLUMN_AUTHOR)),
                            res.getString(res.getColumnIndex(NEWS_COLUMN_CONTENT)), res.getString(res.getColumnIndex(NEWS_COLUMN_URL)),
                                    res.getString(res.getColumnIndex(NEWS_COLUMN_SOURCE))));
            res.moveToNext();
        }
        return array_list;
    }
}
