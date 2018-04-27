package tonko.com.client.model

import com.google.gson.annotations.SerializedName


class Commits {
    @SerializedName("sha")
    var hash: String = ""

    @SerializedName("commit")
    var commit: Commit = Commit()
}