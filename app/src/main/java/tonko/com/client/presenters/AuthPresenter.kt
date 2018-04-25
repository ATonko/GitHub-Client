package tonko.com.client.presenters

import android.util.Base64
import android.util.Log
import tonko.com.client.AUTH_PROBLEM
import tonko.com.client.NET_PROBLEM
import tonko.com.client.api.ApiHolder
import tonko.com.client.api.CustomEnqueue
import tonko.com.client.iview.AuthView
import tonko.com.client.model.AccessToken
import tonko.com.client.model.Owner

class AuthPresenter : BasePresenter<AuthView>() {

    private val publicApi = ApiHolder.publicApi
    private val privateApi = ApiHolder.privateApi
    private val customForOwner = CustomEnqueue<Owner>()
    private val customForAccessToken = CustomEnqueue<AccessToken>()

    fun loginOAuth(clientId: String,
                   clientSecret: String,
                   code: String) {
        val response = publicApi.getAccessToken(
                clientId,
                clientSecret,
                code)
        customForAccessToken.customEnqueue(response, {
            view?.isError(NET_PROBLEM)
        }, {
            getListWithToken(it.body()!!.accessToken)
        })
    }

    fun getListWithToken(token: String) {
        Log.i("MyTag", "presenter token is $token")
        val response = privateApi.getBasicAuth(
                "Bearer $token")
        customForOwner.customEnqueue(response,
                {
                    view?.isError(AUTH_PROBLEM)
                }, {
            if (it.isSuccessful) {
                view?.isSuccess(it.body()!!.login)
            } else {
                view?.isError(NET_PROBLEM)
            }
        })
    }

    fun basicLogin(
            login: String,
            password: String
    ) {
        val response = privateApi.getBasicAuth(
                "Basic " +
                        Base64.encodeToString(
                                "$login:$password".toByteArray(),
                                Base64.NO_WRAP))

        customForOwner.customEnqueue(response, {
            view?.isError(NET_PROBLEM)
        }, {
            if (it.isSuccessful) {
                view?.isSuccess(
                        it.body()!!.login,
                        it.body()!!.avatar_uri)

            } else {
                view?.isError(AUTH_PROBLEM)
            }
        })
    }
}