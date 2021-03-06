package tonko.com.client.api

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*
import tonko.com.client.api.json_responses.AccessToken
import tonko.com.client.api.json_responses.Commits
import tonko.com.client.api.json_responses.Owner
import tonko.com.client.api.json_responses.Repos


interface ApiMethods
{

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    fun getAccessToken(
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("code") code: String
    ): Single<AccessToken>

    @GET("user")
    fun getBasicAuth(
            @Header("Authorization") authHeader: String
    ): Single<Owner>

    @GET("users/{user}/repos")
    fun getRepoList(
            @Path("user") user: String
    ): Single<ArrayList<Repos>>

    @GET("user")
    fun getRepoListWithToken(
            @Header("Authorization") token: String
    ): Call<ArrayList<Repos>>

    @GET("repos/{user}/{project}/commits")
    fun getCommits(
            @Path("user") user: String,
            @Path("project") project: String
    ): Single<List<Commits>>
}