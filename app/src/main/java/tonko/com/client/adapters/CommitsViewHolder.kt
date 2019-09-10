package tonko.com.client.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_commits.view.*
import tonko.com.client.model.Commits

class CommitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    fun bind(commit: Commits)
    {
        with(itemView) {
            tvHash.text = commit.hash.subSequence(0..6)
            tvAuthor.text = commit.commit.author.name
            tvCommitMessage.text = commit.commit.message
            tvDate.text = commit.commit.author.date
        }


    }

}
