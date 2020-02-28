package tonko.com.client.presenters.interfaces

import tonko.com.client.api.json_responses.Repos
import tonko.com.client.view.interfaces.BaseListView

interface IRepoListPresenter : IPresenter<BaseListView<Repos>>
{
    fun getList(login:String)
}
