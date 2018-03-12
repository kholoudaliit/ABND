package com.example.kholoudali.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by kholoudali on 1/27/18.
 */

public class ProductContract {

    // Create base URI
    static final String CONTENT_AUTHORITY = "com.example.kholoudali.inventoryapp";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private ProductContract(){}


    public static class ProductEntry implements BaseColumns{
        // Database URI with path
        public static final String PATH_ProductEntry = "product";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_ProductEntry);
        // Content Typs
        static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ProductEntry;
        static final String CONTENT_ITEM_TYPE = ContentResolver.ANY_CURSOR_ITEM_TYPE + "/" + CONTENT_AUTHORITY +PATH_ProductEntry;
        //Database name and columns
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_PRODUCT_NAME="name";
        public static final String COLUMN_PRODUCT_PRICE="price";
        public static final String COLUMN_PRODUCT_QAUNTITY="quantity";
        public static final String COLUMN_PRODUCT_IMAGE="image";
        public static final String COLUMN_SUPPLIER_NAME="supplier_name";
        public static final String COLUMN_SUPPLIER_EMAIL="supplier_email";
        public static final String COLUMN_SUPPLIER_PHONENUMBER="supplier_phoneNo";

    }

}
