package com.pos.lms.kms_pt_pos_indonesia.ui.digilab

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityDigilabBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.CategoryList
import com.pos.lms.kms_pt_pos_indonesia.helper.CurrentDate
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

    private lateinit var dataCategory : CategoryList
    private lateinit var listCodeCategory : ArrayList<String>
    private lateinit var listNameCategory : ArrayList<String>
    private val listDataCategory = ArrayList<CategoryList>()
    private lateinit var listDataCategorySpinner : ArrayList<String>
    private var codeChoosen : String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDigilabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra("type").toString()

        //biar keyboard ga lgsg popup
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        // setup toolbar
        toolbarSetup()

        // set filter category to visible
        binding.layoutAddContent.spinnerCategory.visibility = View.VISIBLE

        // onclick
        onclick()

        // search
//        binding.layoutToolbar.edtSearch.doOnTextChanged { text, start, before, count ->
//            digilabViewModel.searchQuery.value = text.toString()
//        }

        // search dari api
        binding.layoutToolbar.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){

                setupSearchObserver(type,binding.layoutToolbar.edtSearch.text.toString(),codeChoosen!!)
                buildList()
                // hiding keyboard
                binding.layoutToolbar.edtSearch.hideKeyboard()
                true
            } else {
                false
            }
        }
        // search dari api

        // method
        setupObserver(type)
        buildList()

        // for category
        listCodeCategory = ArrayList<String>()
        listNameCategory = ArrayList<String>()
        listDataCategorySpinner = ArrayList<String>()
        val today = CurrentDate.getToday()
        getListCategory(today)

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

            // add content
            layoutAddContent.tvAddContent.setOnClickListener{
                val mIntent = Intent(this@DigilabActivity, CreateWahanaActivity::class.java)
                mIntent.putExtra(CreateWahanaActivity.ACTIVITY_FROM, "02")
                startActivity(mIntent)
            }

            // filter catogory
            layoutAddContent.spinnerCategory.setOnClickListener {

                val spinnerDialogDataTypes = SpinnerDialog(
                    this@DigilabActivity, listDataCategorySpinner, "Select Item :", R.style.DialogAnimations_SmileWindow
                )

                spinnerDialogDataTypes.bindOnSpinerListener { s, i ->
                    val name = listDataCategory[i].dataName
                    val code = listDataCategory[i].dataCode

                    binding.layoutAddContent.spinnerCategory.text = name
                    codeChoosen = code

                    if (layoutToolbar.edtSearch.text.toString().equals("")){
                        Timber.d("masuk ke sini gak ya")
                        SimpleDialog.newInstance("", "Please input search character at least 2 words").show(supportFragmentManager, SimpleDialog.TAG)
                    } else {
                        setupSearchObserver(type,binding.layoutToolbar.edtSearch.text.toString(),codeChoosen!!)
                    }

                    Timber.d("code chosennya adalah : $codeChoosen")

                }
                spinnerDialogDataTypes.showSpinerDialog()

            }


        }
    }


    private fun setupObserver(typeClicked : String) {

        digilabViewModel.getDigilab(typeClicked).observe(this, { data ->
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

    private fun getListCategory(date: String) {

        digilabViewModel.getCategory(date, date).observe(this, { data ->

            Timber.d("check isi category : ${data.data}")

            if (data != null) {
                when (data) {
                    is Resource.Loading -> { }
                    is Resource.Success -> {

                        listCodeCategory.clear()
                        listNameCategory.clear()

                        // for adding "all categories" text
                        var code = "-"
                        var name = "All Categories"

                        dataCategory = CategoryList(name, code)
                        listDataCategory.add(dataCategory)
                        listDataCategorySpinner.add(name)
                        // for adding "all categories" text


                        for (i in 0 until data.data!!.size) {

                            code = data.data!![i].id
                            name = data.data!![i].value

                            dataCategory = CategoryList(name, code)
                            listDataCategory.add(dataCategory)
                            listDataCategorySpinner.add(name)
                        }
                    }

                    is Resource.Error -> {
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

    private fun setupSearchObserver(typeClicked: String, search: String, category: String) {

        digilabViewModel.getSearchDigilab(typeClicked,search,category).observe(this, { data ->
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