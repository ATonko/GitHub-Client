package tonko.com.client.model.repository

import tonko.com.client.api.ApiHolder
import tonko.com.client.model.interfaces.IRepoRepository

class RepoRepository : IRepoRepository
{
    private val privateApi = ApiHolder.privateApi

    override fun getCommits(user: String, project: String) = privateApi.getCommits(user, project)

}
