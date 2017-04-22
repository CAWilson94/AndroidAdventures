package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static db.DbWrapper.getReadableDatabase;
import static db.DbWrapper.getWritableDatabase;

/**
 * Created by Charlotte on 22/04/2017.
 */

public class BlogEntryDbWrapper {
    public static String getLatestEntry(Context context) {
        String latestEntry = "bob";
        SQLiteDatabase db = getReadableDatabase(context);
        String[] projection = {DbContract.BlogEntry.COLUMN_NAME_ENTRY, DbContract.BlogEntry.COLUMN_NAME_ID};
        String sortOrder =
                DbContract.BlogEntry.COLUMN_NAME_ID + " DESC";
        Cursor cursor = db.query(
                DbContract.BlogEntry.TABLE_NAME,                           // The table to query
                projection,                                                     // The columns to return
                null,                                                           // The columns for the WHERE clause
                null,                                                          // The values for the WHERE clause
                null,                                                           // don't group the rows
                null,                                                           // don't filter by row groups
                sortOrder,                                                      // The sort order
                "1"                                                             // The limit
        );
        while (cursor.moveToNext()) {
            latestEntry = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.BlogEntry.COLUMN_NAME_ENTRY));
        }
        return latestEntry;
    }

    public static String getLatestName(Context context) {
        String latestName = "bob";
        SQLiteDatabase db = getReadableDatabase(context);
        String[] projection = {DbContract.BlogEntry.COLUMN_NAME_NAME, DbContract.BlogEntry.COLUMN_NAME_ID};
        String sortOrder =
                DbContract.BlogEntry.COLUMN_NAME_ID + " DESC";
        Cursor cursor = db.query(
                DbContract.BlogEntry.TABLE_NAME,                           // The table to query
                projection,                                                     // The columns to return
                null,                                                           // The columns for the WHERE clause
                null,                                                          // The values for the WHERE clause
                null,                                                           // don't group the rows
                null,                                                           // don't filter by row groups
                sortOrder,                                                      // The sort order
                "1"                                                             // The limit
        );
        while (cursor.moveToNext()) {
            latestName = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.BlogEntry.COLUMN_NAME_NAME));
        }
        return latestName;
    }

    public static void setLatestBlogPost(Context context, String name, String entry) {
        SQLiteDatabase db = getWritableDatabase(context);
        ContentValues cv = new ContentValues();
        cv.put(DbContract.BlogEntry.COLUMN_NAME_ENTRY, entry);
        cv.put(DbContract.BlogEntry.COLUMN_NAME_NAME, name);
        db.insert(DbContract.BlogEntry.TABLE_NAME, null, cv);
    }
}
