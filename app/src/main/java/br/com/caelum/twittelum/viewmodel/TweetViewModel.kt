package br.com.caelum.twittelum.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.repository.TweetRepository

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {

    fun getLista() = repository.getLista()

    fun insere(tweet: Tweet) = repository.salva(tweet)

}