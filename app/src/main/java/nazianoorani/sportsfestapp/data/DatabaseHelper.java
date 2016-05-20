package nazianoorani.sportsfestapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nazianoorani on 20/05/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Match.db";
    private static final int DATABASE_VERSION = 2;
    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String CreateMatchTable = "CREATE TABLE " + DatabaseContract.MATCH_TABLE + " ("
                + DatabaseContract.match_table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.match_table.DATE_COL + " TEXT NOT NULL,"
                + DatabaseContract.match_table.TIME_COL + " TEXT NOT NULL,"
                + DatabaseContract.match_table.TEAM_A_COL + " TEXT NOT NULL,"
                + DatabaseContract.match_table.TEAM_B_COL + " TEXT NOT NULL,"
                + DatabaseContract.match_table.EVENT + "TEXT NOT NULL,"
                + DatabaseContract.match_table.MATCH_DATE + "TEXT NOT NULL"

                + " );";
        db.execSQL(CreateMatchTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Remove old values when upgrading.
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.MATCH_TABLE);
    }
}
