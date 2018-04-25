package tonko.com.client.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auth.*
import tonko.com.client.*
import tonko.com.client.iview.AuthView
import tonko.com.client.presenters.AuthPresenter

class AuthActivity : AppCompatActivity(), AuthView {

    private val clientId = "48e9432ab493b921da94"
    private val clientSecret = "e76843019ec43ad24161f0be281df501d499631c"
    private val redirectUri = "tonko://callback"
    private var TOKEN = "THE_TOKEN"
    private lateinit var sPref: SharedPreferences
    private val presenter = AuthPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        Log.i("MyTag", "onCreate")
        presenter.attachView(this)
        btnOAuth.setOnClickListener {
            openWebsite()
        }
        btnBasicAuth.setOnClickListener {
            presenter.basicLogin(
                    etLogin.text.toString(),
                    etPassword.text.toString())
        }
    }

    override fun isSuccess(accessToken: String) {
        val intent = Intent(this, RepoListActivity::class.java)
        intent.putExtra(TOKEN, accessToken)
        startActivity(intent)

    }

    override fun isSuccess(login: String, avatar_url: String) {
        sPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sPref.edit()
        editor.putString(LOGIN, login)
        editor.putString(PASSWORD, etPassword.text.toString())
        editor.apply()
        val intent = Intent(this, RepoListActivity::class.java)
        intent.putExtra(LOGIN, login)
        intent.putExtra(AVATAR_URL, avatar_url)
        startActivity(intent)
    }

    override fun isError(code: String) {
        when (code) {
            AUTH_PROBLEM -> Toast.makeText(this, resources.getString(R.string.auth_error), Toast.LENGTH_LONG).show()
            NET_PROBLEM -> Toast.makeText(this, resources.getString(R.string.network_error), Toast.LENGTH_LONG).show()
        }
    }


    override fun onResume() {
        super.onResume()
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            workingWithToken(uri)
        }
    }

    fun openWebsite() {
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/login/oauth/authorize" +
                        "?client_id=$clientId" + "&scope=repo" +
                        "&redirect_url=$redirectUri"))
        startActivity(intent)
    }

    fun workingWithToken(uri: Uri) {
        val code = uri.getQueryParameter("code")
        presenter.loginOAuth(clientId, clientSecret, code)

    }
}
