package tonko.com.client.model

import com.google.gson.annotations.SerializedName

class Author {
    @SerializedName("name")
    var name: String = ""

    @SerializedName("date")
    var date: String = ""
}
