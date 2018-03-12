package com.example.kholoudali.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kholoudali on 1/27/18.
 */

public class ProductDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "product.db";
    public static final int DB_VERSION = 1;

    public ProductDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE="CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME + " ("+
                ProductContract.ProductEntry._ID +" INTEGER PRIMARY KEY," +
                ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + " TEXT,"+
                ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE + " INTEGER, "+
                ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY +" INTEGER, "+
                ProductContract.ProductEntry.COLUMN_PRODUCT_IMAGE + " TEXT, "+
                ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME +" TEXT, " +
                ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL +" TEXT, " +
                ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONENUMBER+ " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE= "DROP TABLE IF EXISTS" + ProductContract.ProductEntry.TABLE_NAME;
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
