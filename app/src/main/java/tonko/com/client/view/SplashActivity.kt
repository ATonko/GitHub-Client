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
            val intent = validAuth(sPref.getString(LOGIN, ""), sPref.getString(PASSWORD, ""))
            startActivity(intent)
        }, 1500)
    }

    private fun validAuth(login: String, password: String): Intent =
            if (login.isNotEmpty() && password.isNotEmpty()) Intent(this, RepoListActivity::class.java)
            else Intent(this, AuthActivity::class.java)
}