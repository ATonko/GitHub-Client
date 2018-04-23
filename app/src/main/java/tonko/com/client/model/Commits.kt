package tonko.com.client.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tonko on 23.04.2018.
 */
class Commits {
    @SerializedName("sha")
    var hash: String = ""

    @SerializedName("commit")
    var commit: Commit = Commit()
}