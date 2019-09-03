package tonko.com.client.presenters

import tonko.com.client.iview.BaseView

abstract class BasePresenter<V : BaseView>
{
    var view: V? = null

    open fun attachView(view: V)
    {
        this.view = view
    }

    open fun detachView()
    {
        this.view=null
    }

}
