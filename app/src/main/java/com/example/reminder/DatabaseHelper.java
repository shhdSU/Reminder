package com.example.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //DB name
    static final String dbName = "RemindersDatabase.db";
    //Tasks table
    static final String taskTable = "Tasks";
    static final String taskId = "ID";
    static final String taskTitle = "Title";
    static final String taskDate = "Date";
    static final String taskTime = "Time";
    static final String taskImportance = "Importance";

    public DatabaseHelper(Context context) {
        super(context, dbName, null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create tasks table
        sqLiteDatabase.execSQL("CREATE TABLE "+taskTable+"("+ taskId+" INTEGER primary key, "+taskTitle+" TEXT, "+taskDate+" TEXT, "+taskTime+" TEXT, "+taskImportance+" INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertData(int id, String title, String date, String time, int isImportant){
        SQLiteDatabase PDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(taskId, id);
        contentValues.put(taskTitle, title);
        contentValues.put(taskDate, date);
        contentValues.put(taskTime, time);
        contentValues.put(taskImportance, isImportant);

        long result = PDB.insert(taskTable, taskId, contentValues);
        if(result==-1)
            return false;
        return true;
    }
     public Task retrieveReminder (int id){
         SQLiteDatabase PDB = this.getReadableDatabase();
         Cursor c = PDB.query(taskTable, new String[]{taskTitle, taskDate, taskTime, taskImportance},"ID = ? ", new String[]{id+""},null,null,null);
         //get the columns indexes
         int titleColIndex= c.getColumnIndex(taskTitle);
         int dateColIndex= c.getColumnIndex(taskDate);
         int timeColIndex= c.getColumnIndex(taskTime);
         int importanceColIndex= c.getColumnIndex(taskImportance);
         //create a task object
         Task newTask = new Task();
         //retrieve and assign
         if(c.moveToNext()) {
             newTask.title = c.getString(titleColIndex);
             newTask.date = c.getString(dateColIndex);
             newTask.time = c.getString(timeColIndex);
             int imp = c.getInt(importanceColIndex);
             // if imp = 1 then its level of importance is HIGH, else it is LOW

             if (imp == 1) {
                 newTask.important = true;
             } else if (imp == 0) {
                 newTask.important = false;
             }
         }

         return newTask;
     }

}
