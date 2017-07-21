package com.imeepwni.android.ankowiki.model.repository

import android.app.*
import android.content.*
import android.text.*
import android.widget.*
import com.imeepwni.android.ankowiki.model.data.*
import com.imeepwni.android.ankowiki.model.db.*
import com.imeepwni.android.ankowiki.ui.anko.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.*
import org.jetbrains.anko.coroutines.experimental.*
import org.jetbrains.anko.db.*
import java.net.*


/**
 * Create by guofeng on 2017/7/20.
 */
class AnkoLab(val activity: Activity) : AnkoLogger {

    val dbOpenHelper = DBOpenHelper.instance

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
            add(Anko("bg") {
                bg {
                    val string = URL("https://www.baidu.com").readText()
                    info { string }
                }
            })
            add(Anko("GoToNoXML") {
                startActivity<NoXMLActivity>()
            })
            add(Anko("db add") {
                alert {
                    title = "增加User"
                    customView {
                        var name: String? = null
                        var age: Int? = null
                        var sex: String? = null
                        verticalLayout {
                            padding = dip(16)
                            editText {
                                hint = "姓名"
                                addTextChangedListener(object : TextWatcher {
                                    override fun afterTextChanged(s: Editable?) {
                                        name = s.toString()
                                    }

                                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                })
                            }
                            editText {
                                inputType = InputType.TYPE_CLASS_NUMBER
                                hint = "年龄"
                                addTextChangedListener(object : TextWatcher {
                                    override fun afterTextChanged(s: Editable?) {
                                        age = s.toString().toInt()
                                    }

                                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                })
                            }
                            radioGroup {
                                orientation = LinearLayout.HORIZONTAL
                                radioButton().apply {
                                    text = "男"
                                    setOnClickListener { sex = text.toString() }
                                }
                                radioButton().apply {
                                    text = "女"
                                    setOnClickListener { sex = text.toString() }
                                }
                                radioButton().apply {
                                    text = "未知"
                                    setOnClickListener { sex = text.toString() }
                                }
                            }
                        }
                        yesButton {
                            if (name==null || age==null || sex==null) {
                                toast("用户信息不能为空")
                                return@yesButton
                            }
                            dbOpenHelper.use {
                                val values = ContentValues().apply {
                                    put(User.NAME, name)
                                    put(User.AGE, age)
                                    put(User.SEX, sex)
                                }
                                insert(User.TABLE_NAME, null, values)
                            }
                        }
                    }
                }.show()
            })
            add(Anko("query all users") {
                dbOpenHelper.use {
                    select(User.TABLE_NAME).parseList(classParser<User>()).forEach {
                        info { it }
                    }
                }
            })
            add(Anko("updating value") {
                alert {
                    title = "更改用户年龄"
                    customView {
                        var name: String? = null
                        var age: Int? = null
                        verticalLayout {
                            padding = dip(16)
                            editText {
                                hint = "姓名"
                                addTextChangedListener(object : TextWatcher {
                                    override fun afterTextChanged(s: Editable?) {
                                        name = s.toString()
                                    }

                                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                })
                            }
                            editText {
                                hint = "年龄"
                                inputType = InputType.TYPE_CLASS_NUMBER
                                addTextChangedListener(object : TextWatcher {
                                    override fun afterTextChanged(s: Editable?) {
                                        age = s.toString().toInt()
                                    }

                                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                })
                            }
                        }
                        yesButton {
                            if (name == null || age == null || age!! < 0) {
                                toast("姓名或年龄不合法")
                                return@yesButton
                            }
                            dbOpenHelper.use {
                                update(User.TABLE_NAME, User.AGE to age)
                                        .whereSimple("${User.NAME} = ?", name!!)
                                        .exec()
                            }
                        }
                    }
                }.show()
            })
            add(Anko("transaction") {
                toast("transaction{}用于进行SQLite transaction操作")
            })
        }
    }
}
