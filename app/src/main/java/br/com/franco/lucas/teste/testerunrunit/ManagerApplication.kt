package br.com.franco.lucas.teste.testerunrunit

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration



/**
 * Created by MacMini on 19/03/2018.
 */
class ManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build())
    }
}