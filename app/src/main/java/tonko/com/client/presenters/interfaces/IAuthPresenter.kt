package tonko.com.client.presenters.interfaces

import tonko.com.client.view.interfaces.AuthView

interface IAuthPresenter : IPresenter<AuthView>
{
    fun basicLogin(
            login: String,
            password: String)

    fun loginOAuth(id: String, secret: String, code: String)
}
