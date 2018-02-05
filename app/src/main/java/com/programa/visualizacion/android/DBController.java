package com.programa.visualizacion.android;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DBController extends SQLiteOpenHelper {

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

    public static void insertRows(List<String> programme1, List<String> programme2, List<String> programme1Schedule, List<String> programme2Schedule, SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("delete from CHARLAS");
        sqLiteDatabase.execSQL("delete from PROGRAMA");


        String[] parts;
        String part0;
        String part1;
        String part2;
        String part3;
        String part4;
        String date;
        int counter = 0;

        for (String row : programme1) {

            parts = row.split("--");

            part0 = (counter < parts.length && parts[0] != null) ? parts[0] : "";
            counter++;
            part1 = (counter < parts.length && parts[1] != null) ? parts[1] : "";
            counter++;
            part2 = (counter < parts.length && parts[2] != null) ? parts[2] : "";
            counter++;
            part3 = (counter < parts.length && parts[3] != null) ? parts[3] : "";
            counter++;
            part4 = (counter < parts.length && parts[4] != null) ? parts[4] : "";
            counter = 0;

            sqLiteDatabase.execSQL("INSERT INTO CHARLAS (SESSIONCODE, EVENTO, TITULO, PONENTES, RESUMEN) VALUES ("
                    + "'" + part1 + "' , "
                    + "'" + part0 + "' , "
                    + "'" + part2 + "' , "
                    + "'" + part3 + "' , "
                    + "'" + part4 + "');");
        }

        for (String row : programme2) {

            parts = row.split("--");

            part0 = (counter < parts.length && parts[0] != null) ? parts[0] : "";
            counter++;
            part1 = (counter < parts.length && parts[1] != null) ? parts[1] : "";
            counter++;
            part2 = (counter < parts.length && parts[2] != null) ? parts[2] : "";
            counter++;
            part3 = (counter < parts.length && parts[3] != null) ? parts[3] : "";
            counter++;
            part4 = (counter < parts.length && parts[4] != null) ? parts[4] : "";
            counter = 0;

            sqLiteDatabase.execSQL("INSERT INTO CHARLAS (SESSIONCODE, EVENTO, TITULO, PONENTES, RESUMEN) VALUES ("
                    + "'" + part1 + "' , "
                    + "'" + part0 + "' , "
                    + "'" + part2 + "' , "
                    + "'" + part4 + "' , "
                    + "'" + part3 + "');");
        }

        for (String row : programme1Schedule) {

            parts = row.split("--");

            part0 = (counter < parts.length && parts[0] != null) ? parts[0] : "";
            counter++;
            part1 = (counter < parts.length && parts[1] != null) ? parts[1] : "";
            counter++;
            part2 = (counter < parts.length && parts[2] != null) ? parts[2] : "";
            counter++;
            part3 = (counter < parts.length && parts[3] != null) ? parts[3] : "";
            counter++;
            part4 = (counter < parts.length && parts[4] != null) ? parts[4] : "";
            counter = 0;


            if (part4.equals("Monday")){

                date = "09/25/2017";
            } else {
                date = "09/26/2017";
            }

            if (!part1.contains("(")) {
                    sqLiteDatabase.execSQL("INSERT INTO PROGRAMA (DIA, FECHA, SESSIONCODE, HORA_INICIO, HORA_FIN, TITULO) VALUES ("
                            + "'" + part4 + "' , "
                            + "'" + date + "' , "
                            + "'" + "" + "' , "
                            + "'" + part0.split("-")[0] + "' , "
                            + "'" + part0.split("-")[1] + "' , "
                            + "'" + part1 + "');");

            } else {

                sqLiteDatabase.execSQL("INSERT INTO PROGRAMA (DIA, FECHA, SESSIONCODE, HORA_INICIO, HORA_FIN, TITULO) VALUES ("
                        + "'" + part4 + "' , "
                        + "'" + date + "' , "
                        + "'" + part1.replaceAll("()", "") + "' , "
                        + "'" + part0.split("-")[0] + "' , "
                        + "'" + part0.split("-")[1] + "' , "
                        + "'" + "" + "');");

                sqLiteDatabase.execSQL("INSERT INTO PROGRAMA (DIA, FECHA, SESSIONCODE, HORA_INICIO, HORA_FIN, TITULO) VALUES ("
                        + "'" + part4 + "' , "
                        + "'" + date + "' , "
                        + "'" + part2.replaceAll("()", "") + "' , "
                        + "'" + part0.split("-")[0] + "' , "
                        + "'" + part0.split("-")[1] + "' , "
                        + "'" + "" + "');");

                sqLiteDatabase.execSQL("INSERT INTO PROGRAMA (DIA, FECHA, SESSIONCODE, HORA_INICIO, HORA_FIN, TITULO) VALUES ("
                        + "'" + part4 + "' , "
                        + "'" + date + "' , "
                        + "'" + part3.replaceAll("()", "") + "' , "
                        + "'" + part0.split("-")[0] + "' , "
                        + "'" + part0.split("-")[1] + "' , "
                        + "'" + "" + "');");
            }
        }

        for (String row : programme2Schedule) {

            parts = row.split("--");

            part0 = (counter < parts.length && parts[0] != null) ? parts[0] : "";
            counter++;
            part1 = (counter < parts.length && parts[1] != null) ? parts[1] : "";
            counter++;
            part2 = (counter < parts.length && parts[2] != null) ? parts[2] : "";
            counter++;
            part3 = (counter < parts.length && parts[3] != null) ? parts[3] : "";
            counter++;
            part4 = (counter < parts.length && parts[4] != null) ? parts[4] : "";
            counter = 0;


            if (part4.equals("Monday")){

                date = "09/25/2017";
            } else {
                date = "09/26/2017";
            }

            if (!part1.contains("(")) {
                sqLiteDatabase.execSQL("INSERT INTO PROGRAMA (DIA, FECHA, SESSIONCODE, HORA_INICIO, HORA_FIN, TITULO) VALUES ("
                        + "'" + part4 + "' , "
                        + "'" + date + "' , " //FECHA
                        + "'" + "" + "' , "
                        + "'" + part0.split("-")[0] + "' , "
                        + "'" + part0.split("-")[1] + "' , "
                        + "'" + part1 + "');");

            } else {

                sqLiteDatabase.execSQL("INSERT INTO PROGRAMA (DIA, FECHA, SESSIONCODE, HORA_INICIO, HORA_FIN, TITULO) VALUES ("
                        + "'" + part4 + "' , "
                        + "'" + date + "' , "
                        + "'" + part1.replaceAll("()", "") + "' , "
                        + "'" + part0.split("-")[0] + "' , "
                        + "'" + part0.split("-")[1] + "' , "
                        + "'" + "" + "');");

                sqLiteDatabase.execSQL("INSERT INTO PROGRAMA (DIA, FECHA, SESSIONCODE, HORA_INICIO, HORA_FIN, TITULO) VALUES ("
                        + "'" + part4 + "' , "
                        + "'" + date + "' , "
                        + "'" + part2.replaceAll("()", "") + "' , "
                        + "'" + part0.split("-")[0] + "' , "
                        + "'" + part0.split("-")[1] + "' , "
                        + "'" + "" + "');");

                sqLiteDatabase.execSQL("INSERT INTO PROGRAMA (DIA, FECHA, SESSIONCODE, HORA_INICIO, HORA_FIN, TITULO) VALUES ("
                        + "'" + part4 + "' , "
                        + "'" + date + "' , "
                        + "'" + part3.replaceAll("()", "") + "' , "
                        + "'" + part0.split("-")[0] + "' , "
                        + "'" + part0.split("-")[1] + "' , "
                        + "'" + "" + "');");
            }
        }
    }
}

