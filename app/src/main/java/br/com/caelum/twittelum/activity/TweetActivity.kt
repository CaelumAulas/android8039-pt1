package br.com.caelum.twittelum.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.Injetor
import br.com.caelum.twittelum.viewmodel.TweetViewModel

class TweetActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, Injetor).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twett)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.tweet_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.salvar_tweet_menu -> {
                publicaTweet()

                finish()
                true
            }

            R.id.foto_tweet_menu -> {

                vaiParaCamera()

                true
            }

            android.R.id.home -> {
                finish()
                true
            }
            else -> false

        }
    }

    private fun vaiParaCamera() {

        val vaiParaCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivity(vaiParaCamera)

    }


    private fun publicaTweet() {

        val conteudo = findViewById<EditText>(R.id.tweet)
        val mensagemDoTweet = conteudo.text.toString()

        val tweet = Tweet(mensagemDoTweet)

        viewModel.insere(tweet)

        Toast.makeText(this, tweet.toString(), Toast.LENGTH_LONG).show()


    }
}
