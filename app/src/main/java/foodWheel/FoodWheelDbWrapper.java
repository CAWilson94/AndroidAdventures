package foodWheel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import db.DbContract;
import db.DbWrapper;

import static android.R.id.list;

/**
 * Created by Charlotte on 23/04/2017.
 */

public class FoodWheelDbWrapper extends DbWrapper {

    public static void addFoodChoice(Context context, FoodWheel foodWheel) {
        // Reference to writable DB
        SQLiteDatabase db = getWritableDatabase(context);
        // Create content values to add key "column/value"
        ContentValues values = new ContentValues();
        values.put(DbContract.FoodwheelEntry.COLUMN_NAME_FOOD_NAME, foodWheel.getFoodName());
        // Insert
        db.insert(DbContract.FoodwheelEntry.TABLE_NAME, null, values);
        // Close
        db.close();
    }

    public static FoodWheel getRandomFoodChoice(Context context) {
        Random ran = new Random();
        int x = ran.nextInt(getAllFoodChoices(context).size()) + 1;
        Log.d("intX", Integer.toString(x));
        FoodWheel fw = getFoodChoice(context, x);
        return fw;
    }

    public static FoodWheel getFoodChoice(Context context, int id) {
        // Reference to readable db
        SQLiteDatabase db = getReadableDatabase(context);
        // Build Query
        String[] projection = {DbContract.FoodwheelEntry.COLUMN_NAME_ID, DbContract.FoodwheelEntry.COLUMN_NAME_FOOD_NAME};
        String sortOrder = DbContract.FoodwheelEntry.COLUMN_NAME_ID + " DESC";
        Cursor cursor = db.query(DbContract.FoodwheelEntry.TABLE_NAME, // The table name
                projection, // The columns to return
                DbContract.FoodwheelEntry.COLUMN_NAME_ID + " = ? ", // Column for the where clause
                new String[]{String.valueOf(id)},
                null, // Group by rows
                null, // Filter row groups
                sortOrder, // Sort order
                null); // No limit
        // If you get results, get the first one
        if (cursor != null) {
            cursor.moveToFirst();
        }
        // Build a new foodWheel object
        FoodWheel foodWheel = new FoodWheel();
        foodWheel.setFoodId(Integer.parseInt(cursor.getString(0))); //May need to change later..
        foodWheel.setFoodName(cursor.getString(1)); //May need to change later..
        // Return foodWheel choice
        db.close();
        return foodWheel;
    }

    public static List<FoodWheel> getAllFoodChoices(Context context) {
        List<FoodWheel> foodWheels = new LinkedList<FoodWheel>();
        // Reference to readable db
        SQLiteDatabase db = getReadableDatabase(context);
        // Build the query
        String query = "SELECT  * FROM " + DbContract.FoodwheelEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        // Go over each row, build foodWheel and add it to the list
        FoodWheel foodWheel = null;
        if (cursor.moveToFirst()) {
            do {
                foodWheel = new FoodWheel();
                foodWheel.setFoodId(Integer.parseInt(cursor.getString(0))); // May change
                foodWheel.setFoodName(cursor.getString(1)); // May change
                // add foodWheel to foodWheels
                foodWheels.add(foodWheel);
            } while (cursor.moveToNext());
        }
        db.close();
        // return all foodWheels
        return foodWheels;
    }

    public static int updateFoodChoice(Context context, FoodWheel foodWheel) {
        // Reference to writable db
        SQLiteDatabase db = getWritableDatabase(context);
        // Create content values to add key "column/value"
        ContentValues values = new ContentValues();
        values.put(DbContract.FoodwheelEntry.COLUMN_NAME_FOOD_NAME, foodWheel.getFoodName());
        // Update row
        int i = db.update(DbContract.FoodwheelEntry.TABLE_NAME, // Table name
                values, // Column/Values
                DbContract.FoodwheelEntry.COLUMN_NAME_ID + " = ?", // Selections
                new String[]{String.valueOf(foodWheel.getFoodId())}); // Selection args
        // Close db
        db.close();
        return i;
    }

    public static void deleteFoodChoice(Context context, FoodWheel foodWheel) {
        // Reference to writable db
        SQLiteDatabase db = getWritableDatabase(context);
        // Delete
        db.delete(DbContract.FoodwheelEntry.TABLE_NAME, // Table name
                DbContract.FoodwheelEntry.COLUMN_NAME_ID + " = ?", // Selection
                new String[]{String.valueOf(foodWheel.getFoodId())}); // Selection args
        // Close db
        db.close();
    }

    public static void initialFoodWheelFill(Context context, SQLiteDatabase db) {
        String[] foodNames = {"Subway", "Coffee and Cookies", "Barburrito", "Sainsburys", "Ichiban"};
        for (int i = 0; i < foodNames.length; i++) {
            ContentValues cv = new ContentValues();
            cv.put(DbContract.FoodwheelEntry.COLUMN_NAME_FOOD_NAME, foodNames[i]);
            db.insert(DbContract.FoodwheelEntry.TABLE_NAME, null, cv);
        }
    }
}
