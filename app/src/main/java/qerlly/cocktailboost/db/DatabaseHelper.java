package qerlly.cocktailboost.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.compat.BuildConfig;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static final String DB_NAME = "dbCocktail";
    private static final int VERSION = 1;
    public static final String TABLE = "cocktail_data_en";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_INGREDIENTS = "INGREDIENTS";
    public static final String COLUMN_RECIPE = "RECIPE";
    public static final String COLUMN_IMAGE = "IMAGE";
    public static final String COLUMN_TYPE = "TYPE";
    public SQLiteDatabase mDatabase;
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void copyDB() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String mOutFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(mOutFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = mInput.read(buffer)) > 0) {
            mOutput.write(buffer, 0, length);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public void openDB() {
        String path = DB_PATH + DB_NAME;
        mDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public Cursor QueryData(String query) {
        return mDatabase.rawQuery(query, null);
    }

    public void checkAndCopyDB() {
        boolean dbExist = checkDB();
        if (dbExist) {
            Log.d("TAG", "Exist");
        } else {
            this.getReadableDatabase();
        }
        try {
            copyDB();
        } catch (IOException pE) {
            pE.printStackTrace();
        }
    }

    private boolean checkDB() {
        SQLiteDatabase mCheckDB = null;
        try {
            String mPath = DB_PATH + DB_NAME;
            mCheckDB = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch (SQLiteException e){
        }
        if (mCheckDB != null) {
            mCheckDB.close();
        }
        return mCheckDB != null ? true : false;
    }

    @Override
    public synchronized void close() {
        if (mDatabase != null) {
            mDatabase.close();
        }
        super.close();
    }
}
