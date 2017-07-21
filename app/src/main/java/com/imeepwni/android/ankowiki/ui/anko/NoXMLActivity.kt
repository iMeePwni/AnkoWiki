package com.imeepwni.android.ankowiki.ui.anko

import android.os.*
import android.support.v7.app.*
import org.jetbrains.anko.*

/**
 * Create by guofeng on 2017/7/21.
 */
class NoXMLActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        verticalLayout {
            padding = dip(30)
            editText {
                hint = "Name"
                textSize = 24f
            }
            editText {
                hint = "Password"
                textSize = 24f
            }
            button {
                textSize = 26f
            }
        }
    }
}