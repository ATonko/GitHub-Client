package tonko.com.client.presenters.interfaces

import tonko.com.client.iview.BaseView

interface IAuthPresenter
{
    fun basicLogin(
            login: String,
            password: String)

    fun loginOAuth(id: String, secret: String, code: String)
}
