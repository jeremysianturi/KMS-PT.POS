package com.pos.lms.kms_pt_pos_indonesia.ui.wahana

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ItemListDigilabBinding
import com.pos.lms.kms_pt_pos_indonesia.databinding.ItemListWahanaBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.helper.loadImage
import timber.log.Timber

class WahanaAdapter : RecyclerView.Adapter<WahanaAdapter.UserViewHolder>() {

    var onItemClick: ((Wahana) -> Unit)? = null

    private val mData = ArrayList<Wahana>()

    fun setData(newListData: List<Wahana>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): WahanaAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_wahana, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: WahanaAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListWahanaBinding.bind(itemView)
        fun bind(data: Wahana) {
            with(binding) {

                // concat string
                tvContenttitle.text = data.cases
                tvNameWahana.text = data.creator
                tvDateWahana.text = data.beginDate
                ivProfilepic.loadImage(itemView.context,data.thumbnail)
                ivContentWahana.loadImage(itemView.context,data.thumbnail)



            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}