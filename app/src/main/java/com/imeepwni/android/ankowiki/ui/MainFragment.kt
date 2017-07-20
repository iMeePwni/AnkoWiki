package com.imeepwni.android.ankowiki.ui

import android.content.*
import android.databinding.*
import android.os.*
import android.support.v4.app.*
import android.support.v7.widget.*
import android.text.*
import android.view.*
import com.imeepwni.android.ankowiki.*
import com.imeepwni.android.ankowiki.databinding.*
import com.imeepwni.android.ankowiki.model.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.*
import org.jetbrains.anko.support.v4.*

class MainFragment : Fragment(),
        AnkoLogger {

    val ankoList by lazy {
        arrayListOf<Anko>().apply {
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
                        tintedEditText {
                            inputType = InputType.TYPE_CLASS_PHONE
                            yesButton {

                                makeCall(this.text.toString())
                            }
                        }
                    }
                }.show()
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
        with(binding.recyclerView) {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
            adapter = AnkoAdapter()
        }
        return binding.root
    }

    inner class AnkoAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding: LayoutListItemBinding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.context), R.layout.layout_list_item, parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(ankoList[position])
        }

        override fun getItemCount() = ankoList.size
    }

    inner class ViewHolder(val binding: LayoutListItemBinding) : RecyclerView.ViewHolder(binding.button) {
        fun bind(anko: Anko) {
            (binding.button).run {
                text = anko.name
                setOnClickListener { anko.function() }
            }
        }
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
