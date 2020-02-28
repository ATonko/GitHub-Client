package tonko.com.client.dagger

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import tonko.com.client.presenters.RepoPresenter
import tonko.com.client.presenters.interfaces.IRepoPresenter
import tonko.com.client.view.RepoActivity

@Module
abstract class RepoModule {
    @Binds
    abstract fun bindsPresenter(impl: RepoPresenter): IRepoPresenter
}

@Subcomponent(modules = [RepoModule::class])
interface RepoComponent {
    fun inject(activity: RepoActivity)
}