package com.example.marcellis.demoweek7;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by marmm on 10/2/16.
 */

public class RemindersContentprovider extends ContentProvider {


// database
    private SQLiteDatabase database;
    private DBhelper dbHelper;

    static final String _ID = DBhelper.REMINDER_ID;
    static final String REMINDER_NAME = DBhelper.REMINDER_NAME;


// provider
    private static final String AUTHORITY = "com.example.marcellis.demoweek7.remindersprovider";
    private static final String REMINDERS_TABLE = "reminders";


    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + REMINDERS_TABLE);

    public static final int REMINDERS = 1;
    public static final int REMINDERS_ID = 2;


    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, REMINDERS_TABLE, REMINDERS);
        sURIMatcher.addURI(AUTHORITY, REMINDERS_TABLE + "/#", REMINDERS_ID);
    }


    @Override
    public boolean onCreate() {
        dbHelper = new DBhelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {


            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables(dbHelper.TABLE_REMINDERS);

            int uriType = sURIMatcher.match(uri);

            switch (uriType) {
                case REMINDERS_ID:
                    queryBuilder.appendWhere(dbHelper.REMINDER_ID + "="
                            + uri.getLastPathSegment());
                    break;
                case REMINDERS:
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI");
            }

            Cursor cursor = queryBuilder.query(dbHelper.getReadableDatabase(),
                    projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }




    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uriType = sURIMatcher.match(uri);

        database = dbHelper.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case  REMINDERS:
                id = database.insert(dbHelper.TABLE_REMINDERS, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(REMINDERS_TABLE + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
