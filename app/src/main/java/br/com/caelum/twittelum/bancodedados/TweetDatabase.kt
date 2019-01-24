package br.com.caelum.twittelum.bancodedados

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import br.com.caelum.twittelum.application.TwittellumApplication
import br.com.caelum.twittelum.modelo.Tweet

@Database(entities = [Tweet::class], version = 1)
abstract class TweetDatabase : RoomDatabase() {

    abstract fun getTweetDao(): TweetDAO

    companion object {

        private var tweetDatabase: TweetDatabase? = null

        fun getInstance(): TweetDatabase {

            return tweetDatabase ?: criaTweetDatabase().also { tweetDatabase = it }
        }

        private fun criaTweetDatabase(): TweetDatabase {

            val contexto = TwittellumApplication.getInstance()

            return Room.databaseBuilder(contexto, TweetDatabase::class.java, "TweetDatabase")
                .allowMainThreadQueries()
                .build()
        }
    }

}