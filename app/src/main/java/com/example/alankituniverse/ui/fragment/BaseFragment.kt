package com.example.alankituniverse.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class BaseFragment<B : ViewDataBinding>(private val layout: Int) : Fragment() {

    private var _binding: B? = null
    val binding: B
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(layoutInflater, layout, container, false)
        return _binding?.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}