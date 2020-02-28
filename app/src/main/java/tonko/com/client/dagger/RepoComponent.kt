package tonko.com.client.dagger

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import tonko.com.client.model.interfaces.IRepoRepository
import tonko.com.client.presenters.RepoPresenter
import tonko.com.client.presenters.interfaces.IRepoPresenter
import tonko.com.client.view.RepoActivity

@Module
class RepoModule {
    @Provides
    fun providePresenter(repository: IRepoRepository): IRepoPresenter = RepoPresenter(repository)
}

@Subcomponent(modules = [RepoModule::class])
interface RepoComponent {
    fun inject(activity: RepoActivity)
}