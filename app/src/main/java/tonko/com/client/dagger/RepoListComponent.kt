package tonko.com.client.dagger

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import tonko.com.client.presenters.RepoListPresenter
import tonko.com.client.presenters.interfaces.IRepoListPresenter
import tonko.com.client.view.RepoListActivity

@Module
abstract class RepoListModule {

    @Binds
    abstract fun bindsPresenter(impl: RepoListPresenter): IRepoListPresenter
}

@Subcomponent(modules = [RepoListModule::class])
interface RepoListComponent {
    fun inject(activity: RepoListActivity)
}