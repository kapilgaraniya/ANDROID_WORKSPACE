package com.example.relam_ex

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        var config = RealmConfiguration.Builder()
            .name("Realm.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(1)
            .build()

        Realm.setDefaultConfiguration(config)

    }
}