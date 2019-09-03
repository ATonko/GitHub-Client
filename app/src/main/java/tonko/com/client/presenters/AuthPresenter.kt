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
import tonko.com.client.presenters.interfaces.IAuthPresenter

class AuthPresenter : BasePresenter<AuthView>(),IAuthPresenter{

    private val publicApi = ApiHolder.publicApi
    private val privateApi = ApiHolder.privateApi
    private val customForOwner = CustomEnqueue<Owner>()
    private val customForAccessToken = CustomEnqueue<AccessToken>()

    override fun loginOAuth(clientId: String,
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

    private fun getListWithToken(token: String) {

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

    override fun basicLogin(
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