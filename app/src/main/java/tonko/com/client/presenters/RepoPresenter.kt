package tonko.com.client.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tonko.com.client.R
import tonko.com.client.iview.RepoView
import tonko.com.client.model.RepoRepository
import tonko.com.client.model.interfaces.IRepoRepository
import tonko.com.client.presenters.interfaces.IRepoPresenter

class RepoPresenter : BasePresenter<RepoView>(), IRepoPresenter
{
    private val repository: IRepoRepository = RepoRepository()
    private val disposable = CompositeDisposable()

    override fun getCommits(user: String,
                            project: String)
    {
        disposable.add(
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

    override fun detachView()
    {
        super.detachView()
        disposable.dispose()
    }
}