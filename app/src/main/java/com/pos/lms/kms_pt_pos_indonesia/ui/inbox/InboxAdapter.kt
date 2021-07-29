package com.pos.lms.kms_pt_pos_indonesia.ui.inbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ItemListInboxBinding
import com.pos.lms.kms_pt_pos_indonesia.databinding.ItemListWahanaBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Inbox
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.helper.DateTimeConverter
import com.pos.lms.kms_pt_pos_indonesia.helper.loadImage
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.WahanaAdapter
import timber.log.Timber

class InboxAdapter : RecyclerView.Adapter<InboxAdapter.UserViewHolder>() {

    var onItemClick: ((Inbox) -> Unit)? = null

    private val mData = ArrayList<Inbox>()

    fun setData(newListData: List<Inbox>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): InboxAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_inbox, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: InboxAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListInboxBinding.bind(itemView)
        fun bind(data: Inbox) {
            with(binding) {

                // concat string
                tvTitleInbox.text = data.actor
                tvInbox.text = data.message
                tvInboxDate.text = DateTimeConverter.dateConverter(data.changeDate)




            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}