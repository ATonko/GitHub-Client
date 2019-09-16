package tonko.com.client.api.json_responses

import com.google.gson.annotations.SerializedName


class Owner : BaseModel() {
    @SerializedName("login")
    var login: String = ""

    @SerializedName("avatar_url")
    var avatar_url: String = ""


}