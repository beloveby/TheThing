package dachuangchuang.group.thething.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 边园 on 2016/1/24.
 */
public class ImageDatabaseHelper extends SQLiteOpenHelper {
    public ImageDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS image" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, identification VARCHAR, update_time TIMESTAMP)");
        db.execSQL("CREATE TABLE IF NOT EXISTS tab" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR)");
        db.execSQL("CREATE TABLE IF NOT EXISTS image_tab" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, image_id INTEGER, tab_id INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
