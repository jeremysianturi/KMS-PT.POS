package com.pos.lms.kms_pt_pos_indonesia.ui.digilab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ItemListDigilabBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Digilab
import com.pos.lms.kms_pt_pos_indonesia.helper.loadImage


class DigilabAdapter : RecyclerView.Adapter<DigilabAdapter.UserViewHolder>() {

    var onItemClick: ((Digilab) -> Unit)? = null

    private val mData = ArrayList<Digilab>()

    fun setData(newListData: List<Digilab>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): DigilabAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_digilab, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: DigilabAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListDigilabBinding.bind(itemView)
        fun bind(data: Digilab) {
            with(binding) {

                // concat string
                tvContenttitle.text = data.cases
                tvNameDigilab.text = data.creator
                tvDateDigilab.text = data.beginDate
                ivProfilepic.loadImage(itemView.context,data.thumbnail)
                ivContentDigilab.loadImage(itemView.context,data.thumbnail)

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}