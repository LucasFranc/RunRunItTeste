package br.com.franco.lucas.teste.testerunrunit.data

import br.com.franco.lucas.teste.testerunrunit.model.Task
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by MacMini on 20/03/2018.
 */

class TasksDAO() {

    private var realm: Realm = Realm.getDefaultInstance()

    constructor(realmTest : Realm) : this(){
        realm = realmTest
    }

    val tasks: List<Task>
        get() = realm.where(Task::class.java).findAll()

    fun saveAll(tasks: List<Task>) {
        realm.beginTransaction()
        realm.insertOrUpdate(tasks)
        realm.commitTransaction()
    }

    fun queryedTasks(): RealmResults<Task> {
        return realm.where(Task::class.java)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll()
    }

}
