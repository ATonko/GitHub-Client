package tonko.com.client.presenters

import android.util.Base64
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tonko.com.client.R
import tonko.com.client.model.AuthRepository
import tonko.com.client.presenters.interfaces.IAuthPresenter
import tonko.com.client.view.interfaces.AuthView
import javax.inject.Inject

class AuthPresenter @Inject constructor() : BasePresenter<AuthView>(), IAuthPresenter {
    private val repository = AuthRepository()

    override fun loginOAuth(id: String,
                            secret: String,
                            code: String) {
        disposables.add(
                repository.getAccessToken(id, secret, code)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            getListWithToken(it.accessToken)
                        }, {
                            view?.isError(R.string.network_error)
                        })
        )
    }

    private fun getListWithToken(token: String) {
        disposables.add(
                repository.getBasicAuth("Bearer $token")
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
    ) {
        if (isLoginValid(login) && isPasswordValid(password)) {
            disposables.add(
                    repository.getBasicAuth(
                            "Basic " +
                                    Base64.encodeToString(
                                            "$login:$password".toByteArray(),
                                            Base64.NO_WRAP))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                view?.isSuccess(it.login, it.avatar_url)
                            }, {
                                view?.isError(R.string.auth_error)
                            })
            )

        } else {
            view?.isError(R.string.error_not_valid_mail_or_password)
        }
    }

    private fun isPasswordValid(password: String) = password.length > 2

    private fun isLoginValid(login: String) = login.contains("@")
}