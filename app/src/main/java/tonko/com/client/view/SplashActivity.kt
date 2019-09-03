package tonko.com.client.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import tonko.com.client.LOGIN
import tonko.com.client.PASSWORD
import tonko.com.client.R

class SplashActivity : Activity() {

    private lateinit var sPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            sPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            if (sPref.getString(LOGIN, "").isNotEmpty() && sPref.getString(PASSWORD, "").isNotEmpty()) {
                startActivity(Intent(this, RepoListActivity::class.java))
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
            }
        }, 1500)
    }
}
