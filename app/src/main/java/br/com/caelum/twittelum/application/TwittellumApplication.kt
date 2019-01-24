package br.com.caelum.twittelum.application

import android.app.Application

class TwittellumApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: TwittellumApplication

        fun getInstance(): TwittellumApplication =
            instance
    }
}