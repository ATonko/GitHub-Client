package tonko.com.client.presenters

import io.reactivex.disposables.CompositeDisposable
import tonko.com.client.view.interfaces.BaseView

abstract class BasePresenter<V : BaseView>
{
    val disposables = CompositeDisposable()

    var view: V? = null

    open fun attachView(view: V)
    {
        this.view = view
    }

    open fun detachView()
    {
        this.view = null
        disposables.dispose()
    }

}
