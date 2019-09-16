package tonko.com.client.api.json_responses

import com.google.gson.annotations.SerializedName

class Repos {
    @SerializedName("name")
    var name: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("owner")
    var author: Owner? = null

    @SerializedName("watchers_count")
    var watchers: Int = 0

    @SerializedName("forks")
    var forks: Int = 0

    @SerializedName("language")
    var language: String = ""


}