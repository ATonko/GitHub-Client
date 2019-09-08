package tonko.com.client.presenters

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tonko.com.client.api.ApiHolder
import tonko.com.client.iview.RepoView
import tonko.com.client.model.Commits
import tonko.com.client.presenters.interfaces.IRepoPresenter

class RepoPresenter : BasePresenter<RepoView>(), IRepoPresenter
{

    private val privateApi = ApiHolder.privateApi

    override fun getCommits(user: String,
                            project: String)
    {
        privateApi.getCommits(user, project).enqueue(object : Callback<List<Commits>>
        {
            override fun onFailure(call: Call<List<Commits>>, t: Throwable)
            {
                view?.isError(t.message.toString())
            }

            override fun onResponse(call: Call<List<Commits>>, response: Response<List<Commits>>)
            {
                view?.
            }

        })
    }
}