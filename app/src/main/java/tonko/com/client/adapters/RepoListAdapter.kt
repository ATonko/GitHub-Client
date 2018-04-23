package tonko.com.client.adapters

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_repo.view.*
import tonko.com.client.R
import tonko.com.client.model.Repos


class RepoListAdapter(var list: ArrayList<Repos>,
                      var listener: RepoListener) : RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
                LayoutInflater
                        .from(parent!!.context)
                        .inflate(
                                R.layout.item_repo,
                                parent,
                                false

                        ), listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder?, position: Int) {
        holder!!.itemView.tvTitle.text = list[position].name
        holder.itemView.ivAvatar.setImageURI(Uri.parse(list[position].author!!.avatar_uri))
        holder.itemView.tvAuthor.text = list[position].author!!.login
        holder.itemView.tvForks.text = "Forks: ${list[position].forks}"
        holder.itemView.tvWatchers.text = "Watchers: ${list[position].watchers}"
        holder.itemView.tvDescription.text = list[position].description
    }
}