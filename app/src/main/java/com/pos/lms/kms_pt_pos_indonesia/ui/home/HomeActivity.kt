package com.pos.lms.kms_pt_pos_indonesia.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityHomeBinding
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityMainBinding
import com.pos.lms.kms_pt_pos_indonesia.ui.digilab.DigilabActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.inbox.InboxActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.login.LoginActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.multimedia.MultimediaActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.WahanaActivity
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailArticle.DetailArticleActivity
import com.pos.lms.kms_pt_pos_indonesia.utils.PreferenceEntity
import com.pos.lms.kms_pt_pos_indonesia.utils.UserPreference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import kotlin.system.exitProcess

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var mPreference: UserPreference
    private lateinit var mPreferenceEntity: PreferenceEntity

    //bottomSheet
    private var bottomSheetDialog: BottomSheetDialog? = null

    private var doubleBackToExitPressedOnce = false
    private lateinit var type : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPreference = UserPreference(this)
        mPreferenceEntity = mPreference.getPref()

        bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialog)

        Timber.d("ini token : ${mPreferenceEntity.token} SAMPESINI")

        onclick()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.logout){
            popupConfirm()
            return true
        } else if (id == R.id.inbox){
            goToInbox()
            return true
        }  else {
           return super.onOptionsItemSelected(item)
        }
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
        content.text = getString(R.string.txt_logout_message)
        btnNo.text = getString(R.string.text_batal)
        btnYes.text = getString(R.string.txt_logout)
        bottomSheetDialog?.show()

        //onclick bottomsheet
        // positive button
        btnYes.setOnClickListener {
            logout()
            bottomSheetDialog?.dismiss()
        }
        // negative button
        btnNo.setOnClickListener {
            bottomSheetDialog?.dismiss()
        }

    }

    private fun logout() {
        mPreferenceEntity.isLogin = false
        mPreference.setPref(mPreferenceEntity)
        val mIntent = Intent(this, LoginActivity::class.java)
        startActivity(mIntent)
        finish()

    }


    private fun onclick() {
        binding.apply {

            // wahana
            buttonWahana.setOnClickListener {
                type = "01"
                val mIntent = Intent(this@HomeActivity, WahanaActivity::class.java)
                mIntent.putExtra("type",type)
                startActivity(mIntent)
            }

            // digilab
            buttonDigilab.setOnClickListener {
                type = "02"
                val mIntent = Intent(this@HomeActivity, DigilabActivity::class.java)
                mIntent.putExtra("type",type)
                startActivity(mIntent)
            }

            // multimedia
            buttonMultimedia.setOnClickListener {
                type = "03"
                val mIntent = Intent(this@HomeActivity, MultimediaActivity::class.java)
                mIntent.putExtra("type",type)
                startActivity(mIntent)
            }
        }
    }

    private fun goToInbox(){
        val mIntent = Intent(this@HomeActivity, InboxActivity::class.java)
        startActivity(mIntent)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
            exitProcess(0)
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Klik sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
//        Toasty.warning(this, "Klik sekali lagi untuk keluar", Toasty.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(
            { doubleBackToExitPressedOnce = false },
            2000
        ) // review
    }
}