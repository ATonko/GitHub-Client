package tonko.com.client.dagger

import dagger.Component
import dagger.Module
import dagger.Provides
import tonko.com.client.presenters.AuthPresenter
import tonko.com.client.presenters.interfaces.IAuthPresenter
import tonko.com.client.view.AuthActivity


@Module
class AuthModule {
    @Provides
    fun providePresenter(): IAuthPresenter =
            AuthPresenter()
}

@Component(modules = [AuthModule::class])
interface AuthComponent {
    fun inject(activity: AuthActivity)
}