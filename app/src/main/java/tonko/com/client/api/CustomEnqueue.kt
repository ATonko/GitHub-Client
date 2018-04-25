package tonko.com.client.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tonko.com.client.model.Owner


open class CustomEnqueue<T> {

    fun customEnqueue(call: Call<T>, fail: () -> Unit = {}, success: (Response<T>) -> Unit = {}) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
                fail()
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                success(response!!)
            }
        })
    }
}