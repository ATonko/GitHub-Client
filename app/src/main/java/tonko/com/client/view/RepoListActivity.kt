package tonko.com.client.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_repo_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tonko.com.client.R
import tonko.com.client.adapters.RepoListAdapter
import tonko.com.client.adapters.RepoListener
import tonko.com.client.model.Repos
import tonko.com.client.presenters.RepoListPresenter

class RepoListActivity : AppCompatActivity(), RepoListener {
    override fun onClick(position: Int) {
        val intent = Intent(this, RepoActivity::class.java)
        intent.putExtra(USER, list[position].author!!.login)
        intent.putExtra(PROJECT, list[position].name)
        startActivity(intent)
    }

    private var TOKEN = "THE_TOKEN"
    private val USER = "USER"
    private val PROJECT = "PROJECT"

    private val presenter = RepoListPresenter()
    private var list = ArrayList<Repos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        rv.layoutManager = GridLayoutManager(this, 2)
        presenter.getList("ATonko").enqueue(object : Callback<ArrayList<Repos>> {
            override fun onFailure(call: Call<ArrayList<Repos>>?, t: Throwable?) {
                Toast.makeText(this@RepoListActivity, "Проблемы с интернетом", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ArrayList<Repos>>?, response: Response<ArrayList<Repos>>?) {
                Log.i("MyTag", "Response")
                if (response!!.isSuccessful) {
                    list = response.body()!!
                    rv.adapter = RepoListAdapter(list, this@RepoListActivity)
                }
            }

        })

    }
}
