package com.resocoder.forecast3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Current::class], version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun currentDao(): CurrentDao

    companion object {
        @Volatile private var instance: WeatherDatabase? = null
        private val lock = Any()

        // if instance is not null, return it; otherwise create it in a synchronized block and return it
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: build(context).also {
                // also save the instance just created
                instance = it
            }
        }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, "weather.db")
                .build()
    }
}