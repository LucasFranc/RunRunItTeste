package br.com.franco.lucas.teste.testerunrunit.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.franco.lucas.teste.testerunrunit.R
import br.com.franco.lucas.teste.testerunrunit.implementation.MainActivityImpl
import br.com.franco.lucas.teste.testerunrunit.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by MacMini on 14/03/2018.
 */
class MainActivity : AppCompatActivity(), MainActivityImpl {

    private val presenter = MainActivityPresenter()
    private lateinit var loadingDialog : Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingDialog = DialogLoading(this,getString(R.string.loading_your_data)).bind()
        presenter.attachView(this,this)
        presenter.bindRefresh(swipe_refresh_tasks)
        presenter.bindRecycler(recycler_tasks)
    }

    override fun onResume() {
        super.onResume()
        presenter.requestAllTasks()
    }

    override fun refreshRecyclerView() {
        recycler_tasks.adapter.notifyDataSetChanged()
    }

    override fun showRefresh(show: Boolean) {
        swipe_refresh_tasks.isRefreshing = show
    }

    override fun showProgress() {
        loadingDialog.show()
    }

    override fun hideProgress() {
        loadingDialog.dismiss()
    }

    override fun startSelectedTaskActivity(i: Intent) {
        startActivity(i)
    }

}
