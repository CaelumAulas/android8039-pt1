package br.com.caelum.twittelum.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.extensions.decodificaParaBase64
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.Injetor
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.activity_twett.*
import java.io.File

class TweetActivity : AppCompatActivity() {

    private var caminho: String? = null

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, Injetor).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twett)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                carregaFoto()
            } else {
                caminho = null
            }
        }
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

        val localFoto: Uri = defineUri()

        vaiParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto)

        startActivityForResult(vaiParaCamera, 123)

    }

    private fun defineUri(): Uri {

        val nomeArquivo = System.currentTimeMillis()
        caminho = "${getExternalFilesDir("imagens")}/$nomeArquivo.jpg"

        val arquivoVazioFoto = File(caminho)

        val localFoto: Uri = FileProvider.getUriForFile(this, "MeuProvider", arquivoVazioFoto)
        return localFoto
    }


    private fun publicaTweet() {

        val tweet = criaTweet()

        viewModel.insere(tweet)

        Toast.makeText(this, tweet.toString(), Toast.LENGTH_LONG).show()


    }

    private fun criaTweet(): Tweet {
        val conteudo = findViewById<EditText>(R.id.tweet)
        val mensagemDoTweet = conteudo.text.toString()

        val foto = fotoTweetForm.tag as String?

        val tweet = Tweet(mensagemDoTweet, foto)
        return tweet
    }

    private fun carregaFoto() {


        val bitmap = BitmapFactory.decodeFile(caminho)

        val bitmapTratado = Bitmap.createScaledBitmap(bitmap, 300, 300, true)

        fotoTweetForm.setImageBitmap(bitmapTratado)

        fotoTweetForm.scaleType = ImageView.ScaleType.FIT_XY

        val fotoBase64: String = bitmapTratado.decodificaParaBase64()

        fotoTweetForm.tag = fotoBase64
    }


}
