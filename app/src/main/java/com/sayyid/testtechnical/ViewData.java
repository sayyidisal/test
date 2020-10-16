package com.sayyid.testtechnical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sayyid.testtechnical.SQLite.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewData extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<HashMap<String, String>> userList = (ArrayList<HashMap<String, String>>) db.getAllData();
        ListView lv = (ListView) findViewById(R.id.list);
        ListAdapter adapter = new SimpleAdapter(ViewData.this, userList, R.layout.list_row,new String[]{"ID","NAME","QTY", "EXPDATE", "HARGA"}, new int[]{R.id.id, R.id.name, R.id.qty, R.id.expdate, R.id.harga});
        lv.setAdapter(adapter);
    }
}