package tonko.com.client.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tonko on 22.04.2018.
 */
class AccessToken {
    @SerializedName("access_token")
    var accessToken: String=""

    @SerializedName("token_type")
    var type: String=""
}