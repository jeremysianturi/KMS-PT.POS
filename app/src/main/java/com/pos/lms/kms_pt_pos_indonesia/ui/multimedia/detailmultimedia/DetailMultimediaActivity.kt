package com.pos.lms.kms_pt_pos_indonesia.ui.multimedia.detailmultimedia

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.github.dhaval2404.form_validation.rule.NonEmptyRule
import com.github.dhaval2404.form_validation.validation.FormValidator
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.data.Resource
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.post.MultimediaCommentCreate
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityDetailMultimediaBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import com.pos.lms.kms_pt_pos_indonesia.helper.CurrentDate
import com.pos.lms.kms_pt_pos_indonesia.helper.Debounce.onThrottledClick
import com.pos.lms.kms_pt_pos_indonesia.helper.loadImage
import com.pos.lms.kms_pt_pos_indonesia.ui.multimedia.detailmultimedia.detailmultimedia.MultimediaCommentAdapter
import com.pos.lms.kms_pt_pos_indonesia.ui.multimedia.detailmultimedia.detailmultimedia.MultimediaCommentViewModel
import com.pos.lms.kms_pt_pos_indonesia.util.PdfViewActivity
import com.pos.lms.kms_pt_pos_indonesia.util.VideoPlayerActivity
import com.pos.lms.kms_pt_pos_indonesia.util.dialog.SimpleDialog
import com.pos.lms.kms_pt_pos_indonesia.utils.ErrorMessageSplit
import com.pos.lms.kms_pt_pos_indonesia.utils.PreferenceEntity
import com.pos.lms.kms_pt_pos_indonesia.utils.UserPreference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.json.JSONObject
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailMultimediaActivity : AppCompatActivity() {

    private val tag = DetailMultimediaActivity::class.java.simpleName
    private lateinit var binding: ActivityDetailMultimediaBinding
    private lateinit var adapter : MultimediaCommentAdapter
    private val multimediaCommentViewModel : MultimediaCommentViewModel by viewModels()

    private lateinit var mPreference: UserPreference
    private lateinit var mPreferenceEntity: PreferenceEntity

    //bottomSheet
    private var bottomSheetDialog: BottomSheetDialog? = null

    private lateinit var convertedString : String
    var currentDate: String? = ""
    var time: String? = ""
    var dataMultimedia: Multimedia? = null
    private lateinit var order : String
    private var objidentifier : Int = 0

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val PARENT_DATA = "materi"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMultimediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //biar keyboard ga lgsg popup
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialog)

        // user preferences
        mPreference = UserPreference(this)
        mPreferenceEntity = mPreference.getPref()

        // get current date
        currentDate = CurrentDate.getToday()

        dataMultimedia = intent.getParcelableExtra<Multimedia>(DetailMultimediaActivity.EXTRA_DATA)
        val idKnowledge = dataMultimedia?.idKnowledge
        val buscd = dataMultimedia?.buscd
        val fileType = dataMultimedia?.address?.takeLast(4)
        Timber.tag(tag).d("FileType : $fileType")

        binding.clMultimedia1.setOnClickListener {

            if (fileType == ".mp4") {
                val mIntent = Intent(this, VideoPlayerActivity::class.java)
                mIntent.putExtra(VideoPlayerActivity.EXTRA_DATA, dataMultimedia)
                mIntent.putExtra(VideoPlayerActivity.PARENT_DATA, DetailMultimediaActivity.PARENT_DATA)
                startActivity(mIntent)
            } else {
                val mIntent = Intent(this, PdfViewActivity::class.java)
                mIntent.putExtra(PdfViewActivity.EXTRA_DATA, dataMultimedia)
                mIntent.putExtra(PdfViewActivity.PARENT_DATA, DetailMultimediaActivity.PARENT_DATA)
                startActivity(mIntent)
            }

        }

        //setup Actionbar and navigasi up
        val actionbar = supportActionBar
        actionbar?.title = "Detail Materi"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        // get and post viewers
        postViewers(idKnowledge,buscd)

        //function
        collectData(dataMultimedia)

        // method comment
        order = "desc"
        objidentifier = dataMultimedia!!.objectIndentifier ?: 0
        currentDate?.let { setupObserver(order,idKnowledge!!, it,it) }
        buildList()

        // post comment
        binding.tvPost.onThrottledClick {
            if (validationField()) {
                isValidField()
            } else {
                Toast.makeText(this, "Lengkapi data terlebih dahulu !", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun collectData(materi: Multimedia?) {
        if (materi != null) {

            binding.ivContentDetailmultimedia.loadImage(this,materi.thumbnail)
            binding.tvContenttitle.text = materi.cases
            binding.tvNameDetailmultimedia.text = materi.creator


            // JANGAN LUPA CONVERT HTML TERUS CONVERT HTML LAGI COBAIN
            Timber.d("check value solution materi : ${materi.solution}")

//            var edited : String
//            edited = materi.solution.replace("\\n","<br>").replace("\\\\r","\r")
//            Timber.d("coba coba coba : $edited")

            binding.tvMultimediaDesc.text =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(
                        materi.solution
                    .replace("\\n","").replace("\\r","")
                        , Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(
                        materi.solution
                    .replace("\\n","").replace("\\r","")
                    )
                }

        }
    }

    private fun convertToString(htmlFormat : String) : String{

        val oldValue = "${"\\"}\\r"
        val newValue = "\\r"
        Timber.d("old value : $oldValue dan new value : $newValue")
        return htmlFormat.replace(oldValue, newValue)

    }

    private fun setupObserver(order: String, knowledgeMultimedia: Int, beginDate : String, endDate : String) {
        multimediaCommentViewModel.getMultimediaComment(order,knowledgeMultimedia,beginDate,endDate).observe(this, { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressbarCreatemultimedia.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressbarCreatemultimedia.visibility = View.GONE
                        adapter.setData(data.data)
                        Timber.tag(tag).d("observer_multimediacomment_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressbarCreatemultimedia.visibility = View.GONE
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
        adapter = MultimediaCommentAdapter()
        binding.rvMultimediaComment.setHasFixedSize(true)
        binding.rvMultimediaComment.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMultimediaComment.adapter = adapter

        binding.rvMultimediaComment.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun validationField(): Boolean {
        return FormValidator.getInstance()
            .addField(
                binding.etCommentMultimedia,
                NonEmptyRule(getString(R.string.ERROR_FIELD_REQUIRED))
            )
            .validate()
    }

    private fun isValidField() {
        popupConfirm()
    }

    private fun popupConfirm() {
        //init bottomSheet
        val views = layoutInflater.inflate(R.layout.bottom_sheet_confirmation, null)
        bottomSheetDialog?.setContentView(views)
        val tittle = views.findViewById<TextView>(R.id.tvDialogTittle)
        val content = views.findViewById<TextView>(R.id.tvDialogMessage)
        val btnNo = views.findViewById<Button>(R.id.btnDialogNo)
        val btnYes = views.findViewById<Button>(R.id.btnDialogYes)

        tittle.text = getString(R.string.txt_konfirmasi)
        content.text = getString(R.string.txt_posting_comment)
        btnNo.text = getString(R.string.text_batal)
        btnYes.text = getString(R.string.text_oke)
        bottomSheetDialog?.show()

        //onclick bottomsheet
        // positive button
        btnYes.setOnClickListener {
            submitData()
            bottomSheetDialog?.dismiss()
        }
        // negative button
        btnNo.setOnClickListener {
            bottomSheetDialog?.dismiss()
        }

    }

    private fun submitData() {

        val beginDate = currentDate ?: ""
        val endDate = "9999-12-31"
        val knowledge = dataMultimedia!!.objectIndentifier
        val buscd = "POS"
        val comment = binding.etCommentMultimedia.text.toString()

        val createMultimediaComment = MultimediaCommentCreate(
            endDate = endDate,
            knowledge = knowledge,
            beginDate = beginDate,
            buscd = buscd,
            comment = comment
        )

        multimediaCommentViewModel.createMultimediaComment(createMultimediaComment).observe(this, { data ->
            if (data != null) {
                when (data) {
                    is Resource.Loading -> {
                        binding.progressbarCreatemultimedia.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        val message = data.message.toString()
                        Timber.d("messageUpdate $message")

                        val msg = "Create Success"
                        binding.progressbarCreatemultimedia.visibility = View.GONE
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                        binding.etCommentMultimedia.text.clear()
                        currentDate?.let { setupObserver(order,objidentifier, it,it) }
                        buildList()

//                        popupInformation()

                    }

                    is Resource.Error -> {
                        val massage = getString(R.string.something_wrong)
                        binding.progressbarCreatemultimedia.visibility = View.GONE
                        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }

    private fun postViewers(idKnowledge : Int?, buscd : String?){
        val urlPostViewers = "https://pos-kms.digitalevent.id/kmsbe/api/views/log"

        val body = JSONObject()
        body.put("knowlegde_id", idKnowledge)
        body.put("business_code", buscd)

        AndroidNetworking.post(urlPostViewers)
            .addHeaders("Authorization", "Bearer ${mPreferenceEntity.token}")
            .setPriority(Priority.MEDIUM)
            .addJSONObjectBody(body)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    val responseVar = response?.get("message")
                    val status = response?.get("status").toString()

                    if (status.equals("true")) {
                        Timber.d("berhasil post viewers")
                    } else {
                        Timber.d("gagal post viewers : $status")
                    }

                    getViewers(idKnowledge)

                }

                override fun onError(anError: ANError?) {
                    Timber.d("on Error post Viewers : ${anError?.message}")
                    Toast.makeText(this@DetailMultimediaActivity, "Something Wrong!", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun getViewers(idKnowledge : Int?){
        val urlGetViewers = "https://pos-kms.digitalevent.id/kmsbe/api/views?id_knowledge=$idKnowledge"
        Timber.d("url get viewers : $urlGetViewers")
        AndroidNetworking.get(urlGetViewers)
            .addHeaders("Authorization", "Bearer ${mPreferenceEntity.token}")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    val jsonObject = response?.getJSONObject("views")
                    val count = jsonObject?.getInt("COUNT")

                    // set count view to ui
                    binding.tvTotalviewMultimedia.text = "${count.toString()} views"

                }

                override fun onError(anError: ANError?) {

                    Timber.d("on Error get Viewers : ${anError?.message}")
                    Toast.makeText(this@DetailMultimediaActivity, "Something Wrong!", Toast.LENGTH_SHORT).show()

                }

            })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}