package com.example.sqlite_db;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import androidx.annotation.Nullable;

public class myDBHandler extends SQLiteOpenHelper{

        private static final int DATABASE_VERSION = 1;
        //file
        private static final String DATABASE_NAME = "products.db";
        //table
        public static final String TABLE_PRODUCTS = "products";
        //columns
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_PRODUCTNAME = "productname";

    public myDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //add new row to the database
    public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //delete product from the database
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCTNAME + "=\"" +
                productName + "\";");
    }

    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS);
    }


    //print out database as string
    @SuppressLint("Range")
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        // Check if cursor contains any data
        if (c.moveToFirst()) {
            // Loop through the cursor rows
            do {
                String productName = c.getString(c.getColumnIndex("productname"));
                String productId = c.getString(c.getColumnIndex("_id"));
                if (productName != null) {
                    dbString += productId + ": " + productName + "\n";
                }
            } while (c.moveToNext());
        }

        // Close the cursor to release resources
        c.close();
        db.close();
        return dbString;
    }

}
