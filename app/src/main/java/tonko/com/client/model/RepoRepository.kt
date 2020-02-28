package tonko.com.client.model

import tonko.com.client.api.ApiMethods
import tonko.com.client.model.interfaces.IRepoRepository

class RepoRepository(private val privateApi: ApiMethods) : IRepoRepository
{
    override fun getCommits(user: String, project: String) = privateApi.getCommits(user, project)
    override fun getList(login: String) = privateApi.getRepoList(login)

}
