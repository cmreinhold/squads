package com.reinhold.squads.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.reinhold.squads.data.db.ListItem

abstract class ListItemPagedListAdapter<T : ListItem, VB : ViewDataBinding> :
    PagedListAdapter<T, ListItemViewHolder<VB>>(
        object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
                oldItem.isSame(newItem)
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder<VB> {
        val binding = DataBindingUtil.inflate<VB>(
            LayoutInflater.from(parent.context),
            getLayoutOf(viewType),
            parent,
            false
        )
        return ListItemViewHolder(
            binding
        )
    }

    @LayoutRes
    abstract fun getLayoutOf(viewType: Int): Int

    override fun onBindViewHolder(holder: ListItemViewHolder<VB>, position: Int) {
        val listItem = getItem(position) ?: return
        bindItem(holder.binding, position, listItem)
    }

    abstract fun bindItem(holder: VB, position: Int, listItem: T)

    fun asLiveData(dataSourceFactory: DataSource.Factory<T,T>) = LivePagedListBuilder<PagedList<T>>(
        dataSourceFactory,
        getConfig()
    ).build()

    private fun getConfig() = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(50) // default = pagesize * 3
        .setPrefetchDistance(10) // default = pagesize
        .setEnablePlaceholders(false)
        .build()
}
