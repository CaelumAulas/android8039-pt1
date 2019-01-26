package br.com.caelum.twittelum.bancodedados

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

object Migracao1Para2 : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {

        database.execSQL("alter table Tweet add column foto text")
    }

}
