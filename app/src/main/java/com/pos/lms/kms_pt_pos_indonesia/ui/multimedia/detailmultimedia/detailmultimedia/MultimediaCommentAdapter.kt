package com.pos.lms.kms_pt_pos_indonesia.ui.multimedia.detailmultimedia.detailmultimedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ItemListCommentBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.DigilabComment
import com.pos.lms.kms_pt_pos_indonesia.domain.model.MultimediaComment
import com.pos.lms.kms_pt_pos_indonesia.helper.loadImage
import com.pos.lms.kms_pt_pos_indonesia.ui.digilab.detaildigilab.comment.DigilabCommentAdapter

class MultimediaCommentAdapter : RecyclerView.Adapter<MultimediaCommentAdapter.UserViewHolder>() {

    var onItemClick: ((MultimediaComment) -> Unit)? = null

    private val mData = ArrayList<MultimediaComment>()

    fun setData(newListData: List<MultimediaComment>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_comment, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCommentBinding.bind(itemView)
        fun bind(data: MultimediaComment) {
            with(binding) {

                // concat string
                tvNameComment.text = data.owner
                tvComment.text = data.comment
                ivProfilepicComment.loadImage(itemView.context,data.changeUser)         // masih belom ada profile pic

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}