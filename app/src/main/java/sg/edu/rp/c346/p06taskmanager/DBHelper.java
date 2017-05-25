package sg.edu.rp.c346.p06taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 14049561 on 25/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "taskmanager.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "task";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_name= "name";
    private static final String COLUMN_description = "description";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_name + " TEXT," + COLUMN_description + " TEXT )";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");


    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * from "+TABLE_TASK+" WHERE "+COLUMN_ID+"="+id,null);
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }


    public long insertTask(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_name, name);
        values.put(COLUMN_description, description);
        long result = db.insert(TABLE_TASK, null, values);
        db.close();
        Log.d("SQL Insert",""+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Task> getAllTask() {
        ArrayList<Task> task = new ArrayList<Task>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_name + "," + COLUMN_description  + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                task.add(new Task(id,name,description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return task;
    }

}
