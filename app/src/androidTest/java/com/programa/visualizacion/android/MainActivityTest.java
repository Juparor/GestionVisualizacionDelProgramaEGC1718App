package com.programa.visualizacion.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.programa.visualizacion.android.database.DBController;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends AndroidTestCase {

    private Context instrumentationCtx;
    private DBController dbController;

    @Before
    public void setup(){
        instrumentationCtx = InstrumentationRegistry.getTargetContext();
        dbController = new DBController(instrumentationCtx);
    }

    @Test
    public void dbConnectionTest(){

        SQLiteDatabase database = dbController.getReadableDatabase();
        Assert.assertNotNull(database);

    }


}
