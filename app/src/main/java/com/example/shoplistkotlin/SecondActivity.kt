package com.example.shoplistkotlin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.shoplistkotlin.databinding.ActivitySecondBinding
import com.example.shoplistkotlin.presentation.main.MainActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkData()

        setupListeners()

    }

    private fun checkData() {
        if (intent != null) {
            binding.etName.setText(intent.getStringExtra(MainActivity.EDIT_NAME))
            binding.etCount.setText(intent.getStringExtra(MainActivity.EDIT_COUNT).toString())
        }
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                putExtra(NAME, binding.etName.text.toString())
                putExtra(COUNT, binding.etCount.text.toString())
                startActivity(this)
            }
            finish()
        }
    }

    companion object {
        const val NAME = "name"
        const val COUNT = "count"
    }
}