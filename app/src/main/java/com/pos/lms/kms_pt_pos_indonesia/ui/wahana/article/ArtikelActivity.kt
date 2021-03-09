package com.pos.lms.kms_pt_pos_indonesia.ui.wahana.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityArtikelBinding

class ArtikelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //method
        toolbarSetup()
    }

    private fun toolbarSetup() {
        binding.lntoolbar.
    }
}