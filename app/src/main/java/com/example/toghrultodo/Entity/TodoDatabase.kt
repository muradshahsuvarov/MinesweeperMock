package com.example.toghrultodo.Entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.toghrultodo.Converters


@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract  class TodoDatabase : RoomDatabase() {

    abstract fun todoDao() : TodoDao

    companion object{
        @Volatile
        private var DbObject: TodoDatabase? = null

        fun getDB(context: Context): TodoDatabase{
            val tmpObject = DbObject
            var objectDB : TodoDatabase?
            if(tmpObject != null)
                return tmpObject

            synchronized(this)
            {
                objectDB = Room.databaseBuilder( context.applicationContext, TodoDatabase::class.java,
                    "todo_database"
                ).build()
                DbObject = objectDB
                return objectDB as TodoDatabase
            }
        }
    }

}