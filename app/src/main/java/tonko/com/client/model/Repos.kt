package tonko.com.client.model

import android.net.Uri
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


}