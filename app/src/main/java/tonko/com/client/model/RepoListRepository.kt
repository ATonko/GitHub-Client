package tonko.com.client.model

import tonko.com.client.api.ApiHolder
import tonko.com.client.model.interfaces.IRepoListRepository
import tonko.com.client.presenters.interfaces.IRepoListPresenter

class RepoListRepository : IRepoListRepository
{
    private val privateApi = ApiHolder.privateApi

    fun getList(login: String)
    {
        privateApi.getRepoList(login)
    }
}