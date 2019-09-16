package tonko.com.client.api.json_responses

import com.google.gson.annotations.SerializedName

class AccessToken : BaseModel() {
    @SerializedName("access_token")
    var accessToken: String=""

    @SerializedName("token_type")
    var type: String=""
}