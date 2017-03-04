package iss.nus.medipal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by richard on 4/3/17.
 */

public class DBDAO {
    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;

    public DBDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(context);
        open();
    }

    public void open() {
        if (dbHelper == null) {
            DataBaseHelper.getHelper(mContext);
        }

        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }

        database = null;
    }
}
