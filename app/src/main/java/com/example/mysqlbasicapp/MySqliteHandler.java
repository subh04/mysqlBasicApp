package com.example.mysqlbasicapp;

import android.database.sqlite.SQLiteOpenHelper;

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




}
