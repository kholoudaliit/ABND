package com.example.kholoudali.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by kholoudali on 2/14/18.
 */

public class ProductProvider extends ContentProvider {

    private static final int PRODUCTS_ID = 0;
    private static final int PRODUCT_ITEM_ID = 1;
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(ProductContract.CONTENT_AUTHORITY,ProductContract.ProductEntry.PATH_ProductEntry,PRODUCTS_ID);
        uriMatcher.addURI(ProductContract.CONTENT_AUTHORITY,ProductContract.ProductEntry.PATH_ProductEntry+"/#",PRODUCT_ITEM_ID);
    }

    private ProductDBHelper productHelper;

    @Override
    public boolean onCreate() {
        productHelper = new ProductDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = productHelper.getReadableDatabase();
        Cursor cursor = null;

        int match = uriMatcher.match(uri);

        switch (match){
            case PRODUCTS_ID:
                cursor = database.query(ProductContract.ProductEntry.TABLE_NAME,
                        null,null,null,null,null,null);
                break;
            case PRODUCT_ITEM_ID:
                cursor = database.query(ProductContract.ProductEntry.TABLE_NAME,
                        projection,selection,selectionArgs,null,null,sortOrder);
                break;
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match){
            case PRODUCTS_ID:
                return ProductContract.ProductEntry.CONTENT_LIST_TYPE;

            case PRODUCT_ITEM_ID:
                return ProductContract.ProductEntry.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalStateException("Unknown Uri " + uri + " with match" + match);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if (uriMatcher.match(uri) != PRODUCTS_ID)
            throw new IllegalArgumentException("Unsupported Uri for insertion " + uri);

        SQLiteDatabase database = productHelper.getWritableDatabase();
        long rowId = database.insert(ProductContract.ProductEntry.TABLE_NAME,null,contentValues);
        if (rowId != -1) {
            //notify Changes after update
            getContext().getContentResolver().notifyChange(uri, null);
            return ContentUris.withAppendedId(ProductContract.ProductEntry.CONTENT_URI, rowId);
        }
        //if there is a problem
        return null;

    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String whereClause, @Nullable String[] whereArgs) {
        SQLiteDatabase database = productHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int effectedRows = 0;
        switch (match){
            case PRODUCTS_ID:
                //delete all rows
                effectedRows = database.delete(ProductContract.ProductEntry.TABLE_NAME,whereClause, whereArgs);
                break;

            case PRODUCT_ITEM_ID:
                //delete specific row
                effectedRows = database.delete(ProductContract.ProductEntry.TABLE_NAME,whereClause, whereArgs);
                break;
        }
        if (effectedRows>0)
        getContext().getContentResolver().notifyChange(uri, null);
        return effectedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,  @Nullable String whereClause, @Nullable String[] whereArgs) {
        SQLiteDatabase database = productHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int effectedRows = 0;
        switch (match){
            case PRODUCTS_ID:
                //update all rows
                effectedRows = database.update(ProductContract.ProductEntry.TABLE_NAME,contentValues,whereClause,whereArgs);
                break;

            case PRODUCT_ITEM_ID:
                //specific row
                effectedRows = database.update(ProductContract.ProductEntry.TABLE_NAME,contentValues,whereClause,whereArgs);
                break;
        }
        //notify Changes after update
        if (effectedRows>0)
        getContext().getContentResolver().notifyChange(uri, null);
        return effectedRows;
    }
}
