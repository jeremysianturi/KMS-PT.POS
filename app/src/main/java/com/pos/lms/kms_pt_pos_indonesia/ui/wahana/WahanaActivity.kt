package com.pos.lms.kms_pt_pos_indonesia.ui.wahana

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityWahanaBinding
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.createwahana.CreateWahanaActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailArticle.DetailArticleActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailwahana.DetailWahanaActivity
import com.pos.lms.kms_pt_pos_indonesia.util.dialog.ErrorBottomSheet
import com.pos.lms.kms_pt_pos_indonesia.util.dialog.SimpleDialog
import com.pos.lms.kms_pt_pos_indonesia.utils.ErrorMessageSplit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WahanaActivity : AppCompatActivity() {

    private val tag = WahanaActivity::class.java.simpleName.toString()

    private lateinit var binding: ActivityWahanaBinding
    private lateinit var adapter : WahanaAdapter
    private val wahanaViewModel : WahanaViewModel by viewModels()

    private var type : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWahanaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //biar keyboard ga lgsg popup
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        type = intent.getStringExtra("type").toString()
        Timber.d("tes value type : $type")

        // onclick
        onclick()

        // setup toolbar
        toolbarSetup()

        // search dari api
        binding.layoutToolbar.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                Timber.d("check char search wahana : ${binding.layoutToolbar.edtSearch.text.toString()}")

                setupSearchObserver(type,binding.layoutToolbar.edtSearch.text.toString())
                buildList()

                // hiding keyboard
                binding.layoutToolbar.edtSearch.hideKeyboard()
                true
            } else {
                false
            }
        }
        // search dari api

        // add content
        binding.layoutAddContent.tvAddContent.setOnClickListener{
            val mIntent = Intent(this, CreateWahanaActivity::class.java)
            mIntent.putExtra(CreateWahanaActivity.ACTIVITY_FROM, "01")
            startActivity(mIntent)
        }

        // method
        setupObserver(type)
        buildList()

    }

    private fun onclick() {
        binding.apply {

        }
    }

    private fun toolbarSetup() {

        binding.layoutToolbar.apply {
            ivNavigationBack.setOnClickListener { onBackPressed() }

            Glide.with(this@WahanaActivity)
                .load(R.drawable.banner_wahana)
                .into(imageView)

            tvTittle1.text = getString(R.string.tv_infinites)
            tvTittle2.text = getString(R.string.tv_wahana)
        }
    }

    private fun setupObserver(typeClicked: String) {

        wahanaViewModel.getWahana(typeClicked).observe(this, { data ->
            Timber.tag(tag).d("observer_wahana $data")
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
//                        SimpleDialog.newInstance(code, message)
//                            .show(supportFragmentManager, SimpleDialog.TAG)
                        ErrorBottomSheet.instance(code, message)
                            .show(supportFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        })
    }

    private fun setupSearchObserver(typeClicked: String, search: String) {

        wahanaViewModel.getSearchWahana(typeClicked,search).observe(this, { data ->
            Timber.tag(tag).d("observer_searchwahana $data")
            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarProposal.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarProposal.visibility = View.GONE
                        adapter.setData(data.data)
                        Timber.tag(tag).d("observer_searchwahana_adapter ${data.data}")
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

    }

    private fun buildList() {

        adapter = WahanaAdapter()
        binding.rvWahana.setHasFixedSize(true)
        binding.rvWahana.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvWahana.adapter = adapter

        binding.rvWahana.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        adapter.onItemClick = { selectData ->
            val mIntent = Intent(this, DetailWahanaActivity::class.java)
            mIntent.putExtra(DetailArticleActivity.EXTRA_DATA, selectData)
            startActivity(mIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}