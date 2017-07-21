package com.imeepwni.android.ankowiki.vm

import android.databinding.*
import com.imeepwni.android.ankowiki.model.data.*

/**
 * Create by guofeng on 2017/7/20.
 */
class AnkoViewModel(var anko: Anko) : BaseObservable() {

    fun getAnkoName() = anko.name

    fun doAnkoFunction() {
        anko.function()
    }
}