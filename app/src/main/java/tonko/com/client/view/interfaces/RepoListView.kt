package tonko.com.client.view.interfaces

import tonko.com.client.model.Repos

interface RepoListView : BaseView
{
    fun isSuccess(repos: ArrayList<Repos>)
    fun isError(error: Int)
    fun isEmptyList()
}
