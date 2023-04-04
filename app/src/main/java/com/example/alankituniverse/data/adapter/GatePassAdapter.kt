package com.example.alankituniverse.data.adapter

import com.example.alankituniverse.R
import com.example.alankituniverse.data.response.GatePass
import com.example.alankituniverse.databinding.ListGatepassBinding

class GatePassAdapter :
    BaseAdapter<GatePass, ListGatepassBinding>(R.layout.list_gatepass) {
    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ListGatepassBinding>,
        position: Int
    ) {
        val gatePassList = items[position]
        holder.binding.gpList = gatePassList
    }
}