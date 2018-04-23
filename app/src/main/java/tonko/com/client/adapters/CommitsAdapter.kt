package tonko.com.client.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_commits.view.*
import tonko.com.client.R
import tonko.com.client.model.Commits


class CommitsAdapter(var list: List<Commits>) : RecyclerView.Adapter<CommitsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommitsViewHolder {
        return CommitsViewHolder(
                LayoutInflater
                        .from(parent!!.context)
                        .inflate(R.layout.item_commits, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommitsViewHolder?, position: Int) {
        holder!!.itemView.tvHash.text = list[position].hash.subSequence(0..6)
        holder.itemView.tvAuthor.text = list[position].commit.author.name
        holder.itemView.tvCommitMessage.text = list[position].commit.message
        holder.itemView.tvDate.text = list[position].commit.author.date
    }
}