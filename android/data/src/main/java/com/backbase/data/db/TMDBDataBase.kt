package com.backbase.data.db.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalMoviesDetail::class], version = 1, exportSchema = false)
abstract class TMDBDataBase : RoomDatabase(){
    abstract fun dao(): TMDBDAO

    companion object{
        @Volatile
        private var _Intance: TMDBDataBase? = null

        fun getDataBase(appContext: Context): TMDBDataBase{
            val tmp = _Intance
            if(tmp != null){
                return tmp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    appContext,
                    TMDBDataBase::class.java,
                    TMDBDataBase::class.simpleName!!
                ).fallbackToDestructiveMigration()
                    .build()
                _Intance = instance
                return instance
            }
        }
    }
}