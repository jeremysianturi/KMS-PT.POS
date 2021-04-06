package com.pos.lms.kms_pt_pos_indonesia.ui.multimedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityMultimediaBinding

class MultimediaActivity : AppCompatActivity() {

    private var binding: ActivityMultimediaBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultimediaBinding.inflate(layoutInflater)
        setContentView(binding?.root)

    }
}