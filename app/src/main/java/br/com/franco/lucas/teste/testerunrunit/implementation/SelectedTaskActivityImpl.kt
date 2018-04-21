package br.com.franco.lucas.teste.testerunrunit.implementation

import br.com.franco.lucas.teste.testerunrunit.model.Task

/**
 * Created by MacMini on 21/03/2018.
 */
interface SelectedTaskActivityImpl {

    fun refreshTask(selectedTask: Task)
    fun showProgress()
    fun hideProgress()
    fun showRefresh(show: Boolean)

}