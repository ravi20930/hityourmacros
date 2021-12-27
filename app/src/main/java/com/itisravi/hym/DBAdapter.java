package com.itisravi.hym;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

public class DBAdapter {

    /* 01 Variables ---------------------------------------- */
    private static final String databaseName = "hitYourMacros";
    private static final int databaseVersion = 64;

    /* 02 Database variables ------------------------------- */
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    /* 03 Class DbAdapter ---------------------------------- */
    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    /* 04 DatabaseHelper ------------------------------------ */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, databaseName, null, databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS foodDB (" +
                        " category INT, " +
                        " foodName VARCHAR, " +
                        " carbs DOUBLE, " +
                        " fats DOUBLE, " +
                        " kcals DOUBLE, " +
                        " protein DOUBLE, " +
                        " size VARCHAR);");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS foodEaten (" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " mealId INT, " +
                        " food VARCHAR, " +
                        " kcals DOUBLE, " +
                        " protein DOUBLE, " +
                        " carbs DOUBLE, " +
                        " fats DOUBLE);");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS foodEatenYesterday (" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " mealId INT, " +
                        " food VARCHAR, " +
                        " kcals DOUBLE, " +
                        " protein DOUBLE, " +
                        " carbs DOUBLE, " +
                        " fats DOUBLE);");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS planExerciseList (" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " typeId INT, " +
                        " name VARCHAR, " +
                        " sets INT, " +
                        " reps INT, " +
                        " rest INT, " +
                        " load VARCHAR);");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS routineExerciseList (" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " routineName VARCHAR, " +
                        " day INT, " +
                        " exName VARCHAR, " +
                        " setsR INT, " +
                        " repsR VARCHAR);");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // ! All tables that are going to be dropped need to be listed here
            db.execSQL("DROP TABLE IF EXISTS foodDB");
            db.execSQL("DROP TABLE IF EXISTS foodEaten");
            db.execSQL("DROP TABLE IF EXISTS foodEatenYesterday");
            db.execSQL("DROP TABLE IF EXISTS planExerciseList");
            db.execSQL("DROP TABLE IF EXISTS routineExerciseList");
            onCreate(db);

            String TAG = "Tag";
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
        }
    }


    /* 05 Open database --------------------------------------------------------- */
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    /* 06 Close database --------------------------------------------------------- */
    public void close() {
        DBHelper.close();
    }


    /* 08 Insert data ------------------------------------------------------------ */
    public void insert(String table, String fields, String values) {

        try {
            db.execSQL("INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ")");
        } catch (SQLiteException e) {
            System.out.println("Insert error: " + e.toString());
            Toast.makeText(context, "Error: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /* 09 Count ------------------------------------------------------------------ */
    public int count(String table) {
        try {
            Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + table + "", null);
            mCount.moveToFirst();
            int count = mCount.getInt(0);
            mCount.close();
            return count;
        } catch (SQLiteException e) {
            return -1;
        }
    }

    /* 10 Select ----------------------------------------------------------------- */
    /* Select example:
           Cursor allCategories;
           String fields[] = new String[] {
                                "category_id",
                                "category_name",
                                "category_parent_id"
             };
            allCategories = db.select("categories", fields);
    */
    // Select
    public Cursor select(String table, String[] fields) throws SQLException {
        Cursor mCursor = db.query(table, fields, null, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    public Cursor select(String table, String where, String whereCond, String and, int andCond) throws SQLException {
        Cursor mCursor = db.rawQuery("SELECT * FROM " + table + " WHERE " + where + "= '"+whereCond+"'"+ " AND " + and + "=" + andCond + ";", null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor select(String table, String where, String whereCond) throws SQLException {
        Cursor mCursor = db.rawQuery("SELECT * FROM " + table + " WHERE " + where + "= '"+whereCond+"';", null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor sum(String table, String column, String whereClause, int condition) throws SQLException {
        Cursor mCursor = db.rawQuery("SELECT SUM(" + column + ") AS Total FROM " + table + " WHERE " + whereClause + "=" + condition + ";", null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor sum(String table, String column) throws SQLException {
        Cursor mCursor = db.rawQuery("SELECT SUM(" + column + ") AS Total FROM " + table + ";", null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void copyTable(String tableName, String fromTableName) throws SQLException {
        db.execSQL("INSERT INTO " + tableName + " SELECT * FROM " + fromTableName + ";");
    }

    public void emptyTable(String tableName) throws SQLException {
        db.execSQL("DROP TABLE " + tableName + ";");
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " mealId INT, " +
                    " food VARCHAR, " +
                    " kcals DOUBLE, " +
                    " protein DOUBLE, " +
                    " carbs DOUBLE, " +
                    " fats DOUBLE);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Cursor select(String table, String[] fields, String whereClause, int whereCondition) throws SQLException {
        Cursor mCursor = db.query(table, fields, whereClause + "=" + whereCondition, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    /* 12 Delete ----------------------------------------------------------------- */
    // Delete a particular record
    public int delete(String table, String primaryKey, long _id) throws SQLException {
        return db.delete(table, primaryKey + "=" + _id, null);
    }

    public int delete(String table, String foodName) throws SQLException {
        return db.delete(table,  "foodName = '"+foodName+"'", null);
    }
}
