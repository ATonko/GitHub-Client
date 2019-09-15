package tonko.com.client.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import tonko.com.client.R
import tonko.com.client.model.Repos


class RepoListAdapter(override var list: List<Repos>,
                      var listener: RepoListener) : AbstractListAdapter<Repos, RepoViewHolder>(list)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder
    {
        return RepoViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(
                                R.layout.item_repo,
                                parent,
                                false

                        ), listener)
    }
}