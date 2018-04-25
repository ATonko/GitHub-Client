package tonko.com.client.presenters

import retrofit2.Call
import tonko.com.client.api.ApiHolder
import tonko.com.client.model.Commits

class RepoPresenter {

    private val privateApi = ApiHolder.privateApi

    fun getCommits(user: String,
                   project: String): Call<List<Commits>> {
        return privateApi.getCommits(user, project)
    }
}