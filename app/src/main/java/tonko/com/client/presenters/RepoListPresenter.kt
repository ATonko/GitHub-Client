package tonko.com.client.presenters

import android.util.Log
import tonko.com.client.api.ApiHolder
import tonko.com.client.api.CustomEnqueue
import tonko.com.client.iview.RepoListView
import tonko.com.client.model.Repos

class RepoListPresenter : BasePresenter<RepoListView>() {

    private val privateApi = ApiHolder.privateApi
    private val custom = CustomEnqueue<ArrayList<Repos>>()

    fun getList(token: String) {
        Log.i("MyTag", "presenter token is $token")
        val response = privateApi.getRepoList(token)
        custom.customEnqueue(response,
                {
                    view?.isError()
                }, {
            if (it.isSuccessful) {
                view?.isSuccess(it.body()!!)
            }
        })
    }
}