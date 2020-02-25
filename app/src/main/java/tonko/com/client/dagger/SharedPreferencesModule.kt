package tonko.com.client.dagger

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import tonko.com.client.PREF_TABLE_NAME

@Module
class SharedPreferencesModule {
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(PREF_TABLE_NAME, Context.MODE_PRIVATE)
}

@Subcomponent(modules = [SharedPreferencesModule::class])
interface SharedPreferencesComponent {
    fun getSharedPref(): SharedPreferences
}