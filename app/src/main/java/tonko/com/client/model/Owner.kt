package tonko.com.client.model

import com.google.gson.annotations.SerializedName


class Owner : BaseModel() {
    @SerializedName("login")
    var login: String = ""

    @SerializedName("avatar_url")
    var avatar_uri: String=""


}