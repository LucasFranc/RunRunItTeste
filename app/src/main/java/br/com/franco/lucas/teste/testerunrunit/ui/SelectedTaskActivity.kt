package br.com.franco.lucas.teste.testerunrunit.ui

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.franco.lucas.teste.testerunrunit.R
import br.com.franco.lucas.teste.testerunrunit.implementation.SelectedTaskActivityImpl
import br.com.franco.lucas.teste.testerunrunit.model.Task
import br.com.franco.lucas.teste.testerunrunit.presenter.SelectedTaskActivityPresenter
import br.com.franco.lucas.teste.testerunrunit.utils.Constants
import kotlinx.android.synthetic.main.activity_selected_task.*

/**
 * Created by MacMini on 21/03/2018.
 */
class SelectedTaskActivity : AppCompatActivity(), SelectedTaskActivityImpl {

    private val presenter = SelectedTaskActivityPresenter()
    private lateinit var loadingDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_task)
        val selectedTask : Task = intent.extras.getParcelable(Constants.TASK)
        loadingDialog = DialogLoading(this,getString(R.string.loading_your_task)).bind()
        presenter.attachView(this,this,selectedTask)
        presenter.bindToolbar()
        presenter.bindRefresh(refresh_selected_task)
        presenter.requestTask()
        presenter.bindBtnStartWork(btn_start_work)
        presenter.bindBtnDeliver(btn_deliver)
        refreshTask(selectedTask)
    }

    override fun showProgress() {
        loadingDialog.show()
    }

    override fun hideProgress() {
        loadingDialog.dismiss()
    }


    override fun showRefresh(show: Boolean) {
        refresh_selected_task.isRefreshing = show
    }

    override fun refreshTask(selectedTask: Task) {
        title = selectedTask.title
        task_title.text = selectedTask.title
        presenter.checkLabelVisibility(presenter.convertTime(selectedTask.time_worked),label_hours_worked,hours_horked)
        presenter.checkLabelVisibility(presenter.convertTime(selectedTask.time_pending),label_hours_remaining,hours_remaining)
        presenter.checkLabelVisibility(presenter.convertDate(selectedTask.start_date),label_init_date,init_date)
        presenter.checkLabelVisibility(presenter.convertDate(selectedTask.close_date),label_delivery_date,delivery_date)
        presenter.checkLabelVisibility(selectedTask.client_name + " > " + selectedTask.project_name,label_project,project)
        presenter.checkLabelVisibility(selectedTask.task_status_name,label_status,status)
        presenter.checkLabelVisibility(selectedTask.type_name,label_task_type,task_type)
        btn_start_work.text = presenter.checkBtnStartWorkText(selectedTask.is_working_on)
        label_created_at.text = getString(R.string.created_at) + " " + presenter.convertDate(selectedTask.created_at) + " " + getString(R.string.by)
        created_by_avatar.titleText = selectedTask.user_name[0].toString()
        created_by_txt.text = selectedTask.user_name
        responsible_txt.text = selectedTask.responsible_name
        responsible_avatar.titleText = selectedTask.responsible_name[0].toString()
    }

}