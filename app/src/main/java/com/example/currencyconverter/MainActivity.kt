package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ConverterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        // Set listeners
        setTextChangedListener(binding.usdEditText, binding.usdTextField, "usd")
        setTextChangedListener(binding.eurEditText, binding.eurTextField, "eur")
        setTextChangedListener(binding.irrEditText, binding.irrTextField, "irr")
        setTextChangedListener(binding.cadEditText, binding.cadTextField, "cad")
        setTextChangedListener(binding.gbpEditText, binding.gbpTextField, "gbp")
        setTextChangedListener(binding.aedEditText, binding.aedTextField, "aed")
    }


    private fun setTextChangedListener(
        editText: TextInputEditText,
        textField: TextInputLayout,
        type: String
    ) {
        editText.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    try {
                        val value = editText.text.toString().toDouble()
                        if (value < 0) throw Exception("Negative value")
                        setErrorTextField(false, textField)
                    } catch (e: Exception) {
                        setErrorTextField(true, textField)
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    if (editText.hasFocus()) {
                        try {
                            val value = s.toString().toDouble()
                            if (value < 0) throw Exception("Negative value")
                            viewModel.convert(value, type)
                        } catch (e: Exception) {
                            Log.d("CONVERT_ERROR", e.toString())
                        }
                    }
                }
            }
        )
    }

    private fun setErrorTextField(error: Boolean, textField: TextInputLayout) {
        if (error) {
            textField.isErrorEnabled = true
            textField.error = getString(R.string.error)
        } else {
            textField.isErrorEnabled = false
        }
    }

}