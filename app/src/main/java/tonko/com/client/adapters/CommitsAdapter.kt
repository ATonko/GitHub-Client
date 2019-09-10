package tonko.com.client.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import tonko.com.client.R
import tonko.com.client.model.Commits


class CommitsAdapter(var list: List<Commits>) : RecyclerView.Adapter<CommitsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder
    {
        return CommitsViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_commits, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommitsViewHolder, position: Int)
    {
        holder.bind(list[position])
    }
}