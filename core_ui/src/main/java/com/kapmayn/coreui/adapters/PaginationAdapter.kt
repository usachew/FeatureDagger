package com.kapmayn.coreui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kapmayn.core.base.IPopulatable
import com.kapmayn.coreui.R
import com.kapmayn.coreui.views.HiddenMessageModel
import com.kapmayn.coreui.views.MessageModel

private const val SIMPLE_ITEM_TYPE = 10
private const val MESSAGE_ITEM_TYPE = 11
private const val COUNT_PAGINATION_ITEMS = 1

open class PaginationAdapter<MODEL>(
    private val layoutResId: Int,
    private val models: MutableList<MODEL> = mutableListOf(),
    var clickListener: (MODEL) -> Unit = {},
    var paginationCallback: () -> Unit = {}
) : RecyclerView.Adapter<PaginationAdapter.PopulateViewHolder<MODEL>>() {

    private var message: MessageModel = HiddenMessageModel()

    override fun getItemViewType(position: Int): Int {
        return when (itemCount - position) {
            0 -> MESSAGE_ITEM_TYPE
            else -> SIMPLE_ITEM_TYPE
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PopulateViewHolder<MODEL> {
        val viewId = when (viewType) {
            SIMPLE_ITEM_TYPE -> layoutResId
            MESSAGE_ITEM_TYPE -> R.layout.view_message
            else -> throw IllegalArgumentException()
        }
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(viewId, viewGroup, false)
        return PopulateViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PopulateViewHolder<MODEL>, position: Int) {
        val viewType = getItemViewType(position)

        when (viewType) {
            SIMPLE_ITEM_TYPE -> {
                val data = models[position]
                viewHolder.apply {
                    populate(data)
                    view.setOnClickListener { clickListener(data) }
                }
            }
            MESSAGE_ITEM_TYPE -> {
                viewHolder.populate(message)
                paginationCallback()
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount() = models.size + COUNT_PAGINATION_ITEMS

    fun updateData(items: List<MODEL>) {
        models.clear()
        models += items.toMutableList()
        notifyDataSetChanged()
    }

    fun updateMessageItem(message: MessageModel) {
        this.message = message
        notifyDataSetChanged()
    }

    class PopulateViewHolder<MODEL>(val view: View) : RecyclerView.ViewHolder(view) {

        fun populate(model: MODEL) {
            val populateView = view as IPopulatable<MODEL>
            populateView.populate(model)
        }

        fun populate(model: MessageModel) {
            val populateView = view as IPopulatable<MessageModel>
            populateView.populate(model)
        }
    }
}