package br.com.franco.lucas.teste.testerunrunit.adapter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.franco.lucas.teste.testerunrunit.R
import br.com.franco.lucas.teste.testerunrunit.callbacks.OnAdapterClickCallback
import br.com.franco.lucas.teste.testerunrunit.model.Task
import kotlinx.android.synthetic.main.item_view_task.view.*
import kotlinx.android.synthetic.main.task_priority.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by MacMini on 18/03/2018.
 */
class TaskAdapter(private val activity: AppCompatActivity,
                  private val list: List<Task>,
                  private val callback: OnAdapterClickCallback) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_view_task, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val actualTask = list[holder!!.adapterPosition]
        holder.taskTitle.text = actualTask.title
        holder.txtPriority.text = actualTask.priority
        holder.taskProjectClient.text = actualTask.client_name + " > " + actualTask.project_name
        holder.txtEstimatedDate.text = convertDate(actualTask.close_date)
        holder.progressBar.progress = actualTask.time_progress!!.toInt()
        holder.container.setOnClickListener({callback.onClick(holder.container,holder.adapterPosition)})
        checkBgColor(holder)

    }

    private fun checkBgColor(holder: ViewHolder) {
        if (holder.adapterPosition == 0) {
            holder.bgPriority.setImageResource(R.color.colorPrimary)
        } else {
            holder.bgPriority.setImageResource(R.color.material_grey_600)
        }
    }

    fun convertDate(date: Date?): String {
        return if(date != null) {
            val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            df.format(date)
        }else{ "" }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView
        val taskTitle = itemView.task_title
        val bgPriority = itemView.bg_priority
        val txtPriority = itemView.txt_priority
        val txtEstimatedDate = itemView.txt_estimated_date
        val taskProjectClient = itemView.task_project_client
        val progressBar = itemView.task_progress
    }
}