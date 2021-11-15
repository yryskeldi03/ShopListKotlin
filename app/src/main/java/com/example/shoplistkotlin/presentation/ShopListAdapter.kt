package com.example.shoplistkotlin.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistkotlin.R
import com.example.shoplistkotlin.domain.ShopItem
import com.example.shoplistkotlin.presentation.diff_util.ShopItemDiffCallback
import java.lang.RuntimeException

class ShopListAdapter :
    ListAdapter<ShopItem, ShopListAdapter.ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemClickListener: ((ShopItem, Int) -> Unit)? = null
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLE -> R.layout.shop_list_enable
            VIEW_TYPE_DISABLE -> R.layout.shop_list_disable
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return ShopItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.onBind(getItem(position))

        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(getItem(position))
            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(getItem(position), position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enable) {
            VIEW_TYPE_ENABLE
        } else {
            VIEW_TYPE_DISABLE
        }
    }

    class ShopItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val tvCount = itemView.findViewById<TextView>(R.id.tv_count)
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)

        fun onBind(shopItem: ShopItem) {
            tvCount.text = shopItem.count.toString()
            tvName.text = shopItem.name
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLE = 1
        const val VIEW_TYPE_DISABLE = 2
        const val MAX_POOL_SIZE = 10
    }
}