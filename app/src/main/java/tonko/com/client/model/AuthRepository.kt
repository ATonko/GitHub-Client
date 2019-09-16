package tonko.com.client.model

import io.reactivex.Single
import tonko.com.client.api.ApiHolder
import tonko.com.client.api.json_responses.AccessToken
import tonko.com.client.api.json_responses.Owner
import tonko.com.client.model.interfaces.IAuthRepository

class AuthRepository : IAuthRepository
{
    private val publicApi = ApiHolder.publicApi
    private val privateApi = ApiHolder.privateApi

    fun getAccessToken(id: String, secret: String, code: String): Single<AccessToken>
    {
        return publicApi.getAccessToken(id, secret, code)
    }

    fun getBasicAuth(authHeader: String): Single<Owner>
    {
        return privateApi.getBasicAuth(authHeader)
    }
}