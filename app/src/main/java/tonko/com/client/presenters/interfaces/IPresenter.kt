package tonko.com.client.presenters.interfaces

interface IPresenter<T> {
    fun attachView(view: T)
    fun detachView()
}
