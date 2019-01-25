package br.com.caelum.twittelum.repository

import br.com.caelum.twittelum.bancodedados.TweetDAO
import br.com.caelum.twittelum.modelo.Tweet

class TweetRepository(private val fonteDeDados: TweetDAO) {

    fun getLista() = fonteDeDados.getTweets()

    fun salva(tweet: Tweet) = fonteDeDados.insere(tweet)

    fun deleta(tweet: Tweet) = fonteDeDados.deleta(tweet)

}
