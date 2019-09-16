package tonko.com.client.api.json_responses

import com.google.gson.annotations.SerializedName


class Commits {
    @SerializedName("sha")
    var hash: String = ""

    @SerializedName("commit")
    var commit: Commit = Commit()
}