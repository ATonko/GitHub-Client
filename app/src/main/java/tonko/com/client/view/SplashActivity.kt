package tonko.com.client.view

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import tonko.com.client.App
import tonko.com.client.LOGIN
import tonko.com.client.PASSWORD
import tonko.com.client.R
import javax.inject.Inject

class SplashActivity : Activity() {

    @Inject
    lateinit var sPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sPref = App.appComponent.plusSharedPrefCompoment().getSharedPref()

        Handler().postDelayed({
            if (sPref.getString(LOGIN, "").isNotEmpty() && sPref.getString(PASSWORD, "").isNotEmpty()) {
                startActivity(Intent(this, RepoListActivity::class.java))
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
            }
        }, 1500)
    }
}
