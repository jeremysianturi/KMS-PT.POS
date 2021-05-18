package com.pos.lms.kms_pt_pos_indonesia.ui.inbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityInboxBinding
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityWahanaBinding
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.WahanaActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.WahanaAdapter
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.WahanaViewModel
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailArticle.DetailArticleActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailwahana.DetailWahanaActivity
import com.pos.lms.kms_pt_pos_indonesia.util.dialog.SimpleDialog
import com.pos.lms.kms_pt_pos_indonesia.utils.ErrorMessageSplit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class InboxActivity : AppCompatActivity() {

    private val tag = InboxActivity::class.java.simpleName.toString()

    private lateinit var binding: ActivityInboxBinding
    private lateinit var adapter : InboxAdapter
    private val wahanaViewModel : InboxViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInboxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // navigate back
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // onclick
        onclick()

        // method
        setupObserver()
        buildList()

    }

    private fun onclick() {
        binding.apply {



        }
    }


    private fun setupObserver() {

        wahanaViewModel.getInbox("").observe(this, { data ->
            Timber.tag(tag).d("observer_inbox $data")
            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarProposal.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarProposal.visibility = View.GONE
                        adapter.setData(data.data)
                        Timber.tag(tag).d("observer_wahana_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarProposal.visibility = View.GONE
                        val message = ErrorMessageSplit.message(data.message.toString())
                        val code = ErrorMessageSplit.code(data.message.toString())
                        SimpleDialog.newInstance(code, message)
                            .show(supportFragmentManager, SimpleDialog.TAG)
                    }
                }

            }
        })

//        wahanaViewModel.search.observe(this, { data ->
//            adapter.setData(data)
//        })
    }

    private fun buildList() {

        adapter = InboxAdapter()
        binding.rvInbox.setHasFixedSize(true)
        binding.rvInbox.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvInbox.adapter = adapter

        binding.rvInbox.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}