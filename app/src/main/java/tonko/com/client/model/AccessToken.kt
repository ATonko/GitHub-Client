package tonko.com.client.model

import com.google.gson.annotations.SerializedName

class AccessToken : BaseModel() {
    @SerializedName("access_token")
    var accessToken: String=""

    @SerializedName("token_type")
    var type: String=""
}