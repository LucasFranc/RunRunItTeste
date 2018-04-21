package br.com.franco.lucas.teste.testerunrunit.presenter

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.franco.lucas.teste.testerunrunit.R
import br.com.franco.lucas.teste.testerunrunit.adapter.TaskAdapter
import br.com.franco.lucas.teste.testerunrunit.callbacks.OnAdapterClickCallback
import br.com.franco.lucas.teste.testerunrunit.callbacks.OnResponseCallback
import br.com.franco.lucas.teste.testerunrunit.callbacks.OnRetryClickCallback
import br.com.franco.lucas.teste.testerunrunit.data.TasksDAO
import br.com.franco.lucas.teste.testerunrunit.implementation.MainActivityImpl
import br.com.franco.lucas.teste.testerunrunit.model.Task
import br.com.franco.lucas.teste.testerunrunit.repository.TasksRepository
import br.com.franco.lucas.teste.testerunrunit.ui.SelectedTaskActivity
import br.com.franco.lucas.teste.testerunrunit.utils.Constants
import br.com.franco.lucas.teste.testerunrunit.utils.ErrorManager


/**
 * Created by MacMini on 16/03/2018.
 */
class MainActivityPresenter : OnResponseCallback, OnAdapterClickCallback {

    private lateinit var activity: AppCompatActivity
    private lateinit var impl: MainActivityImpl
    private lateinit var repository : TasksRepository
    private var list = ArrayList<Task>()
    private var tasksDAO = TasksDAO()

    fun attachView(activity: AppCompatActivity, impl: MainActivityImpl) {
        this.activity = activity
        this.impl = impl
        repository = TasksRepository(activity)
    }

    fun bindRecycler(recyclerTasks: RecyclerView) {
        list.addAll(tasksDAO.tasks)
        recyclerTasks.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerTasks.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recyclerTasks.adapter = TaskAdapter(activity, list, this)
    }

    fun bindRefresh(swipeRefreshTasks: SwipeRefreshLayout?) {
        swipeRefreshTasks!!.setOnRefreshListener {
            impl.showRefresh(false)
            requestAllTasks()
        }
    }

    fun requestAllTasks() {
        impl.showProgress()
        repository.requestAllTasks(this)
    }

    override fun onResponse(response: Any) {
        impl.hideProgress()
        list.clear()
        list.addAll(response as List<Task>)
        TasksDAO().saveAll(response)
        impl.refreshRecyclerView()
    }

    override fun onError(errorCode: Int, errorMessage: String?) {
        impl.hideProgress()
        ErrorManager().manageError(activity.findViewById(R.id.recycler_tasks) as View, errorCode, errorMessage,
                object : OnRetryClickCallback{
                    override fun onRetryClick() {
                        requestAllTasks()
                    }
                } )
    }

    override fun onClick(v: View, position: Int) {
        val i = Intent(activity, SelectedTaskActivity::class.java)
        i.putExtra(Constants.TASK,list[position])
        impl.startSelectedTaskActivity(i)
    }
}