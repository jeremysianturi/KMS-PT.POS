package com.pos.lms.kms_pt_pos_indonesia.ui.wahana.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.layoutToolbar.apply {
            ivNavigationBack.setOnClickListener {
                onBackPressed()
            }
            tvTittle1.text = getString(R.string.tv_infinites)
            tvTittle2.text = getString(R.string.tv_artikel)
        }

    }
}