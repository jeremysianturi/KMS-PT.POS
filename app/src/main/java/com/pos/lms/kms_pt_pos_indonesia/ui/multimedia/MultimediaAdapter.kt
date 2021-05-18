package com.pos.lms.kms_pt_pos_indonesia.ui.multimedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ItemListDigilabBinding
import com.pos.lms.kms_pt_pos_indonesia.databinding.ItemListMultimediaBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.helper.loadImage
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.WahanaAdapter

class MultimediaAdapter : RecyclerView.Adapter<MultimediaAdapter.UserViewHolder>() {

    var onItemClick: ((Multimedia) -> Unit)? = null

    private val mData = ArrayList<Multimedia>()

    fun setData(newListData: List<Multimedia>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): MultimediaAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_multimedia, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: MultimediaAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMultimediaBinding.bind(itemView)
        fun bind(data: Multimedia) {
            with(binding) {

                // concat string
                tvContenttitle.text = data.cases
                tvNameMultimedia.text = data.creator
                tvDateMultimedia.text = data.beginDate
                ivProfilepic.loadImage(itemView.context,data.thumbnail)
                ivContentMultimedia.loadImage(itemView.context,data.thumbnail)




            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}