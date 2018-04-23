package tonko.com.client.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import tonko.com.client.R

import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    private lateinit var basicUri: String
    val URI_EXTRA: String = "URI1_EXTRA"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        supportActionBar?.title = ""
        webView.settings.javaScriptEnabled = true
        if (savedInstanceState == null) {
            basicUri = intent.getStringExtra(URI_EXTRA)
            loadingWebView(basicUri)
        }
    }

    private fun loadingWebView(s: String) {
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl(s)
    }
}
