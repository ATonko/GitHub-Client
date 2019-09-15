package tonko.com.client.adapters

import android.view.View
import kotlinx.android.synthetic.main.item_repo.view.*
import tonko.com.client.model.Repos


class RepoViewHolder(itemView: View,
                     var listener: RepoListener) : AbstractViewHolder<Repos>(itemView), View.OnClickListener
{
    init
    {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?)
    {
        listener.onClick(adapterPosition)
    }

    override fun bind(item: Repos)
    {
        with(itemView) {
            tvTitle.text = item.name
            tvLanguage.text = item.language
            tvForks.text = item.forks.toString()
            tvWatchers.text = item.watchers.toString()
            tvDescription.text = item.description
        }
    }
}