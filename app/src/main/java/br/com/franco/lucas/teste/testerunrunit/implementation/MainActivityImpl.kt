package br.com.franco.lucas.teste.testerunrunit.implementation

import android.content.Intent

/**
 * Created by MacMini on 16/03/2018.
 */
interface MainActivityImpl {

    fun refreshRecyclerView()
    fun showRefresh(show : Boolean)
    fun showProgress()
    fun hideProgress()
    fun startSelectedTaskActivity(i: Intent)
}