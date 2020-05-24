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
        sqliteHandler=new MySqliteHandler(MainActivity.this);
        allComputers=sqliteHandler.getAllComputer();
        computersName=new ArrayList<>();    //lists can be instantiated by ArrayLists

        if(allComputers.size()>0){
            for(int i=0;i<allComputers.size();i++){
                Computer computer=allComputers.get(i);
                computersName.add(computer.getComputerName()+" - "+computer.getComputerType());


            }

        }
        adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,computersName);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAdd:

                if(edtComputerName.getText().toString().matches("")||edtComputerType.getText().toString().matches("")){
                    return;
                }

               Computer computer=new Computer(edtComputerName.getText().toString(),edtComputerType.getText().toString());

               allComputers.add(computer);
               sqliteHandler.addComputer(computer);
               computersName.add(computer.getComputerName()+" - "+computer.getComputerType());
               edtComputerName.setText("");
               edtComputerType.setText("");

                break;
            case R.id.btnDelete:

                if (allComputers.size()>0){
                    computersName.remove(0);
                    sqliteHandler.deleteComputer(allComputers.get(0));
                    allComputers.remove(0);
                }else {
                    return;
                }

                break;
        }
        adapter.notifyDataSetChanged();

    }
}
