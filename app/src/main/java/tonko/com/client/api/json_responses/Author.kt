package tonko.com.client.api.json_responses

import com.google.gson.annotations.SerializedName

class Author {
    @SerializedName("name")
    var name: String = ""

    @SerializedName("date")
    var date: String = ""
}
