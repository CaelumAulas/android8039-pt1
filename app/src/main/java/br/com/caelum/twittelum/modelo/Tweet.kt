package br.com.caelum.twittelum.modelo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Tweet(
    var mensagem: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
) {

    override fun toString(): String {
        return mensagem
    }
}