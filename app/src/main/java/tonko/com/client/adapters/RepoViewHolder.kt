package tonko.com.client.adapters

import android.support.v7.widget.RecyclerView
import android.view.View


class RepoViewHolder(itemView: View,
                     var listener: RepoListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        listener.onClick(adapterPosition)
    }
}