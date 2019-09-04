package tonko.com.client.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_repo.view.*
import tonko.com.client.model.Repos


class RepoViewHolder(itemView: View,
                     var listener: RepoListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener
{
    init
    {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?)
    {
        listener.onClick(adapterPosition)
    }

    fun bind(repo: Repos)
    {
        with(itemView) {
            tvTitle.text = repo.name
            tvLanguage.text = repo.language
            tvForks.text = repo.forks.toString()
            tvWatchers.text = repo.watchers.toString()
            tvDescription.text = repo.description
        }
    }
}