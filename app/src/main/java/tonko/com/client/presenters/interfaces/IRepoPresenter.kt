package tonko.com.client.presenters.interfaces

import tonko.com.client.api.json_responses.Commits
import tonko.com.client.view.interfaces.BaseListView

interface IRepoPresenter : IPresenter<BaseListView<Commits>> {

    fun getCommits(user: String,
                   project: String)
}
