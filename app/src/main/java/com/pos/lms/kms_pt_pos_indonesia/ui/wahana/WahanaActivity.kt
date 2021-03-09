package com.pos.lms.kms_pt_pos_indonesia.ui.wahana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityHomeBinding
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityWahanaBinding
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.article.ArtikelActivity

class WahanaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWahanaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWahanaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // onclick
        toolbarSetup()
        onclick()

    }

    private fun onclick() {
        binding.apply {
            tvArticle.setOnClickListener {
                val mIntent = Intent(this@WahanaActivity, ArtikelActivity::class.java)
                startActivity(mIntent)
            }
            tvKaryaTulis.setOnClickListener {
                Toast.makeText(this@WahanaActivity, "fitur not ready", Toast.LENGTH_SHORT).show()
            }
            tvDiseminasi.setOnClickListener {
                Toast.makeText(this@WahanaActivity, "fitur not ready", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun toolbarSetup() {
        binding.toolbar.apply {
            ivNavigationBack.setOnClickListener {
                onBackPressed()
            }
            tvTittle1.text = getString(R.string.tv_infinites)
            tvTittle2.text = getString(R.string.tv_wahana)
        }

    }
}