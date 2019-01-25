package br.com.caelum.twittelum.bancodedados

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.caelum.twittelum.modelo.Tweet

@Dao
interface TweetDAO {

    @Insert
    fun insere(tweet: Tweet)

    @Query("select * from Tweet")
    fun getTweets(): LiveData<List<Tweet>>

    @Delete
    fun deleta(tweet: Tweet)

}