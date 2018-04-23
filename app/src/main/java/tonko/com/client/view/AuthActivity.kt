package tonko.com.client.view

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auth.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tonko.com.client.R
import tonko.com.client.model.AccessToken
import tonko.com.client.presenters.AuthPresenter

class AuthActivity : AppCompatActivity() {

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
        if (intent.data == null || intent.getBooleanExtra("QUIT", true)) {
            openWebsite()
        }

    }

    override fun onResume() {
        super.onResume()
        val uri = intent.data
        if (uri == null) {

        } else {
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
        val accessToken = presenter.login(clientId, clientSecret, code)

        accessToken.enqueue(object : Callback<AccessToken> {
            override fun onFailure(call: Call<AccessToken>?, t: Throwable?) {
                Toast.makeText(this@AuthActivity, "Проблемы с интернетом", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AccessToken>?, response: Response<AccessToken>?) {
                if (response!!.isSuccessful) {
                    val intent = Intent(this@AuthActivity, RepoListActivity::class.java)
                    token = response.body()!!.accessToken
                    intent.putExtra(TOKEN, token)
                    Log.i("MyTag", "token = ${response.body()!!.accessToken}")
                    startActivity(intent)
                }
            }
        })

    }
}
