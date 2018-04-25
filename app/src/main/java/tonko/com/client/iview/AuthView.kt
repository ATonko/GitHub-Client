package tonko.com.client.iview

interface AuthView : BaseView {
    fun isSuccess(login: String, avatar_url: String)
    fun isSuccess(accessToken: String)
    fun isError(code: String)
}
