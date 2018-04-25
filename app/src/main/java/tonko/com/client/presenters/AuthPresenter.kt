package tonko.com.client.presenters

import android.util.Base64
import android.util.Log
import tonko.com.client.model.AccessToken
import tonko.com.client.api.ApiHolder
import tonko.com.client.api.CustomEnqueue
import tonko.com.client.model.Owner
import tonko.com.client.iview.AuthView

class AuthPresenter : BasePresenter<AuthView>() {

    private val publicApi = ApiHolder.publicApi
    private val privateApi = ApiHolder.privateApi
    private val customForOwner = CustomEnqueue<Owner>()
    private val customForAccessToken = CustomEnqueue<AccessToken>()

    fun loginOAuth(clientId: String,
                   clientSecret: String,
                   code: String) {
        Log.i("MyTag", "AuthPresenter login")
        val response = publicApi.getAccessToken(
                clientId,
                clientSecret,
                code)
        customForAccessToken.customEnqueue(response, {
            view?.isError()
        }, {
            view?.isSuccess(it.body()!!.accessToken)
        })
    }

    fun basicLogin(
            login: String,
            password: String
    ) {
        Log.i("MyTag", "basicLogin")
        val response = privateApi.getBasicAuth(
                "Basic " +
                        Base64.encodeToString(
                                "$login:$password".toByteArray(),
                                Base64.NO_WRAP))
        customForOwner.customEnqueue(response, {
            Log.i("MyTag", "error")
            view?.isError()
        }, {
            if (it.isSuccessful) {
                Log.i("MyTag", "it.body.login = ${it.body()!!.login}")
                view?.isSuccess(
                        it.body()!!.login,
                        it.body()!!.avatar_uri)

            }
        })
    }
}