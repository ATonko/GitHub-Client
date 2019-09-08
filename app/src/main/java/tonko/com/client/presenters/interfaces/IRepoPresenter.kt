package tonko.com.client.presenters.interfaces

import retrofit2.Call
import tonko.com.client.model.Commits

interface IRepoPresenter
{

    fun getCommits(user: String,
                   project: String)
}
