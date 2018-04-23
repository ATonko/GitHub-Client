package tonko.com.client.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tonko on 23.04.2018.
 */
class Commit {
    @SerializedName("message")
    var message: String = ""

    @SerializedName("author")
    var author: Author = Author()

}