package tonko.com.client.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tonko.com.client.R
import tonko.com.client.model.Commits
import tonko.com.client.model.interfaces.IRepoRepository
import tonko.com.client.model.repository.RepoRepository
import tonko.com.client.presenters.interfaces.IRepoPresenter
import tonko.com.client.view.interfaces.BaseListView

class RepoPresenter : BasePresenter<BaseListView<Commits>>(), IRepoPresenter
{
    private val repository: IRepoRepository = RepoRepository()

    override fun getCommits(user: String,
                            project: String)
    {
        disposables.add(
                repository.getCommits(user, project)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it.isEmpty()) view?.isEmptyList()
                            else view?.isSuccess(it)
                        }, {
                            view?.isError(R.string.network_error)
                        })
        )
    }
}