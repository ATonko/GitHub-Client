package tonko.com.client.model

import tonko.com.client.api.ApiHolder
import tonko.com.client.model.interfaces.IRepoListRepository

class RepoListRepository : IRepoListRepository
{
    private val privateApi = ApiHolder.privateApi

    override fun getList(login: String) = privateApi.getRepoList(login)

}