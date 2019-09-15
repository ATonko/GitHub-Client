package tonko.com.client.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_repo.*
import tonko.com.client.PROJECT
import tonko.com.client.R
import tonko.com.client.USER
import tonko.com.client.adapters.CommitsAdapter
import tonko.com.client.iview.RepoView
import tonko.com.client.model.Commits
import tonko.com.client.presenters.RepoPresenter

class RepoActivity : AppCompatActivity(), RepoView
{
    private val presenter = RepoPresenter()


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        presenter.attachView(this)
        presenter.getCommits(
                intent.getStringExtra(USER),
                intent.getStringExtra(PROJECT))
    }

    override fun isSuccess(list: List<Commits>)
    {
        tvNoRepos.visibility = View.GONE
        rvRepo.layoutManager = LinearLayoutManager(
                this@RepoActivity,
                LinearLayoutManager.VERTICAL,
                false)
        rvRepo.adapter = CommitsAdapter(list)
    }

    override fun isError(error: Int)
    {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun isEmptyList()
    {
        tvNoRepos.visibility = View.VISIBLE
    }

    override fun onDestroy()
    {
        super.onDestroy()
        presenter.detachView()
    }

}
