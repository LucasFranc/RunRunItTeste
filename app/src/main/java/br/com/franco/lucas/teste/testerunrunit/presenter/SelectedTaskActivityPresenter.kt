package br.com.franco.lucas.teste.testerunrunit.presenter

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import br.com.franco.lucas.teste.testerunrunit.R
import br.com.franco.lucas.teste.testerunrunit.callbacks.OnResponseCallback
import br.com.franco.lucas.teste.testerunrunit.callbacks.OnRetryClickCallback
import br.com.franco.lucas.teste.testerunrunit.implementation.SelectedTaskActivityImpl
import br.com.franco.lucas.teste.testerunrunit.model.Task
import br.com.franco.lucas.teste.testerunrunit.repository.TasksRepository
import br.com.franco.lucas.teste.testerunrunit.utils.ErrorManager
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by MacMini on 21/03/2018.
 */
class SelectedTaskActivityPresenter: OnResponseCallback{

    lateinit var activity: AppCompatActivity
    lateinit var impl: SelectedTaskActivityImpl
    private lateinit var selectedTask: Task
    private lateinit var repository : TasksRepository

    fun attachView(activity: AppCompatActivity, impl: SelectedTaskActivityImpl, selectedTask: Task) {
        this.activity = activity
        this.impl = impl
        this.selectedTask = selectedTask
        repository = TasksRepository(activity)
    }

    fun bindToolbar() {
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun requestTask() {
        impl.showProgress()
        repository.getOneTaskById(selectedTask.id,this)
    }

    override fun onResponse(response: Any) {
        impl.hideProgress()
        impl.showRefresh(false)
        selectedTask = response as Task
        impl.refreshTask(selectedTask)
    }

    override fun onError(errorCode: Int, errorMessage: String?) {
        impl.hideProgress()
        impl.showRefresh(false)
        ErrorManager().manageError(activity.findViewById(R.id.task_title) as View,errorCode,errorMessage,
                object : OnRetryClickCallback{
                    override fun onRetryClick() {
                        requestTask()
                    }
                })
    }

    fun bindRefresh(refresh_selected_task: SwipeRefreshLayout?) {
        refresh_selected_task!!.setOnRefreshListener {
            impl.showRefresh(false)
            requestTask()
        }
    }

    fun checkLabelVisibility(txt: String?, label: TextView, textView: TextView) {
        if(txt != null && txt.trim().isNotEmpty()){
            textView.text = txt
            label.visibility = View.VISIBLE
            textView.visibility = View.VISIBLE
        }else{
            label.visibility = View.GONE
            textView.visibility = View.GONE
        }
    }

    fun convertTime(timeSeconds: Int?): String? {
        return if(timeSeconds != null) {
            var longSeconds = timeSeconds.toLong()
            val hour = TimeUnit.SECONDS.toHours(longSeconds)
            longSeconds -= TimeUnit.HOURS.toSeconds(hour)
            val minutes = TimeUnit.SECONDS.toMinutes(longSeconds)
            val hourString = hour.toString() + ":" + minutes.toString()
            checkZeros(hourString)
        }else { "" }
    }

    fun checkZeros(hourString: String): String? {
        val split = hourString.split(":")
        var hours = split[0]
        var minutes = split[1]

        if(hours.length == 1){
            hours = "0$hours"
        }

        if(minutes.length == 1){
            minutes = "0$minutes"
        }
        return "$hours:$minutes"
    }

    fun convertDate(date: Date?): String {
        return if(date != null) {
            val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            df.format(date)
        }else{ "" }
    }

    fun checkBtnStartWorkText(is_working_on: Boolean?): String? {
        return if(is_working_on == null || is_working_on == false){ activity.getString(R.string.start_work) }
        else{ activity.getString(R.string.working) }
    }

    fun bindBtnStartWork(btn_start_work: Button?) {

        btn_start_work!!.setOnClickListener({
            if(selectedTask.is_working_on == null || selectedTask.is_working_on == false){
                impl.showRefresh(true)
                repository.playTask(selectedTask.id,this)
            }else{
                impl.showRefresh(true)
                repository.pauseTask(selectedTask.id,this)
            }
        })
    }

    fun bindBtnDeliver(btn_deliver: Button?) {
        btn_deliver!!.setOnClickListener({
            impl.showRefresh(true)
            repository.deliverTask(selectedTask.id,object : OnResponseCallback{
                override fun onResponse(response: Any) {
                    Toast.makeText(activity,activity.getString(R.string.task_delivered_with_success),Toast.LENGTH_LONG).show()
                    activity.finish()
                }

                override fun onError(errorCode: Int, errorMessage: String?) {
                    impl.hideProgress()
                    ErrorManager().manageError(activity.findViewById(R.id.task_title) as View,errorCode,errorMessage,
                            object : OnRetryClickCallback{
                                override fun onRetryClick() {
                                    requestTask()
                                }
                            })
                }
            })
        })
    }
}