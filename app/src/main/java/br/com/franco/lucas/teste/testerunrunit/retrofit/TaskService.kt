package br.com.franco.lucas.teste.testerunrunit.retrofit

import br.com.franco.lucas.teste.testerunrunit.model.Task
import br.com.franco.lucas.teste.testerunrunit.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by MacMini on 16/03/2018.
 */
interface TaskService{

    @GET(Constants.TASKS_URL)
    fun getAllTasks() : Call<ArrayList<Task>>

    @GET(Constants.TASKS_URL + "{id}")
    fun getOneTaskById(@Path("id")id : Long) : Call<Task>

    @POST(Constants.TASKS_URL + "{id}" + Constants.PLAY)
    fun playTask(@Path("id")id : Int) : Call<Task>

    @POST(Constants.TASKS_URL + "{id}" + Constants.PAUSE)
    fun pauseTask(@Path("id")id : Int) : Call<Task>

    @POST(Constants.TASKS_URL + "{id}" + Constants.DELIVER)
    fun deliverTask(@Path("id")id : Int) : Call<Task>

}