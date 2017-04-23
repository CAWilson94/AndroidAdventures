package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import foodWheel.FoodWheelDbWrapper;


/**
 * Created by Charlotte on 22/04/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private Context context;

    public DbHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.BlogEntry.CREATE_TABLE);
        db.execSQL(DbContract.FoodwheelEntry.CREATE_TABLE);
        FoodWheelDbWrapper.initialFoodWheelFill(context,db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.BlogEntry.DELETE_TABLE);
        db.execSQL(DbContract.FoodwheelEntry.DELETE_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
