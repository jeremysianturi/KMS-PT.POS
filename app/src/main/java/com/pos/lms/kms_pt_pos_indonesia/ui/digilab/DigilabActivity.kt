package com.pos.lms.kms_pt_pos_indonesia.ui.digilab

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityDigilabBinding
import com.pos.lms.kms_pt_pos_indonesia.ui.digilab.detaildigilab.DetailDigilabActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.WahanaAdapter
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.WahanaViewModel
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.createwahana.CreateWahanaActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailArticle.DetailArticleActivity
import com.pos.lms.kms_pt_pos_indonesia.util.dialog.ErrorBottomSheet
import com.pos.lms.kms_pt_pos_indonesia.util.dialog.SimpleDialog
import com.pos.lms.kms_pt_pos_indonesia.utils.ErrorMessageSplit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DigilabActivity : AppCompatActivity() {

    private val tag = DigilabActivity::class.java.simpleName.toString()

    private lateinit var binding: ActivityDigilabBinding
    private lateinit var adapter : DigilabAdapter

    private val digilabViewModel : DigilabViewModel by viewModels()

    private var type : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDigilabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra("type").toString()
        Timber.d("tes value type : $type")

        // setup toolbar
        toolbarSetup()

        // onclick
        onclick()

        // search
//        binding.layoutToolbar.edtSearch.doOnTextChanged { text, start, before, count ->
//            digilabViewModel.searchQuery.value = text.toString()
//        }

        // search dari api
        binding.layoutToolbar.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                Timber.d("check char search digilab : ${binding.layoutToolbar.edtSearch.text.toString()}")

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
            mIntent.putExtra(CreateWahanaActivity.ACTIVITY_FROM, "02")
            startActivity(mIntent)
        }

        // method
        setupObserver(type)
        buildList()

    }

    private fun toolbarSetup() {

        binding.layoutToolbar.apply {
            ivNavigationBack.setOnClickListener { onBackPressed() }

            Glide.with(this@DigilabActivity)
                .load(R.drawable.banner_digilab)
                .into(imageView)

            tvTittle1.text = getString(R.string.tv_infinites)
            tvTittle2.text = getString(R.string.tv_digilab)
        }
    }


    private fun onclick() {
        binding.apply {
//            tvArticle.setOnClickListener {
//                val mIntent = Intent(this@WahanaActivity, ArtikelActivity::class.java)
//                startActivity(mIntent)
//            }
//            tvKaryaTulis.setOnClickListener {
//                Toast.makeText(this@WahanaActivity, "fitur not ready", Toast.LENGTH_SHORT).show()
//            }
//            tvDiseminasi.setOnClickListener {
//                Toast.makeText(this@WahanaActivity, "fitur not ready", Toast.LENGTH_SHORT).show()
//            }

        }
    }


    private fun setupObserver(typeClicked : String) {

        digilabViewModel.getDigilab(typeClicked).observe(this, { data ->
            Timber.tag(tag).d("observer_digilab $data")
            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarProposal.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarProposal.visibility = View.GONE
                        adapter.setData(data.data)
                        Timber.tag(tag).d("observer_digilab_adapter ${data.data}")
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

//        digilabViewModel.search.observe(this, { data ->
//            adapter.setData(data)
//
//        })
    }

    private fun setupSearchObserver(typeClicked: String, search: String) {

        digilabViewModel.getSearchDigilab(typeClicked,search).observe(this, { data ->
            Timber.tag(tag).d("observer_searchdigilab $data")
            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarProposal.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarProposal.visibility = View.GONE
                        adapter.setData(data.data)
                        Timber.tag(tag).d("observer_searchdigilab_adapter ${data.data}")
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

        adapter = DigilabAdapter()
        binding.rvDigilab.setHasFixedSize(true)
        binding.rvDigilab.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvDigilab.adapter = adapter

        binding.rvDigilab.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        adapter.onItemClick = { selectData ->
            val mIntent = Intent(this, DetailDigilabActivity::class.java)
            mIntent.putExtra(DetailDigilabActivity.EXTRA_DATA, selectData)
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