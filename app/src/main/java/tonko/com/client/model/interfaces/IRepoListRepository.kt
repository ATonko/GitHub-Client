package tonko.com.client.model.interfaces

import io.reactivex.Single
import tonko.com.client.model.Repos

interface IRepoListRepository
{
    fun getList(login: String): Single<ArrayList<Repos>>
}
