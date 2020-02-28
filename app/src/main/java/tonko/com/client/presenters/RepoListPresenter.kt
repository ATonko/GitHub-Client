package tonko.com.client.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tonko.com.client.R
import tonko.com.client.api.json_responses.Repos
import tonko.com.client.model.interfaces.IRepoListRepository
import tonko.com.client.presenters.interfaces.IRepoListPresenter
import tonko.com.client.view.interfaces.BaseListView

class RepoListPresenter(private val repository: IRepoListRepository) : BasePresenter<BaseListView<Repos>>(), IRepoListPresenter
{
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