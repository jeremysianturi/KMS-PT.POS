package com.pos.lms.kms_pt_pos_indonesia.util

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityPdfViewBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Wahana
import timber.log.Timber


class PdfViewActivity : AppCompatActivity() {

    private val tag = PdfViewActivity::class.java.simpleName

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val PARENT_DATA = "parent_data"
        private const val PDF_SELECTION_CODE = 99

    }

    private val parentId = "materi"
    private lateinit var binding: ActivityPdfViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var url = ""
        val parentIdExtra = intent.getStringExtra(PARENT_DATA)
        val extraData = intent.getParcelableExtra<Wahana>(EXTRA_DATA)

        Timber.d("check value extraData : $extraData dan $parentIdExtra")

        Timber.tag(tag).d("CHECK_DATA_TYPE : ${VideoPlayerActivity.PARENT_DATA} ")

        url = extraData?.address.toString()


        binding.apply {
            webview.settings.javaScriptEnabled = true
            webview.settings.pluginState = WebSettings.PluginState.ON
            webview.settings.setSupportZoom(true)
        }
        val openUurl = "http://docs.google.com/gview?embedded=true&url=$url"
        Timber.d("check url open pdf : $openUurl")
        showPdf(openUurl)

        //setup Actionbar and navigasi up
        val actionbar = supportActionBar
        actionbar?.title = "View PDF"
        actionbar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun showPdf(openUurl: String) {

//        binding.webview.settings.loadWithOverviewMode = true
//        binding.webview.settings.javaScriptEnabled = true
//        binding.webview.loadUrl(openUurl)
        //        binding.webview.webViewClient = WebViewClient()


        // emang di comment
//        binding.webview.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                view.loadUrl(url)
//                return true
//            }
//        }
        // emang di comment


        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(openUurl))
        startActivity(browserIntent)
        binding.progressBar3.visibility = View.GONE
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.progressBar3.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}