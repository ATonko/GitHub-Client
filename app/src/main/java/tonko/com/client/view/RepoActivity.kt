package tonko.com.client.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import tonko.com.client.R
import tonko.com.client.presenters.RepoPresenter

class RepoActivity : AppCompatActivity() {

    private val USER = "USER"
    private val PROJECT = "PROJECT"

    private val presenter = RepoPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        /*presenter.getCommits(
                intent.getStringExtra(USER),
                intent.getStringExtra(PROJECT)).enqueue(object : Callback<List<Commits>> {
            override fun onFailure(call: Call<List<Commits>>?, t: Throwable?) {
                Toast.makeText(this@RepoActivity,"Проблемы с интернетом", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Commits>>?, response: Response<List<Commits>>?) {
                Log.i("MyTag", "response")
                if (response!!.isSuccessful) {
                    Log.i("MyTag", "success")
                    rvRepo.layoutManager = GridLayoutManager(this@RepoActivity, 3)
                    Log.i("MyTag", "${response.body()!!.size}")
                    rvRepo.adapter = CommitsAdapter(response.body()!!)
                }
            }

        })
        */
    }
}
