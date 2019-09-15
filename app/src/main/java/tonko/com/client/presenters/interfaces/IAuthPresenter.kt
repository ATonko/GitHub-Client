package tonko.com.client.presenters.interfaces

interface IAuthPresenter
{
    fun basicLogin(
            login: String,
            password: String)

    fun loginOAuth(id: String, secret: String, code: String)
}
