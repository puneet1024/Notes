package com.example.notes;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Note.class}, version=1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance; // to create our class singleton so that one one instance is used in all the class

    public abstract NoteDao noteDao(); //we can use this method to access the database operation methods that we added in the noteDao interface

    //creating out database - singleton

    //syncronised means only one thread can access the database at a moment so we dont accidently create 2 instances of the database
    public static synchronized NoteDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
            //fallbackToDestructiveMigration() to handle the migration to new schema when we change the version number
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private NoteDao noteDao;

        public PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }
}
