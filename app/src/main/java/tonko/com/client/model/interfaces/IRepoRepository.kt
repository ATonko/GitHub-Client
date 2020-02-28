package tonko.com.client.model.interfaces

import io.reactivex.Single
import tonko.com.client.api.json_responses.Commits
import tonko.com.client.api.json_responses.Repos

interface IRepoRepository
{
    fun getCommits(user: String, project: String): Single<List<Commits>>
    fun getList(login: String): Single<ArrayList<Repos>>
}
