package com.pos.lms.kms_pt_pos_indonesia.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pos.lms.kms_pt_pos_indonesia.R
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityHomeBinding
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityMainBinding
import com.pos.lms.kms_pt_pos_indonesia.ui.wahana.WahanaActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onclick()
    }

    private fun onclick() {
        binding.apply {
            button.setOnClickListener {
                val mIntent = Intent(this@HomeActivity, WahanaActivity::class.java)
                startActivity(mIntent)
            }
        }
    }
}