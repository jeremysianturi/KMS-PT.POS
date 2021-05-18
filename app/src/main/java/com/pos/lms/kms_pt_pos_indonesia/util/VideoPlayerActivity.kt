package com.pos.lms.kms_pt_pos_indonesia.util

import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityVideoPlayerBinding
import com.pos.lms.kms_pt_pos_indonesia.databinding.ContentVideoPlayerBinding
import com.pos.lms.kms_pt_pos_indonesia.domain.model.Multimedia
import com.pos.lms.kms_pt_pos_indonesia.utils.PreferenceEntity
import com.pos.lms.kms_pt_pos_indonesia.utils.UserPreference
import timber.log.Timber

class VideoPlayerActivity : AppCompatActivity(), Player.EventListener {

    private val TAG = VideoPlayerActivity::class.java.simpleName

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val PARENT_DATA = "parent_data"
        private const val PDF_SELECTION_CODE = 99
        const val POTRAIT_HEIGHT = 750

    }

    private val parentId = "materi"

    private var flagFullScreen: Boolean = false

    private var binding: ActivityVideoPlayerBinding? = null

    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0

    private var mp4Url: String? = ""
    private lateinit var mPreference: UserPreference
    private lateinit var mPreferenceEntity: PreferenceEntity

    private var binding2: ContentVideoPlayerBinding? = null

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(this, "exoplayer-sample")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityVideoPlayerBinding.inflate(layoutInflater).also { binding = it }
        setContentView(binding!!.root)

        //setup Actionbar and navigasi up
        val actionbar = supportActionBar
        actionbar?.title = "Video Materi"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val parentIdExtra = intent.getStringExtra(PdfViewActivity.PARENT_DATA)
        val extraData = intent.getParcelableExtra<Multimedia>(EXTRA_DATA)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // video player
        mPreference = UserPreference(this)
        mPreferenceEntity = mPreference.getPref()

        binding2 = ContentVideoPlayerBinding.inflate(layoutInflater)

        binding2!!.exoFullscreen.setOnClickListener {
            Toast.makeText(this, "full_screen_click", Toast.LENGTH_SHORT).show()
            Timber.tag(TAG).d("full_screen_click")
        }

        var materiId = 0

        var videoUrl = extraData?.address.toString()

        mp4Url = videoUrl
        Timber.tag(TAG).d("urlVideo : $mp4Url")
        binding2 = ContentVideoPlayerBinding.inflate(layoutInflater)

        val fullScreen = findViewById<ImageButton>(R.id.exo_fullscreen)
        val exoPlayer = findViewById<View>(R.id.exoplayerView)
        var params = exoPlayer.layoutParams
        params.height = POTRAIT_HEIGHT

        fullScreen.setOnClickListener {
            if (flagFullScreen) {
                // set icon fullscreen enter
                fullScreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.exo_ic_fullscreen_enter
                    )
                )
                // set orientation lanscape
                actionbar?.show()
                params.height = POTRAIT_HEIGHT
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                flagFullScreen = false
            } else {
                // set icon fullscreen exit
                fullScreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.exo_ic_fullscreen_exit
                    )
                )
                // set orientation lanscape
                actionbar?.hide()
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                params.height = ViewGroup.LayoutParams.MATCH_PARENT

                flagFullScreen = true
            }

        }

    }


    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }


    private fun initializePlayer() {
        simpleExoplayer = SimpleExoPlayer.Builder(this).build()
//        val randomUrl = urlList.random()
        preparePlayer(mp4Url.toString(), "default")
        simpleExoplayer.also { binding?.exoplayerView?.player = it }
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }

    private fun buildMediaSource(uri: Uri, type: String): MediaSource {
        return if (type == "dash") {
            DashMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        } else {
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        }
    }

    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }

    override fun onPlayerError(error: ExoPlaybackException) {
        // handle error
        Toast.makeText(
            this,
            "Gagal memutar video, Periksa koneksi internet anda atau coba beberapa saat lagi",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_BUFFERING)
            binding?.progressBar?.visibility = View.VISIBLE
        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED)
            binding?.progressBar?.visibility = View.INVISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}