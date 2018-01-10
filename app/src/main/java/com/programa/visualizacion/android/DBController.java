package com.programa.visualizacion.android;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DBPrograma.db";


    public DBController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE PROGRAMA (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,DIA VARCHAR,FECHA VARCHAR,SESSIONCODE VARCHAR, HORA_INICIO TEXT,HORA_FIN TEXT,TITULO VARCHAR);");
        sqLiteDatabase.execSQL("CREATE TABLE CHARLAS (SESSIONCODE VARCHAR NOT NULL PRIMARY KEY UNIQUE,EVENTO VARCHAR,TITULO VARCHAR,PONENTES VARCHAR, RESUMEN VARCHAR ,SESSION_NAME VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PROGRAMA;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CHARLAS;");
        onCreate(sqLiteDatabase);

    }
}
