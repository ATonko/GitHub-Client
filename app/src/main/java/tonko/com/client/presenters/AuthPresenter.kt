package tonko.com.client.presenters

import android.util.Log
import retrofit2.Call
import tonko.com.client.model.AccessToken
import tonko.com.client.api.ApiHolder

class AuthPresenter {

    private val publicApi = ApiHolder.publicApi
    fun login(clientId: String,
              clientSecret: String,
              code: String): Call<AccessToken> {
        Log.i("MyTag", "AuthPresenter login")
        return publicApi.getAccessToken(
                clientId,
                clientSecret,
                code)
    }
}