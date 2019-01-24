package br.com.caelum.twittelum.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.caelum.twittelum.bancodedados.TweetDAO
import br.com.caelum.twittelum.bancodedados.TweetDatabase
import br.com.caelum.twittelum.repository.TweetRepository

object Injetor : ViewModelProvider.Factory {

    private val database: TweetDatabase = TweetDatabase.getInstance()
    private val fonteDeDados: TweetDAO = database.getTweetDao()
    private val repository: TweetRepository = TweetRepository(fonteDeDados)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return TweetViewModel(repository) as T
    }

}
