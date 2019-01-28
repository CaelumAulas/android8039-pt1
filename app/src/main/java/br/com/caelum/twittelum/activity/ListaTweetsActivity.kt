package br.com.caelum.twittelum.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.adapter.TweetAdapter
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

            tweets?.let {
                val adapter = TweetAdapter(it)


                listaTweets.adapter = adapter
            }


        })


        fabAddTweet.setOnClickListener {

            val intencao = Intent(this, TweetActivity::class.java)

            startActivity(intencao)
        }


        val listener = AdapterView.OnItemClickListener { adapter, itemClicado, posicaoClicada, id ->

            val tweet = listaTweets.getItemAtPosition(posicaoClicada) as Tweet

            deletaSeNecessario(tweet)
        }
        listaTweets.onItemClickListener = listener
    }

    private fun deletaSeNecessario(tweet: Tweet) {

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Atenção")
        builder.setIcon(R.drawable.ic_warning)
        builder.setMessage("Deseja excluir o tweet ?")
        builder.setPositiveButton("Sim") { _, _ -> viewModel.deleta(tweet) }
        builder.setNegativeButton("Não", null)

        builder.show()
    }


}













