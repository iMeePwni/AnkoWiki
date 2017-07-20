package com.imeepwni.android.ankowiki.ui

import android.support.v4.app.*
import com.imeepwni.android.ankowiki.app.*

class MainActivity : SingleFragmentActivity() {
    override fun getFragment(): Fragment {
        return MainFragment.newInstance()
    }
}
