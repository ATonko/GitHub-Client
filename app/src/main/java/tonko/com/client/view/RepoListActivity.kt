package tonko.com.client.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_repo_list.*
import tonko.com.client.LOGIN
import tonko.com.client.R
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

    override fun onClick(position: Int) {
        val intent = Intent(this, RepoActivity::class.java)
        intent.putExtra(USER, list[position].author!!.login)
        intent.putExtra(PROJECT, list[position].name)
        startActivity(intent)
    }

    override fun isSuccess(repos: ArrayList<Repos>) {
        rv.visibility = View.VISIBLE
        llError.visibility = View.GONE
        list = repos
        rv.adapter = RepoListAdapter(list, this)
    }

    override fun isError() {
        rv.visibility = View.GONE
        llError.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        presenter.attachView(this)

        btnReload.setOnClickListener {
            if (intent.getStringExtra(LOGIN) != null) {
                presenter.getList(intent.getStringExtra(LOGIN))
            }
        }

        rv.layoutManager = LinearLayoutManager(this)

        if (intent.getStringExtra(LOGIN) != null) {
            presenter.getList(intent.getStringExtra(LOGIN))
        }
    }

    override fun onBackPressed() {
    }
}
