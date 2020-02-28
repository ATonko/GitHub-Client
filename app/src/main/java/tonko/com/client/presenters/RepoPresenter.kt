package tonko.com.client.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tonko.com.client.R
import tonko.com.client.api.json_responses.Commits
import tonko.com.client.model.interfaces.IRepoRepository
import tonko.com.client.presenters.interfaces.IRepoPresenter
import tonko.com.client.view.interfaces.BaseListView
import javax.inject.Inject

class RepoPresenter @Inject constructor(private val repository: IRepoRepository) : BasePresenter<BaseListView<Commits>>(), IRepoPresenter
{
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