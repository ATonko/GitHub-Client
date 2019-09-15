package tonko.com.client.presenters

import android.util.Base64
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tonko.com.client.R
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
                            view?.isError(R.string.network_error)
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
                            view?.isError(R.string.network_error)
                        })
        )
    }

    override fun basicLogin(
            login: String,
            password: String
    )
    {
        if (isLoginValid(login) && isPasswordValid(password))
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
                                view?.isError(R.string.auth_error)
                            })
            )

        } else
        {
            view?.isError(R.string.error_not_valid_mail_or_password)
        }
    }

    private fun isPasswordValid(password: String) = password.length > 2

    private fun isLoginValid(login: String) = login.contains("@")

    override fun detachView()
    {
        super.detachView()
        disposable.dispose()
    }
}