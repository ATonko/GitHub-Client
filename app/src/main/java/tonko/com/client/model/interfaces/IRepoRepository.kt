package tonko.com.client.model.interfaces

import io.reactivex.Single
import tonko.com.client.model.Commits

interface IRepoRepository
{
    fun getCommits(user: String, project: String): Single<List<Commits>>
}
