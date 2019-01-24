package br.com.caelum.twittelum.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancodedados.TweetDatabase
import br.com.caelum.twittelum.modelo.Tweet
import kotlinx.android.synthetic.main.activity_lista_tweets.*

class ListaTweetsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)


        fabAddTweet.setOnClickListener {

            val intencao = Intent(this, TwettActivity::class.java)

            startActivity(intencao)
        }
    }

    override fun onResume() {
        super.onResume()

        val database = TweetDatabase.getInstance()
        val dao = database.getTweetDao()

        val tweets = dao.getTweets()

        val adapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweets)

        listaTweets.adapter = adapter
    }
}













