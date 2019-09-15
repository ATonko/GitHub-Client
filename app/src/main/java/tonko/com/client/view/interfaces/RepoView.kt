package tonko.com.client.view.interfaces

import tonko.com.client.model.Commits

interface RepoView : BaseView
{
    fun isSuccess(list:List<Commits>)
    fun isError(error: Int)
    fun isEmptyList()
}
