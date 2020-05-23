package com.example.mysqlbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtComputerName,edtComputerType;
    private Button btnAdd,btnDelete;
    private ListView listView;

    List<Computer> allComputers;
    ArrayList<String> computersName;
    MySqliteHandler sqliteHandler;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtComputerName=findViewById(R.id.edtComputerName);
        edtComputerType=findViewById(R.id.edtComputerType);
        btnAdd=findViewById(R.id.btnAdd);
        btnDelete=findViewById(R.id.btnDelete);
        listView=findViewById(R.id.listView);

        btnAdd.setOnClickListener(MainActivity.this);
        btnDelete.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAdd:
                break;
            case R.id.btnDelete:
                break;
        }

    }
}
