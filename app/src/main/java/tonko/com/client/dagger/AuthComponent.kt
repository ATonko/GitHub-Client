package tonko.com.client.dagger

import dagger.Binds
import dagger.Component
import dagger.Module
import tonko.com.client.presenters.AuthPresenter
import tonko.com.client.presenters.interfaces.IAuthPresenter
import tonko.com.client.view.AuthActivity


@Module
abstract class AuthModule {
    @Binds
    abstract fun bindsPresenter(impl: AuthPresenter): IAuthPresenter
}

@Component(modules = [AuthModule::class])
interface AuthComponent {
    fun inject(activity: AuthActivity)
}