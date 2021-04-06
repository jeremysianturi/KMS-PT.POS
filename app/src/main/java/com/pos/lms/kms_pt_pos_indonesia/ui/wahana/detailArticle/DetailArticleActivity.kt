package com.pos.lms.kms_pt_pos_indonesia.ui.wahana.detailArticle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityArtikelBinding
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}