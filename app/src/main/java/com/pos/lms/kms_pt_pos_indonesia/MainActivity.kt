package com.pos.lms.kms_pt_pos_indonesia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pos.lms.kms_pt_pos_indonesia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
// abi ho
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}