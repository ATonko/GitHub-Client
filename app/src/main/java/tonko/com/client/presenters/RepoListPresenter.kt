package tonko.com.client.presenters

import android.util.Log
import retrofit2.Call
import tonko.com.client.api.ApiHolder
import tonko.com.client.model.Repos

/**
 * Created by Tonko on 22.04.2018.
 */
class RepoListPresenter {

    private val privateApi = ApiHolder.privateApi

    fun getList(token: String): Call<ArrayList<Repos>> {
        Log.i("MyTag", "presenter token is $token")
        return privateApi.getRepoList(token)
    }
}