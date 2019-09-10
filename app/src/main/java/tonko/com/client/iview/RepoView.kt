package tonko.com.client.iview

import tonko.com.client.model.Commits

interface RepoView:BaseView
{
    fun isSuccess(list:List<Commits>)
    fun isError(error: String)
    fun isEmptyList()
}
