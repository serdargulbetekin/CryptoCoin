package com.example.cryptocoinloodos.modules.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinloodos.databinding.ActivityLoginRegisterBinding
import com.example.cryptocoinloodos.modules.crypto.CryptoActivity

class LoginRegisterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginRegisterBinding.inflate(layoutInflater) }
    private val viewModelFactory by lazy { LoginRegisterActivityViewModelFactory() }
    private lateinit var viewModelRegister: LoginRegisterActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModelRegister =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(LoginRegisterActivityViewModel::class.java)

        viewModelRegister.firebaseUser.observe(this, Observer {
            if (it != null) {
                startActivity(Intent(this, CryptoActivity::class.java))
            }
        })

        binding.apply {
            toolbar.show("LOGIN")
            textViewLogin.setOnClickListener {
                viewModelRegister.login(editTextEmail.text, editTextPassword.text)
            }

            textViewRegister.setOnClickListener {
                viewModelRegister.register(editTextEmail.text, editTextPassword.text)
            }
        }
    }
}