package tonko.com.client.iview

import tonko.com.client.model.Repos

interface RepoListView : BaseView {
    fun isSuccess(repos: ArrayList<Repos>)
    fun isError()
}
