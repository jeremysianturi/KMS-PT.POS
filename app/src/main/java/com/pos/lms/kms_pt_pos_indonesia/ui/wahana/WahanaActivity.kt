package com.pos.lms.kms_pt_pos_indonesia.ui.wahana

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityWahanaBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Category
import com.pos.lms.kms_pt_pos_indonesia.domain.model.CategoryList
import com.pos.lms.kms_pt_pos_indonesia.helper.CurrentDate
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.createwahana.CreateWahanaActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailArticle.DetailArticleActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailwahana.DetailWahanaActivity
import com.pos.lms.kms_pt_pos_indonesia.util.dialog.ErrorBottomSheet
import com.pos.lms.kms_pt_pos_indonesia.util.dialog.SimpleDialog
import com.pos.lms.kms_pt_pos_indonesia.utils.ErrorMessageSplit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WahanaActivity : AppCompatActivity() {

    private val tag = WahanaActivity::class.java.simpleName.toString()

    private lateinit var binding: ActivityWahanaBinding
    private lateinit var adapter : WahanaAdapter
    private val wahanaViewModel : WahanaViewModel by viewModels()

    private var type : String = ""

    private lateinit var dataCategory : CategoryList
    private lateinit var listCodeCategory : ArrayList<String>
    private lateinit var listNameCategory : ArrayList<String>
    private val listDataCategory = ArrayList<CategoryList>()
    private lateinit var listDataCategorySpinner : ArrayList<String>
    private var codeChoosen : String? = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWahanaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //biar keyboard ga lgsg popup
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        type = intent.getStringExtra("type").toString()

        // onclick
        onclick()

        // setup toolbar
        toolbarSetup()

        // set filter category to visible
        binding.layoutAddContent.spinnerCategory.visibility = View.VISIBLE

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

    private fun onclick() {
        binding.apply {

            // add content
            layoutAddContent.tvAddContent.setOnClickListener{
                val mIntent = Intent(this@WahanaActivity, CreateWahanaActivity::class.java)
                mIntent.putExtra(CreateWahanaActivity.ACTIVITY_FROM, "01")
                startActivity(mIntent)
            }

            // filter catogory
            layoutAddContent.spinnerCategory.setOnClickListener {

                val spinnerDialogDataTypes = SpinnerDialog(
                    this@WahanaActivity, listDataCategorySpinner, "Select Item :", R.style.DialogAnimations_SmileWindow
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

    private fun getListCategory(date: String) {

        wahanaViewModel.getCategory(date, date).observe(this, { data ->

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

        wahanaViewModel.getSearchWahana(typeClicked,search,category).observe(this, { data ->

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
            mIntent.putExtra(DetailWahanaActivity.EXTRA_DATA, selectData)
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