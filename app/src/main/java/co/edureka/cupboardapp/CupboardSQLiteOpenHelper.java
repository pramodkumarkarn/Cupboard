package co.edureka.cupboardapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nl.qbusict.cupboard.Cupboard;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Admin on 11/28/2017.
 */

public class CupboardSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "book.db";
    private static final int DATABASE_VERSION = 1;

    static{
        cupboard().register(Book.class);
    }

    public CupboardSQLiteOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables(); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
