package tonko.com.client.presenters

import android.util.Log
import tonko.com.client.api.ApiHolder
import tonko.com.client.api.CustomEnqueue
import tonko.com.client.iview.RepoListView
import tonko.com.client.model.Repos
import tonko.com.client.presenters.interfaces.IRepoListPresenter

class RepoListPresenter : BasePresenter<RepoListView>(), IRepoListPresenter
{

    private val privateApi = ApiHolder.privateApi
    private val custom = CustomEnqueue<ArrayList<Repos>>()

    override fun getList(login: String)
    {

        val response = privateApi.getRepoList(login)
        custom.customEnqueue(response,
                {
                    view?.isError()
                }, {
            if (it.isSuccessful)
            {
                view?.isSuccess(it.body()!!)
            } else
            {
                view?.isError()
            }
        })
    }
}