package com.sayyid.testtechnical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sayyid.testtechnical.SQLite.DatabaseHelper;

public class HomeActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editName,editQty,editExpDate,editTextId, editHarga;

    Button btnAddData;

    Button btnViewAll;

    Button btnUpdate;

    Button btnDelete;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);

        editQty = (EditText)findViewById(R.id.editText_qty);

        editExpDate = (EditText)findViewById(R.id.editText_expdate);

        editTextId = (EditText)findViewById(R.id.editTextId);
        editHarga = (EditText)findViewById(R.id.editTextHarga);

        btnAddData = (Button)findViewById(R.id.button_add);

        btnViewAll = (Button)findViewById(R.id.button_view);

        btnUpdate = (Button)findViewById(R.id.button_update);

        btnDelete = (Button)findViewById(R.id.button_delete);

        AddData();

        viewAll();

        UpdateData();

        deleteData();

    }



    //fungsi hapus

    public void deleteData() {

        btnDelete.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());

                        if (deletedRows > 0)

                            Toast.makeText(HomeActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();

                        else

                            Toast.makeText(HomeActivity.this,"Data Failed to Deleted!",Toast.LENGTH_LONG).show();

                    }

                }

        );

    }



    //fungsi update

    public void UpdateData() {

        btnUpdate.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),

                                editName.getText().toString(),

                                editQty.getText().toString(),

                                editExpDate.getText().toString(),

                                editHarga.getText().toString());

                        if(isUpdate == true)

                            Toast.makeText(HomeActivity.this,"Data Updated",Toast.LENGTH_LONG).show();

                        else

                            Toast.makeText(HomeActivity.this,"Data Failed to Update",Toast.LENGTH_LONG).show();

                    }

                }

        );

    }



    //fungsi tambah

    public void AddData() {

        btnAddData.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        boolean isInserted = myDb.insertData(editName.getText().toString(),

                                editQty.getText().toString(),

                                editExpDate.getText().toString(),

                                editHarga.getText().toString() );

                        if(isInserted == true)

                            Toast.makeText(HomeActivity.this,"Data Iserted",Toast.LENGTH_LONG).show();

                        else

                            Toast.makeText(HomeActivity.this,"Data Not Iserted",Toast.LENGTH_LONG).show();

                    }

                }

        );

    }




    //fungsi menampilkan data

    public void viewAll() {

        btnViewAll.setOnClickListener(

                new View.OnClickListener(){

                    @Override

                    public void onClick(View v) {

                        Cursor res = myDb.getAllData();

                        if(res.getCount() == 0) {

                            // show message

                            showMessage("Error","Noting Found");

                            return;

                        }



                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext() ) {

                            buffer.append("Id :"+ res.getString(0)+"\n");

                            buffer.append("Name :"+ res.getString(1)+"\n");

                            buffer.append("Qty :"+ res.getString(2)+"\n");

                            buffer.append("ExpDate :"+ res.getString(2)+"\n");

                            buffer.append("Harga :"+ res.getString(4)+"\n\n");

                        }



                        // show all data

                        showMessage("Data",buffer.toString());

                    }

                }

        );

    }



    //membuat alert dialog

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);

        builder.setTitle(title);

        builder.setMessage(Message);

        builder.show();

    }

}

