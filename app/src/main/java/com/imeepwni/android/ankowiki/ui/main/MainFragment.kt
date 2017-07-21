package com.imeepwni.android.ankowiki.ui.main

import android.databinding.*
import android.os.*
import android.support.v4.app.*
import android.support.v7.widget.*
import android.view.*
import com.imeepwni.android.ankowiki.*
import com.imeepwni.android.ankowiki.databinding.*
import com.imeepwni.android.ankowiki.model.*
import com.imeepwni.android.ankowiki.vm.*
import org.jetbrains.anko.*

class MainFragment : Fragment(),
        AnkoLogger {

    val ankoList by lazy {
        AnkoLab(activity).getAnkoList()
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
            binding.viewModel = AnkoViewModel(anko)
            binding.executePendingBindings()
        }
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
