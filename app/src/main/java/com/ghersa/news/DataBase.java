package com.ghersa.news;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;
import java.util.Vector;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "News.db";
    private static final int DATABASE_VERSION = 1;

    public DataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL = " CREATE TABLE News ("
                        + " idNews INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + " author TEXT NOT NULL , "
                        + " title TEXT NOT NULL , "
                        + " url TEXT NOT NULL , "
                        + " urlToImage TEXT NOT NULL , "
                        + " publishedAt TEXT NOT NULL , "
                        + " content TEXT NOT NULL , "
                        + " dateAdd INTEGER NOT NULL "
                        +" ) ";
        db.execSQL(strSQL);
        Log.i("DEBUGE"," Create DATABASE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    Insert
    public void insertNews(Article article){
        String author, title, url, urlToImage, publishedAt, content;
        if(article.getAuthor()!=null) author=article.getAuthor().replace("'","''"); else author="null";
        if(article.getTitle()!=null) title=article.getTitle().replace("'","''"); else title="null";
        if(article.getUrl()!=null) url=article.getUrl().replace("'","''"); else url="null";
        if(article.getUrlToImage()!=null) urlToImage=article.getUrlToImage().replace("'","''"); else urlToImage="null";
        if(article.getPublishedAt()!=null) publishedAt=article.getPublishedAt().replace("'","''"); else publishedAt="null";
        if(article.getContent()!=null) content=article.getContent().replace("'","''"); else content="null";

        String strSQL = " INSERT INTO News (author, title, url, urlToImage, publishedAt, content, dateAdd ) VALUES ( '"
                        +author+"', '"+title+"', '"+url+"' ,'"+urlToImage+"', '"+publishedAt+"', '"+content+"', "+new Date().getTime()
                         +" ) ";
        this.getWritableDatabase().execSQL(strSQL);
        Log.i("DEBUGE"," INSERT DATABASE");
    }


    public Vector<Article> getNews(){
        Vector<Article> result = new Vector<>();
        String strSQL = "  SELECT * FROM News ORDER BY dateAdd ";

        Cursor cursor = getReadableDatabase().rawQuery(strSQL, null);
        cursor.moveToFirst();
        while (! cursor.isAfterLast() ){
            Article article = new Article(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            result.add(article);
            cursor.moveToNext();
        }
        cursor.close();

        return  result;
    }

    public void deleteDB(int arg){
        String strSQL = " DELETE FROM News WHERE idNews="+arg ;
        this.getWritableDatabase().execSQL(strSQL);
    }
}
