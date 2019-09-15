package tonko.com.client.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tonko.com.client.R
import tonko.com.client.model.Repos
import tonko.com.client.model.interfaces.IRepoListRepository
import tonko.com.client.model.repository.RepoListRepository
import tonko.com.client.presenters.interfaces.IRepoListPresenter
import tonko.com.client.view.interfaces.BaseListView

class RepoListPresenter : BasePresenter<BaseListView<Repos>>(), IRepoListPresenter
{
    private val repository: IRepoListRepository = RepoListRepository()

    override fun getList(login: String)
    {
        disposables.add(repository.getList(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isEmpty()) view?.isEmptyList()
                    else view?.isSuccess(it)

                }, {
                    view?.isError(R.string.no_repos_here)
                })
        )
    }
}