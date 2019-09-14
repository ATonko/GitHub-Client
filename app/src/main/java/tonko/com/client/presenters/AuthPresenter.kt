package tonko.com.client.presenters

import android.util.Base64
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tonko.com.client.AUTH_PROBLEM
import tonko.com.client.NET_PROBLEM
import tonko.com.client.api.ApiHolder
import tonko.com.client.iview.AuthView
import tonko.com.client.presenters.interfaces.IAuthPresenter

class AuthPresenter : BasePresenter<AuthView>(), IAuthPresenter
{

    private val publicApi = ApiHolder.publicApi
    private val privateApi = ApiHolder.privateApi
    private val disposable = CompositeDisposable()

    override fun loginOAuth(id: String,
                            secret: String,
                            code: String)
    {
        val response = publicApi.getAccessToken(
                id,
                secret,
                code)

        disposable.add(
                response
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            getListWithToken(it.accessToken)
                        }, {
                            view?.isError("$NET_PROBLEM, ${it.message.toString()}")
                        })
        )
    }

    private fun getListWithToken(token: String)
    {

        val response = privateApi.getBasicAuth(
                "Bearer $token")

        disposable.add(
                response
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            view?.isSuccess(it.login)
                        }, {
                            view?.isError("$NET_PROBLEM, ${it.message.toString()}")
                        })
        )
    }

    override fun basicLogin(
            login: String,
            password: String
    )
    {
        val response = privateApi.getBasicAuth(
                "Basic " +
                        Base64.encodeToString(
                                "$login:$password".toByteArray(),
                                Base64.NO_WRAP))

        disposable.add(
                response
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            view?.isSuccess(it.login, it.avatar_uri)
                        }, {
                            view?.isError("$AUTH_PROBLEM, ${it.message.toString()}")
                        })
        )
    }

    override fun detachView()
    {
        super.detachView()
        disposable.dispose()
    }
}