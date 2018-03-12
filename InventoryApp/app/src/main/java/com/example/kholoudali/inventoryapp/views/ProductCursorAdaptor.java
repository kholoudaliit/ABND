package com.example.kholoudali.inventoryapp.views;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kholoudali.inventoryapp.R;
import com.example.kholoudali.inventoryapp.data.ProductContract;

/**
 * Created by kholoudali on 2/17/18.
 */

public class ProductCursorAdaptor extends CursorAdapter {

    private TextView productName;
    private TextView prodcutQuintity;
    private TextView productPrice;
    private TextView supplierName;
    private Button saleButton;


    public ProductCursorAdaptor (Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.product_item, viewGroup, false);
        bindView(v,context,cursor);
        return v;
         }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        productName= view.findViewById(R.id.product_name);
        prodcutQuintity = view.findViewById(R.id.quantity_value);
        productPrice = view.findViewById(R.id.price_value);
        supplierName = view.findViewById(R.id.supplier_name);
        saleButton = view.findViewById(R.id.sale_button);

        final int qantity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY)));
        productName.setText(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME)));
        prodcutQuintity.setText(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY)));
        productPrice.setText(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE)));
        supplierName.setText(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME)));
        final int poisition = cursor.getPosition();

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor.moveToPosition(poisition);
                int itemQuantity = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY));

                if (itemQuantity> 0 ) {
                    // reduce quantity number
                    itemQuantity -- ;
                    // get the item ID
                    int itemID = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry._ID));
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY, itemQuantity);
                    String where = ProductContract.ProductEntry._ID + " =?";
                    //Get the item id which should be updated
                    String [] whereArg = {Integer.toString(itemID)};
                    int rowsAffected = view.getContext().getContentResolver().update(
                            ContentUris.withAppendedId(ProductContract.ProductEntry.CONTENT_URI , itemID),
                            contentValues,
                            where,
                            whereArg);
                    int updatedItemQuantity = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY));
                    prodcutQuintity.setText(updatedItemQuantity+ "");

                    Toast.makeText(view.getContext(), R.string.quantity_updated, Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(view.getContext(), R.string.no_quantity, Toast.LENGTH_SHORT).show();
                {

                }


            }
        });

    }


}
