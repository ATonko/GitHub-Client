package tonko.com.client.adapters

import android.view.View
import kotlinx.android.synthetic.main.item_commits.view.*
import tonko.com.client.model.Commits

class CommitsViewHolder(itemView: View) : AbstractViewHolder<Commits>(itemView)
{
    override fun bind(item: Commits)
    {
        with(itemView) {
            tvHash.text = item.hash.subSequence(0..6)
            tvAuthor.text = item.commit.author.name
            tvCommitMessage.text = item.commit.message
            tvDate.text = item.commit.author.date
        }
    }
}
