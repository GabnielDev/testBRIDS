package com.example.testbri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.activity.viewModels
import com.example.testbri.databinding.ActivityMainBinding
import com.example.testbri.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            val name = binding.edtName.text.toString().trim()
            val address = binding.edtAddress.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()

            if (name.isEmpty() || address.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show()
            } else if (name.length > 10) {
                Toast.makeText(this, "Nama tidak boleh lebih dari 10", Toast.LENGTH_SHORT).show();
            } else if (address.length > 30) {
                Toast.makeText(this, "Alamat tidak boleh lebih dari 30", Toast.LENGTH_SHORT).show()
            } else {
                postKandidat(
                    name, address, email)
                postId()
            }
        }

    }

    private fun postId() {
        val map = HashMap<String, Any>()
        map["nid"] = 10
        map["officeId"] = 20

        mainViewModel.postKandidat(map).observe(this, {
            if (it.success == true) {
                Log.e("idData", "$it")
            } else {
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun postKandidat(name: String, address: String, email: String) {
        val map = HashMap<String, Any>()
        map["name"] = name
        map["address"] = address
        map["email"] = email

        mainViewModel.postKandidat(map).observe(this, {
            if (it.success == true) {
                Log.e("kandidatData", "$it")
                Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
        })

    }


}