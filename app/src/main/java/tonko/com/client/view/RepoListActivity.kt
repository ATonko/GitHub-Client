package tonko.com.client.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_repo_list.*
import tonko.com.client.LOGIN
import tonko.com.client.PASSWORD
import tonko.com.client.R
import tonko.com.client.TOKEN
import tonko.com.client.adapters.RepoListAdapter
import tonko.com.client.adapters.RepoListener
import tonko.com.client.iview.RepoListView
import tonko.com.client.model.Repos
import tonko.com.client.presenters.RepoListPresenter

class RepoListActivity : AppCompatActivity(), RepoListener, RepoListView {
    private val USER = "USER"
    private val PROJECT = "PROJECT"

    private val presenter = RepoListPresenter()
    private var list = ArrayList<Repos>()
    private lateinit var sPref: SharedPreferences
    private lateinit var avatar_url: String

    override fun onClick(position: Int) {
        val intent = Intent(this, RepoActivity::class.java)
        intent.putExtra(USER, list[position].author!!.login)
        intent.putExtra(PROJECT, list[position].name)
        startActivity(intent)
    }

    override fun isSuccess(repos: ArrayList<Repos>) {
        rv.visibility = View.VISIBLE
        llNameWithAvatar.visibility = View.VISIBLE
        llError.visibility = View.GONE

        Glide
                .with(this)
                .load(repos[0].author!!.avatar_uri)
                .into(ivAvatar)
        tvAuthorName.text = repos[0].author!!.login
        list = repos
        rv.adapter = RepoListAdapter(list, this)
    }

    override fun isError() {
        rv.visibility = View.GONE
        llNameWithAvatar.visibility = View.GONE
        llError.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        presenter.attachView(this)
        sPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        btnReload.setOnClickListener {
            if (intent.getStringExtra(LOGIN) != null) {
                presenter.getList(intent.getStringExtra(LOGIN))
            }
        }
        btnQuit.setOnClickListener {
            val editor = sPref.edit()
            editor.putString(LOGIN, "")
            editor.putString(PASSWORD, "")
            editor.apply()
            startActivity(Intent(this, AuthActivity::class.java))
        }

        rv.layoutManager = LinearLayoutManager(this)
        if (intent.getStringExtra(LOGIN) != null) {
            presenter.getList(intent.getStringExtra(LOGIN))
        }
        if (sPref.getString(LOGIN, "").isNotEmpty()) {
            presenter.getList(sPref.getString(LOGIN, ""))
        }


    }

    override fun onBackPressed() {
    }
}
