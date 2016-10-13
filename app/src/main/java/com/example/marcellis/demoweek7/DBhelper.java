package com.example.marcellis.demoweek7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marmm on 9/10/16.
 */
public class DBhelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "reminders.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_REMINDERS = "Reminder";
    public static final String REMINDER_ID = "_id";
    public static final String REMINDER_NAME = "reminder";

    // Creating the table

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_REMINDERS +
                    "(" +
                    REMINDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    REMINDER_NAME +
                    ");";



    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_REMINDERS);
        onCreate (db);
    }
}
