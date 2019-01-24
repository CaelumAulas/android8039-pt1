package br.com.caelum.twittelum.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.Injetor
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.activity_lista_tweets.*

class ListaTweetsActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, Injetor).get(TweetViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)


        val liveData = viewModel.getLista()

        liveData.observe(this, Observer { tweets ->
            val adapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweets)

            listaTweets.adapter = adapter
        })


        fabAddTweet.setOnClickListener {

            val intencao = Intent(this, TweetActivity::class.java)

            startActivity(intencao)
        }
    }


}













