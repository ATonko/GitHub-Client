package tonko.com.client.model

import android.net.Uri
import com.google.gson.annotations.SerializedName

/**
 * Created by Tonko on 23.04.2018.
 */
class Owner {
    @SerializedName("login")
    var login: String = ""

    @SerializedName("avatar_url")
    var avatar_uri: String=""


}