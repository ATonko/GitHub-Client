package tonko.com.client.dagger

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import tonko.com.client.model.interfaces.IRepoRepository
import tonko.com.client.presenters.RepoListPresenter
import tonko.com.client.presenters.interfaces.IRepoListPresenter
import tonko.com.client.view.RepoListActivity

@Module
class RepoListModule {

    @Provides
    fun providePresenter(repository: IRepoRepository): IRepoListPresenter = RepoListPresenter(repository)
}

@Subcomponent(modules = [RepoListModule::class])
interface RepoListComponent {
    fun inject(activity: RepoListActivity)
}