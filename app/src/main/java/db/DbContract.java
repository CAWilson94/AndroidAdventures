package db;

import android.provider.BaseColumns;

/**
 * Created by Charlotte on 22/04/2017.
 */

public class DbContract {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "notes.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String REAL_TYPE = " REAL";
    private static final String DATE_TYPE = " DATE";
    private static final String STRING_TYPE = " STRING";
    private static final String INTEGER_TYPE = " INTEGER ";
    private static final String COMMA_SEP = " , ";

    private DbContract() {
    }

    public static class BlogEntry implements BaseColumns {
        public static final String TABLE_NAME = "blog";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ENTRY = "entry";
        public static final String COLUMN_NAME_DATE = "date";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_ENTRY + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_DATE + DATE_TYPE + " ) ";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class FoodwheelEntry implements BaseColumns {
        public static final String TABLE_NAME = "food_wheel";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_FOOD_NAME = "name";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_FOOD_NAME + TEXT_TYPE + " ) ";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    }


}
