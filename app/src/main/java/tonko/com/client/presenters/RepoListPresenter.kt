package tonko.com.client.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tonko.com.client.R
import tonko.com.client.iview.RepoListView
import tonko.com.client.model.RepoListRepository
import tonko.com.client.model.interfaces.IRepoListRepository
import tonko.com.client.presenters.interfaces.IRepoListPresenter

class RepoListPresenter : BasePresenter<RepoListView>(), IRepoListPresenter
{
    private val repository: IRepoListRepository = RepoListRepository()
    private val disposables = CompositeDisposable()

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

    override fun detachView()
    {
        super.detachView()
        disposables.dispose()
    }

}