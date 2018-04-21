package br.com.franco.lucas.teste.testerunrunit.repository

import android.content.Context
import android.net.ConnectivityManager
import br.com.franco.lucas.teste.testerunrunit.R
import br.com.franco.lucas.teste.testerunrunit.callbacks.OnResponseCallback
import br.com.franco.lucas.teste.testerunrunit.model.Task
import br.com.franco.lucas.teste.testerunrunit.retrofit.TaskService
import br.com.franco.lucas.teste.testerunrunit.utils.AuthRetrofit
import br.com.franco.lucas.teste.testerunrunit.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by MacMini on 16/03/2018.
 */
class TasksRepository(private val context: Context) {

    fun requestAllTasks(callback: OnResponseCallback) {
        if (!hasInternetConnection(context)) {
            return callback.onError(Constants.ERROR_NO_INTERNET_CONN, context.getString(R.string.seems_without_internet))
        }

        AuthRetrofit().request().create(TaskService::class.java).getAllTasks()
                .enqueue(object : Callback<ArrayList<Task>> {
                    override fun onResponse(call: Call<ArrayList<Task>>?, response: Response<ArrayList<Task>>?) {
                        if (response!!.isSuccessful)
                            callback.onResponse(response.body()!!)
                        else
                            callback.onError(response.code(), response.message()) // aqui o servidor
                        // poderia retornar mensagens de erro mais direcionadas ao erro em si.
                    }

                    override fun onFailure(call: Call<ArrayList<Task>>?, t: Throwable?) {
                        callback.onError(500, null) // Aqui poderia vir algum
                        // código padrão para falta de internet, ou outros erros mais especificos, ao invés de somente 500.
                    }
                })
    }

    fun getOneTaskById(id: Long, callback: OnResponseCallback) {
        if (!hasInternetConnection(context)) {
            return callback.onError(Constants.ERROR_NO_INTERNET_CONN, context.getString(R.string.seems_without_internet))
        }
        AuthRetrofit().request().create(TaskService::class.java).getOneTaskById(id)
                .enqueue(object : Callback<Task> {
                    override fun onResponse(call: Call<Task>?, response: Response<Task>?) {
                        if (response!!.isSuccessful)
                            callback.onResponse(response.body()!!)
                        else
                            callback.onError(response.code(), response.message())
                    }

                    override fun onFailure(call: Call<Task>?, t: Throwable?) {
                        callback.onError(500, null)
                    }
                })
    }

    fun playTask(id: Long, callback: OnResponseCallback) {
        if (!hasInternetConnection(context)) {
            return callback.onError(Constants.ERROR_NO_INTERNET_CONN, context.getString(R.string.seems_without_internet))
        }
        AuthRetrofit().request().create(TaskService::class.java).playTask(id.toInt())
                .enqueue(object : Callback<Task> {
                    override fun onResponse(call: Call<Task>?, response: Response<Task>?) {
                        if (response!!.isSuccessful)
                            callback.onResponse(response.body()!!)
                        else
                            callback.onError(response.code(), response.message())
                    }

                    override fun onFailure(call: Call<Task>?, t: Throwable?) {
                        callback.onError(500, null)
                    }
                })
    }

    fun pauseTask(id: Long, callback: OnResponseCallback) {
        if (!hasInternetConnection(context)) {
            return callback.onError(Constants.ERROR_NO_INTERNET_CONN, context.getString(R.string.seems_without_internet))
        }
        AuthRetrofit().request().create(TaskService::class.java).pauseTask(id.toInt())
                .enqueue(object : Callback<Task> {
                    override fun onResponse(call: Call<Task>?, response: Response<Task>?) {
                        if (response!!.isSuccessful)
                            callback.onResponse(response.body()!!)
                        else
                            callback.onError(response.code(), response.message())
                    }

                    override fun onFailure(call: Call<Task>?, t: Throwable?) {
                        callback.onError(500, null)
                    }
                })
    }

    fun deliverTask(id: Long, callback: OnResponseCallback) {
        if (!hasInternetConnection(context)) {
            return callback.onError(Constants.ERROR_NO_INTERNET_CONN, context.getString(R.string.seems_without_internet))
        }
        AuthRetrofit().request().create(TaskService::class.java).deliverTask(id.toInt())
                .enqueue(object : Callback<Task> {
                    override fun onResponse(call: Call<Task>?, response: Response<Task>?) {
                        if (response!!.isSuccessful)
                            callback.onResponse(response.body()!!)
                        else
                            callback.onError(response.code(), response.message())
                    }

                    override fun onFailure(call: Call<Task>?, t: Throwable?) {
                        callback.onError(500, null)
                    }
                })
    }

    private fun hasInternetConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }


}