package br.com.caelum.twittelum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.extensions.CarregadorDeImagem
import br.com.caelum.twittelum.modelo.Tweet
import kotlinx.android.synthetic.main.item_tweet.view.*

class TweetAdapter(private val tweets: List<Tweet>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)

        val tweet = tweets[position]

        val view : View = inflater.inflate(R.layout.item_tweet, parent, false)


        view.item_conteudo.text = tweet.mensagem

        tweet.foto?.let { fotoBase64->
            view.item_foto.visibility = View.VISIBLE

            view.item_foto.setImageBitmap(CarregadorDeImagem.decodifica(fotoBase64))
        }





        return view
    }

    override fun getItem(position: Int): Any = tweets[position]

    override fun getItemId(position: Int): Long = tweets[position].id.toLong()

    override fun getCount(): Int = tweets.size

}