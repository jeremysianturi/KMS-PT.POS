package com.pos.lms.kms_pt_pos_indonesia.ui.wahana.createwahana

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import br.com.onimur.handlepathoz.HandlePathOz
import br.com.onimur.handlepathoz.HandlePathOzListener
import br.com.onimur.handlepathoz.model.PathOz
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import com.github.dhaval2404.form_validation.rule.NonEmptyRule
import com.github.dhaval2404.form_validation.validation.FormValidator
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jaiselrahman.filepicker.activity.FilePickerActivity
import com.jaiselrahman.filepicker.config.Configurations
import com.jaiselrahman.filepicker.model.MediaFile
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.data.source.remote.response.SubmitResponse
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityCreateWahanaBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import com.pos.lms.kms_pt_pos_indonesia.helper.CurrentDate
import com.pos.lms.kms_pt_pos_indonesia.helper.URIPathHelper
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailwahana.DetailWahanaActivity
import com.pos.lms.kms_pt_pos_indonesia.utils.PreferenceEntity
import com.pos.lms.kms_pt_pos_indonesia.utils.UserPreference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import org.json.JSONObject
import timber.log.Timber
import java.io.File
import java.net.URI

@AndroidEntryPoint
class CreateWahanaActivity : AppCompatActivity(), View.OnClickListener,HandlePathOzListener.SingleUri {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val ACTIVITY_FROM = "activity_from"
        const val USER_ROLE = "user_role"
        private const val IMAGE_REQ_CODE = 101
        private const val FILE_REQ_CODE = 111
        private const val DATE_PICKER_TAG_START = "DatePickerStart"
        private const val DATE_PICKER_TAG_END = "DatePickerEnd"
    }

    //bottomSheet
    private var bottomSheetDialog: BottomSheetDialog? = null

    private lateinit var binding: ActivityCreateWahanaBinding
    private val createWahanaViewModel: CreateWahanaViewModel by viewModels()

    private lateinit var mPreference: UserPreference
    private lateinit var mPreferenceEntity: PreferenceEntity

    var token: String? = ""
    var owner: String? = ""
    var currentDate: String? = ""


    private var mImage: File? = null
    private var mImageName: String = ""
    private var mFile: File? = null
    private var activityFrom: String? = ""
    private lateinit var handlePathOz: HandlePathOz


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateWahanaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup Actionbar and navigasi up
        val actionbar = supportActionBar
        actionbar?.title = "Add Content"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        mPreference = UserPreference(this)
        mPreferenceEntity = mPreference.getPref()

        bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialog)

        token = mPreferenceEntity.token
        owner = mPreferenceEntity.username
        currentDate = CurrentDate.getToday()

        // get data intent
        activityFrom = intent.getStringExtra(ACTIVITY_FROM)

        // onclick
        binding.btnCreate.setOnClickListener(this)
        binding.tvUploadimage.setOnClickListener(this)
        binding.tvUploadfile.setOnClickListener(this)

        // file
        handlePathOz = HandlePathOz(this, this)

    }

    //////////////////////////////////////     OTHER METHODS   /////////////////////////////////////
    private fun openFile() {
        if (checkSelfPermission()) {
            val intent =
                if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                    Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                } else {
                    Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI)
                }

            intent.apply {
                type = "*/*"
                action = Intent.ACTION_GET_CONTENT
                action = Intent.ACTION_OPEN_DOCUMENT
                addCategory(Intent.CATEGORY_OPENABLE)
                putExtra("return-data", true)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivityForResult(intent, FILE_REQ_CODE)
        }
    }

    private fun checkSelfPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                FILE_REQ_CODE
            )
            return false
        }
        return true
    }

    @FlowPreview
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == FILE_REQ_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFile()
            } else {
                TODO("show Message to the user")
            }
        }
    }
    //////////////////////////////////////     OTHER METHODS   /////////////////////////////////////

    private fun pickPhoto() {
        ImagePicker.with(this)
            .setImageProviderInterceptor { imageProvider -> // Intercept ImageProvider
                Timber.tag("ImagePicker").d("Selected ImageProvider: %s", imageProvider.name)
            }
            .maxResultSize(1080, 1920)
            .compress(1024)
            .start(IMAGE_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {

                Timber.tag("ImagePickerDetail").e("Path:${ImagePicker.getFilePath(data)}")


                when (requestCode) {
                    IMAGE_REQ_CODE -> {
                        val file = ImagePicker.getFile(data)!!
                        val name = ImagePicker.getFilePath(data)
                        mImage = file
                        mImageName = name.toString()
                        binding.tvUploadimage.text = ImagePicker.getFilePath(data)
//                    img_result_photo.setLocalImage(file, false)
//                    tv_name_image_helpdesk.text = ImagePicker.getFilePath(data)
//                    uploadFoto = 1
                    }

                    ImagePicker.RESULT_ERROR -> {
                        Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                    }

                    FILE_REQ_CODE -> {

                        data?.data?.also { it ->
                            handlePathOz.getRealPath(it)

                        }

                    }
                }
            }
            else -> {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestHandlePathOz(pathOz: PathOz, tr: Throwable?) {

        binding.tvUploadfile.text = pathOz.path
        mFile = File(pathOz.path)

        //Handle any Exception (Optional)
        tr?.let {
            Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validationField(): Boolean {
        return FormValidator.getInstance()
            .addField(
                binding.etTitlevalueCreateWahana,
                NonEmptyRule(getString(R.string.ERROR_FIELD_REQUIRED))
            )
            .addField(
                binding.etDescCreateWahana,
                NonEmptyRule(getString(R.string.ERROR_FIELD_REQUIRED))
            )
            .addField(
                binding.tvUploadimage,
                NonEmptyRule(getString(R.string.ERROR_FIELD_REQUIRED))
            )
            .addField(
                binding.tvUploadfile,
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
        content.text = getString(R.string.txt_konfirmasi_content)
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

        binding.progressBar.visibility = View.VISIBLE

        val title = binding.etTitlevalueCreateWahana.text.toString()
        val description = binding.etDescCreateWahana.text.toString()

        // sementara selama retrofit blm bisa upload image "Lebih ke tolol sih "
//        ${com.pos.lms.core.BuildConfig.API_URL}
        AndroidNetworking.upload("https://pos-kms.digitalevent.id/kmsbe/api/home")
            .addHeaders("Authorization", "Bearer $token")
            .addMultipartParameter("knowledge_type", activityFrom)
            .addMultipartParameter("begin_date", currentDate)
            .addMultipartParameter("end_date", "9999-12-31")
            .addMultipartParameter("business_code", "POS")
            .addMultipartParameter("solution", description)
            .addMultipartFile("thumbnail", mImage)
            .addMultipartFile("file[]", mFile)
            .addMultipartParameter("cases", title)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    var responseCoba = response
                    Timber.d("cek response terluar : $responseCoba")

                    if (response?.getBoolean("status") == true) {
                        val message = response.getString("message")
                        val status = response.getBoolean("status")
                        Timber.d("check status post content : $status dan message : $message")
                        val responseSubmit = SubmitResponse(message, status)
                        binding.progressBar.visibility = View.GONE
                        popupInformation()

                    } else {
                        val message = response?.getString("message")
                        val status = response?.getBoolean("status")
                        Timber.d("check status post content : $status dan message : $message")
                        val responseSubmit = SubmitResponse(message.toString(), status!!)
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@CreateWahanaActivity, "Failed", Toast.LENGTH_SHORT)
                            .show()

                    }
                }

                override fun onError(anError: ANError?) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@CreateWahanaActivity, "Error", Toast.LENGTH_SHORT).show()
                    Timber.d("anerror post content : ${anError?.message} dan ${anError?.errorBody}")

                }

            })
    }

    private fun popupInformation() {

        val views = layoutInflater.inflate(R.layout.bottom_sheet_information, null)
        bottomSheetDialog?.setContentView(views)

        val tittle = views.findViewById<TextView>(R.id.tvTittleInfo)
        val content = views.findViewById<TextView>(R.id.tvContentInfo)
        val btnYes = views.findViewById<Button>(R.id.btnInfo)
        val img = views.findViewById<ImageView>(R.id.imgInfo)
        tittle.text = getString(R.string.text_berhasil)
        content.text = getString(R.string.txt_confirm_content)
        Glide.with(this)
            .load(R.drawable.img_confirm)
            .into(img)

        btnYes.text = getString(R.string.text_oke)
        bottomSheetDialog?.show()

        //setOnclick bottomsheet
        btnYes.setOnClickListener {
            bottomSheetDialog?.dismiss()
            finish()
        }

    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_create -> {
                if (validationField()) {
                    isValidField()
                }
            }

            R.id.tv_uploadimage -> {
                pickPhoto()
            }

            R.id.tv_uploadfile -> {
//                pickFile()
                openFile()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}