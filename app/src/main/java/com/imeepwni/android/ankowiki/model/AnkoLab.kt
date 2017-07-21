package com.imeepwni.android.ankowiki.model

import android.app.*
import android.content.*
import android.support.annotation.*
import android.text.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.*
import org.jetbrains.anko.coroutines.experimental.*
import java.net.*


/**
 * Create by guofeng on 2017/7/20.
 */
class AnkoLab(val activity: Activity) {
    fun getAnkoList() = arrayListOf<Anko>().apply {
        with(activity) {
            add(Anko("toast") {
                toast("Hi there!")
            })
            add(Anko("longToast") {
                longToast("Wow, such a duration")
            })
            add(Anko("alerts") {
                alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
                    yesButton { toast("Oh…") }
                    noButton { toast("you clicked no!") }
                }.show()
            })
            add(Anko("appCompat") {
                alert {
                    customView {
                        val editText = tintedEditText()
                        yesButton { toast("${editText.text}") }
                    }
                }.show()
            })
            add(Anko("selectors") {
                val list = arrayListOf("Russia", "USA", "Japan", "Australia")
                selector("Where are you from?", list) {
                    _, i ->
                    toast("So you're living in ${list[i]}")
                }
            })
            add(Anko("Progress dialogs") {
                progressDialog("Please wait a bit...", "Fetching data") {
                    setButton(DialogInterface.BUTTON_POSITIVE, "ok") {
                        _, _ ->
                        toast(" ok")
                    }
                    setButton(DialogInterface.BUTTON_NEGATIVE, "cancel") {
                        _, _ ->
                        cancel()
                    }
                }
            })
            add(Anko("Make a call") {
                alert {
                    customView {
                        val phone = tintedEditText {
                            inputType = InputType.TYPE_CLASS_PHONE
                        }
                        yesButton {
                            makeCall(phone.text.toString())
                        }
                    }
                }.show()
            })
            add(Anko("sendSMS") {
                alert {
                    customView {
                        val text = tintedEditText {
                            inputType = InputType.TYPE_CLASS_TEXT
                        }
                        yesButton {
                            activity.sendSMS("18566235740", text.text.toString())
                        }
                    }
                }.show()
            })
            add(Anko("Browse The web") {
                browse("https://www.baidu.com")
            })
            add(Anko("share some text") {
                share("text", "subject")
            })
            add(Anko("SendAnEmail") {
                email("email", "subject", "haha")
            })
            add(Anko("asReference") {
                val ref: Ref<Activity> = this.asReference()

                doAsync {
                    val string = URL("https://www.baidu.com").readText()
                    uiThread {
                        ref.run {
                            toast(string)
                        }
                    }
                }
            })
        }
    }
}
