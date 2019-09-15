package tonko.com.client.adapters

import android.support.v7.widget.RecyclerView

abstract class AbstractListAdapter<T, VH : AbstractViewHolder<T>>(open var list: List<T>) : RecyclerView.Adapter<VH>()
{
    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: VH, position: Int)
    {
        holder.bind(list[position])
    }
}
