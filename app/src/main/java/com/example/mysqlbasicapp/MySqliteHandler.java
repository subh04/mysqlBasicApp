package com.example.mysqlbasicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySqliteHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="computer.db";
    public static final String TABLE_COMPUTER="computers"; //table name
    public static final String COLUMN_ID ="id";
    public static final String COLUMN_COMPUTER_NAME="computerName";
    public static final String COLUMN_COMPUTER_TYPE="computerType";
    String CREATE_COMPUTER_TABLE="create table "+TABLE_COMPUTER+" ( "+COLUMN_ID+
            " INTEGER PRIMARY KEY, "+COLUMN_COMPUTER_NAME+" text, "+
            COLUMN_COMPUTER_TYPE+" text"+" )";

    public MySqliteHandler(Context context){     //creation of a constructor which has context in the parameter
                                                 //without the super constructor call this constructor will show error

        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COMPUTER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  //this is when you upload on android playstore and after some months you wanna update your database
        db.execSQL("drop table if exists "+TABLE_COMPUTER); //THE OLDER VERSION WILL BE DROPPED AN DA NEWER VERSION WILL BE CREATED
        onCreate(db);


    }
    public void addComputer(Computer computer){
        SQLiteDatabase database=MySqliteHandler.this.getWritableDatabase(); //getWritable is used to add data to the database
        ContentValues values=new ContentValues();
        values.put(COLUMN_COMPUTER_NAME,computer.getComputerName());//column name with the data you wanna add
        values.put(COLUMN_COMPUTER_TYPE,computer.getComputerType());//same as above
        database.insert(TABLE_COMPUTER,null,values); //insert values into the table
        database.close();


    }
    //getting a single computer
    public Computer getComputer(int id){    //we are going to fetch some thing of type computer with its unique id
        SQLiteDatabase database=MySqliteHandler.this.getReadableDatabase();//getReadableDatabase used to read from the database
        Cursor cursor=database.query(TABLE_COMPUTER,new String[]        //cursor is used to query data from the database
                {COLUMN_ID,COLUMN_COMPUTER_NAME,COLUMN_COMPUTER_TYPE},  //new String array created to keep the columns in the database for reading purpose
                COLUMN_ID+" =?",new String[]{String.valueOf(id)},null,null,null);   //COLUMN_ID+" =?" its a selection or can be called as where clause  means we need a computer object of a specific id (new String[]{String.valueOf(id)} these are  selection args)so the string array contains the values of id's
        if (cursor!=null){
            cursor.moveToFirst(); //if we have objects in our database then move the cursor to first object
        }
        Computer computer=new Computer(Integer.parseInt(cursor.getString(0)),  //converting string id to integer
                cursor.getString(1),
                cursor.getString(2)); //calling costructor of the computer class which accepted 3 args

        return computer;
    }

    //getting all the computers so it will return a list of all the objects in the database
    public List<Computer> getAllComputer(){ //List is a ordered collection of data

        List<Computer> computerList=new ArrayList<>();
        String selectAllQuery="select * from "+TABLE_COMPUTER;
        SQLiteDatabase database=MySqliteHandler.this.getReadableDatabase();
        Cursor cursor=database.rawQuery(selectAllQuery,null); //we could also specify the where clause

        if (cursor.moveToFirst()) { //
            do {
                Computer computer = new Computer();
                computer.setId(Integer.parseInt(cursor.getString(0)));
                computer.setComputerName(cursor.getString(1));
                computer.setComputerType(cursor.getString(2));

                computerList.add(computer);
            } while (cursor.moveToNext()); //if there is still some data left then it will move to next
        }
        return computerList;


    }
    //update single computer
    public int updateComputer(Computer computer){
        SQLiteDatabase database=MySqliteHandler.this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_COMPUTER_NAME,computer.getComputerName());
        values.put(COLUMN_COMPUTER_TYPE,computer.getComputerType());
        return  database.update(TABLE_COMPUTER,values,COLUMN_ID+" =?",
                new  String[] {String.valueOf(computer.getId())}); //update the table object of specific id

    }

    //deleting a single computer object
    public void deleteComputer(Computer computer){
        SQLiteDatabase database=MySqliteHandler.this.getWritableDatabase();
        database.delete(TABLE_COMPUTER,COLUMN_ID+" =?",new String[]{String.valueOf(computer.getId())});
        database.close();
    }


}
