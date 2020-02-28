package tonko.com.client

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import tonko.com.client.api.ApiHolder
import tonko.com.client.api.ApiMethods
import tonko.com.client.dagger.RepoComponent
import tonko.com.client.dagger.RepoListComponent
import tonko.com.client.dagger.SharedPreferencesComponent
import tonko.com.client.model.RepoRepository
import tonko.com.client.model.interfaces.IRepoRepository
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(applicationContext))
                .build()
    }
}

@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideApiMethods(): ApiMethods = ApiHolder.privateApi

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideRepository(apiMethods: ApiMethods): IRepoRepository =
            RepoRepository(apiMethods)
}

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun plusSharedPrefCompoment(): SharedPreferencesComponent
    fun plusRepoListComponent(): RepoListComponent
    fun plusRepoComponent(): RepoComponent
}

