package tonko.com.client.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tonko.com.client.R
import tonko.com.client.model.Commits


class CommitsListAdapter(override var list: List<Commits>) : AbstractListAdapter<Commits, CommitsViewHolder>(list)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder
    {
        return CommitsViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_commits, parent, false)
        )
    }
}
