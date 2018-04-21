package br.com.franco.lucas.teste.testerunrunit.callbacks

/**
 * Created by MacMini on 18/03/2018.
 */
interface OnResponseCallback {

    fun onResponse(response: Any)
    fun onError(errorCode: Int, errorMessage: String?)

}