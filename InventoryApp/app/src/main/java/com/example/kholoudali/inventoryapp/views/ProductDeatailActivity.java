package com.example.kholoudali.inventoryapp.views;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kholoudali.inventoryapp.R;
import com.example.kholoudali.inventoryapp.data.ProductContract;

import java.util.List;

public class ProductDeatailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> , View.OnClickListener{

    private static final int PRODUCT_LOADER = 0;
    private Uri uri;
    private EditText product_name;
    private EditText product_quantity;
    private EditText product_price;
    private EditText supplier_name;
    private EditText supplier_email;
    private EditText supplier_number;
    private Button btn_delete;
    private Button btn_plus;
    private Button btn_minus;
    private Button emailOrder;
    private Button phoneOrder;
    private LinearLayout action_layout;
    private String id;
    private int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_deatail);

        product_name = findViewById(R.id.product_name_value);
        product_quantity = findViewById(R.id.product_quantity_value);
        product_price = findViewById(R.id.product_price_value);
        supplier_name = findViewById(R.id.supplier_name_value);
        supplier_email = findViewById(R.id.supplier_email_value);
        supplier_number = findViewById(R.id.supplier_num_value);
        action_layout = findViewById(R.id.action_layout);
        btn_delete= findViewById(R.id.delete_button);
        btn_plus = findViewById(R.id.btn_add_qantitiy);
        btn_minus= findViewById(R.id.btn_minus_qantitiy);
        emailOrder= findViewById(R.id.btn_order_by_email);
        phoneOrder= findViewById(R.id.btn_order_by_call);

        btn_delete.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        emailOrder.setOnClickListener(this);
        phoneOrder.setOnClickListener(this);

        //Get Intent Extras
        uri = getIntent().getData();
        if (uri == null) {
            setTitle(getString(R.string.add_title));
        } else {
            setTitle(getString(R.string.edit_title));
            action_layout.setVisibility(View.VISIBLE);
            List<String> uriSegments = uri.getPathSegments();
            id = uriSegments.get(1);
            getLoaderManager().initLoader(PRODUCT_LOADER, null, this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,
                ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONENUMBER
        };
        //set selection statment and args
        String selection = ProductContract.ProductEntry._ID + " = ?";
        String[] selectionArgs = {id};

        return new CursorLoader(
                this,
                uri,
                projection,
                selection,
                selectionArgs,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            product_name.setText(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME)));
            quantity = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY));
            product_quantity.setText(Integer.toString(quantity));
            product_price.setText(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE)));
            supplier_name.setText(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME)));
            supplier_email.setText(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL)));
            supplier_number.setText(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONENUMBER)));

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() == 0) {
            etText.setError("Empty");
            return true;
        } else
            return false;
    }

    public void insertItem(String name, String price, String quantity, String supplierName, String supplierEmail, String suppllierPhone) {

        long insertedID = -1;
        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, name);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, price);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY, quantity);
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME, supplierName);
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL, supplierEmail);
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONENUMBER, suppllierPhone);

        Uri uriInserted = getContentResolver().insert(ProductContract.ProductEntry.CONTENT_URI, values);
        insertedID = ContentUris.parseId(uriInserted);

        if (insertedID == -1)
            Toast.makeText(this, "ERROR in insert item", Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(this, "Product inserted successfully, the new product id is: " + insertedID, Toast.LENGTH_LONG).show();
            Log.d("SQLLog", "Product inserted successfully, the new product id is: " + insertedID);
        }
        finish();
    }

    private void delteItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_comifrmatio_text);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (uri != null) {
                    String selection = ProductContract.ProductEntry._ID + " = ?";
                    String[] selectionArgs = {id};
                    int rowsDeleted = getContentResolver().delete(uri, selection, selectionArgs);
                    if (rowsDeleted == 0)
                        Toast.makeText(getBaseContext(), getString(R.string.not_deleted), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getBaseContext(), "item deleted successfully", Toast.LENGTH_SHORT).show();

                }
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public int update() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, product_name.getText().toString().trim());
        contentValues.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, product_price.getText().toString().trim());
        contentValues.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME, supplier_name.getText().toString().trim());
        contentValues.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL, supplier_email.getText().toString().trim());
        contentValues.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONENUMBER, supplier_number.getText().toString().trim());
        contentValues.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QAUNTITY, product_quantity.getText().toString().trim());

        String where = ProductContract.ProductEntry._ID + " =?";
        //Get the item id which should be updated
        String [] whereArg = {id};
        int rowsAffected = getContentResolver().update(
                ContentUris.withAppendedId(ProductContract.ProductEntry.CONTENT_URI , Integer.parseInt(id)),
                contentValues,
                where,
                whereArg);
        if (rowsAffected>0)
            Toast.makeText(this, "iteam is updated successfully",Toast.LENGTH_SHORT).show();
        finish();
        return rowsAffected;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.submit) {
            if (isEmpty(product_name) || isEmpty(product_price) || isEmpty(product_quantity) || isEmpty(supplier_name) || isEmpty(supplier_email) || isEmpty(supplier_number)) {
                Toast.makeText(this, R.string.is_empty, Toast.LENGTH_SHORT).show();
            } else {
                if (uri == null)
                    insertItem(product_name.getText().toString(), product_price.getText().toString(), product_quantity.getText().toString(), supplier_name.getText().toString(), supplier_email.getText().toString(), supplier_number.getText().toString());
                else
                    update();

            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        quantity= Integer.parseInt(product_quantity.getText().toString().trim());
        switch (view.getId()){

            case R.id.delete_button:
                delteItem();
                break;
            case R.id.btn_add_qantitiy:
                product_quantity.setText(++ quantity + "");


                break;
            case R.id.btn_minus_qantitiy:
                if (quantity>0)
                    product_quantity.setText(-- quantity +"");
                else
                    Toast.makeText(this, R.string.no_quantity ,Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_order_by_email:
                Intent intentEmail = new Intent(android.content.Intent.ACTION_SENDTO);
                intentEmail.setType("text/plain");
                intentEmail.setData(Uri.parse("mailto:" + supplier_email.getText().toString().trim()));
                intentEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, "Gift New Order");
                String bodyMessage = "Hey, I just to order this item " +
                        supplier_name.getText().toString().trim() +
                        ", thanks!!";
                intentEmail.putExtra(android.content.Intent.EXTRA_TEXT, bodyMessage);
                startActivity(intentEmail);

                break;
            case R.id.btn_order_by_call:
                Intent intentDail = new Intent(Intent.ACTION_DIAL);
                intentDail.setData(Uri.parse("tel:" + supplier_number.getText().toString().trim()));
                startActivity(intentDail);
                break;
        }

    }
}
