package tonko.com.client.dagger

import dagger.Component
import dagger.Module
import dagger.Provides
import tonko.com.client.model.RepoListRepository
import tonko.com.client.model.interfaces.IRepoListRepository
import tonko.com.client.presenters.RepoListPresenter
import tonko.com.client.presenters.interfaces.IRepoListPresenter
import tonko.com.client.view.RepoListActivity

@Module
class RepoListModule {

    @Provides
    fun provideRepository(): IRepoListRepository = RepoListRepository()


    @Provides
    fun providePresenter(repository: IRepoListRepository): IRepoListPresenter = RepoListPresenter(repository)
}

@Component(modules = [RepoListModule::class])
interface RepoListComponent {
    fun inject(activity: RepoListActivity)
}