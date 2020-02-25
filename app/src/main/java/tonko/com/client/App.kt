package tonko.com.client

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import tonko.com.client.dagger.SharedPreferencesComponent
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
    fun provideContext(): Context = context
}

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun plusSharedPrefCompoment(): SharedPreferencesComponent
}

