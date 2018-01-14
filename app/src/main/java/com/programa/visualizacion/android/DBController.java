package com.programa.visualizacion.android;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

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

    public static void insertRows(List<String> programme1, List<String> programme2, SQLiteDatabase sqLiteDatabase){

        String[] parts;
        for (String row : programme1){

            parts = row.split("--");

            String part0 = (parts[0] != null)?parts[0]:"";
            String part1 = (parts[1] != null)?parts[1]:"";
            String part2 = (parts[2] != null)?parts[2]:"";
            String part3 = (parts[3] != null)?parts[3]:"";
            String part4 = (parts[4] != null)?parts[4]:"";

            sqLiteDatabase.execSQL("INSERT INTO CHARLAS (SESSIONCODE, EVENTO, TITULO, PONENTES, RESUMEN) VALUES ("
                    + part1 + ", "
                    + part0 + ", "
                    + part2 + ", "
                    + part3 + ", "
                    + part4 + ");");
        }

        for (String row: programme2){

            parts = row.split("--");

            String part0 = (parts[0] != null)?parts[0]:"";
            String part1 = (parts[1] != null)?parts[1]:"";
            String part2 = (parts[2] != null)?parts[2]:"";
            String part3 = (parts[3] != null)?parts[3]:"";
            String part4 = (parts[4] != null)?parts[4]:"";

            sqLiteDatabase.execSQL("INSERT INTO CHARLAS (SESSIONCODE, EVENTO, TITULO, PONENTES, RESUMEN) VALUES ("
                    + part1 + ", "
                    + part0 + ", "
                    + part2 + ", "
                    + part4 + ", "
                    + part3 + ");");
        }


    }
}
