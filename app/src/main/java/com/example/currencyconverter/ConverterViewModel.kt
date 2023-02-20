package com.example.currencyconverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {
    // USA Dollar
    private val _usd = MutableLiveData<String>()
    val usd: LiveData<String>
        get() = _usd

    // Euro
    private val _eur = MutableLiveData<String>()
    val eur: LiveData<String>
        get() = _eur

    // Iran Rial
    private val _irr = MutableLiveData<String>()
    val irr: LiveData<String>
        get() = _irr

    // Canada Dollar
    private val _cad = MutableLiveData<String>()
    val cad: LiveData<String>
        get() = _cad

    // Great Britain Pound
    private val _gbp = MutableLiveData<String>()
    val gbp: LiveData<String>
        get() = _gbp

    // United Arab Emirates Dirham
    private val _aed = MutableLiveData<String>()
    val aed: LiveData<String>
        get() = _aed

    private val currencyMap: HashMap<String, Double> = HashMap()

    init {
        setCurrencyMap()
    }

    private fun setCurrencyMap() {
        currencyMap["eur"] = 0.9499
        currencyMap["irr"] = 42423.2432
        currencyMap["cad"] = 1.3434
        currencyMap["gbp"] = 0.8157
        currencyMap["aed"] = 3.6701
    }


    fun convert(value: Double, type: String) {
        var usdVal = value
        if (type !== "usd") {
            usdVal = value / currencyMap[type]!!
        }
        if (type !== "usd")
            _usd.value = usdVal.toString()
        if (type !== "eur")
            _eur.value = (usdVal * currencyMap["eur"]!!).toString()
        if (type !== "irr")
            _irr.value = (usdVal * currencyMap["irr"]!!).toString()
        if (type !== "cad")
            _cad.value = (usdVal * currencyMap["cad"]!!).toString()
        if (type !== "gbp")
            _gbp.value = (usdVal * currencyMap["gbp"]!!).toString()
        if (type !== "aed")
            _aed.value = (usdVal * currencyMap["aed"]!!).toString()
    }
}