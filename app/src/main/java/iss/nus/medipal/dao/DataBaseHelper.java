package iss.nus.medipal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by richard on 4/3/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "medipalData";
    private static final int DATABASE_VERSION = 1;

    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS";

    public static final String ICE_TABLE = "ice";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CONTACT_NO = "contactNo";
    public static final String CONTACT_TYPE = "contactType";
    public static final String DESCRIPTION = "description";
    public static final String SEQUENCE = "sequence";

    private static final String CREATE_ICE_TABLE =
            "CREATE TABLE " + ICE_TABLE +
                    "(" + ID + " INTEGER PRIMARY KEY, " +
                    NAME + " TEXT, " +
                    CONTACT_NO + " TEXT, " +
                    CONTACT_TYPE + " TEXT, " +
                    DESCRIPTION + " TEXT, " +
                    SEQUENCE + " INTEGER)";

    private static DataBaseHelper instance;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null) {
            System.out.println("Creating database..");
            instance = new DataBaseHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ICE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseHelper.class.getName(), "Upgrading data base from " + oldVersion + " to " +  newVersion + ", which will destroy all old data");
        db.execSQL(String.format("%s %s", DROP_TABLE_IF_EXISTS, ICE_TABLE));
        onCreate(db);
    }
}
