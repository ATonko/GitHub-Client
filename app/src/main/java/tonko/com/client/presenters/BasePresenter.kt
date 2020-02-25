package tonko.com.client.presenters

import io.reactivex.disposables.CompositeDisposable
import tonko.com.client.presenters.interfaces.IPresenter
import tonko.com.client.view.interfaces.BaseView

abstract class BasePresenter<V : BaseView> : IPresenter<V> {
    val disposables = CompositeDisposable()

    var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposables.dispose()
    }

}
