package dachuangchuang.group.thething.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 边园 on 2016/1/25.
 */
public class ImageDatabaseManager {
    private ImageDatabaseHelper imageDatabaseHelper;
    private SQLiteDatabase db;

    private String name = "image";
    private SQLiteDatabase.CursorFactory factory = null;
    private int version = 1;

    public ImageDatabaseManager(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        this.factory = factory;
        this.version = version;

        imageDatabaseHelper = new ImageDatabaseHelper(context, name, factory, version);
    }

    public ImageDatabaseManager(Context context) {
        imageDatabaseHelper = new ImageDatabaseHelper(context, name, factory, version);
    }
}
