package tonko.com.client.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auth.*
import tonko.com.client.AVATAR_URL
import tonko.com.client.LOGIN
import tonko.com.client.R
import tonko.com.client.iview.AuthView
import tonko.com.client.presenters.AuthPresenter

class AuthActivity : AppCompatActivity(), AuthView {

    private val clientId = "48e9432ab493b921da94"
    private val clientSecret = "e76843019ec43ad24161f0be281df501d499631c"
    private val redirectUri = "tonko://callback"
    private var TOKEN = "THE_TOKEN"
    val URI_EXTRA: String = "URI1_EXTRA"
    private val presenter = AuthPresenter()
    private lateinit var token: String

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
        val intent = Intent(this, RepoListActivity::class.java)
        intent.putExtra(LOGIN, login)
        intent.putExtra(AVATAR_URL, avatar_url)
        startActivity(intent)
    }

    override fun isError() {
        Toast.makeText(this, resources.getString(R.string.network_error), Toast.LENGTH_LONG).show()
    }


    override fun onResume() {
        super.onResume()
        Log.i("MyTag", "onResume")
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            Log.i("MyTag", "start of working with uri")
            workingWithToken(uri)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MyTag", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MyTag", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MyTag", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MyTag", "onStop")
    }


    fun openWebsite() {
        Log.i("MyTag", "openwebsite")
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
