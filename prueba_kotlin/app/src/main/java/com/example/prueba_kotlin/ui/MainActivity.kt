package com.example.prueba_kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.prueba_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var data : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClear.setOnClickListener( View.OnClickListener {
            binding.txtResult.setText("")
            data = ""
        })

        binding.buttonGo.setOnClickListener( View.OnClickListener {
            val intent = Intent(this, ActivityRecall::class.java)
            startActivity(intent)
        })

        binding.button1.setOnClickListener {
            data+= 1
            binding.txtResult.setText(data)
        }

        binding.button2.setOnClickListener {
            data+= 2
            binding.txtResult.setText(data)
        }

        binding.button3.setOnClickListener {
            data+= 3
            binding.txtResult.setText(data)
        }

        binding.button4.setOnClickListener {
            data+= 4
            binding.txtResult.setText(data)
        }

        binding.button5.setOnClickListener {
            data+= 5
            binding.txtResult.setText(data)
        }

        binding.button6.setOnClickListener {
            data+= 6
            binding.txtResult.setText(data)
        }

        binding.button7.setOnClickListener {
            data+= 7
            binding.txtResult.setText(data)
        }

        binding.button8.setOnClickListener {
            data+= 8
            binding.txtResult.setText(data)
        }

        binding.button9.setOnClickListener {
            data+= 9
            binding.txtResult.setText(data)
        }

        binding.button0.setOnClickListener {
            data+= 0
            binding.txtResult.setText(data)
        }
    }

}