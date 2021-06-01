package com.b21cap0398.acnescan.ui.specificdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.data.source.local.entity.MedicineInformation
import com.b21cap0398.acnescan.databinding.ItemCureAcneBinding

class RecomendedMedicineAdapter :
    RecyclerView.Adapter<RecomendedMedicineAdapter.CustomeViewHolder>() {

    private val recomendedMedicines = ArrayList<MedicineInformation>()

    fun setList(list: List<MedicineInformation>) {
        recomendedMedicines.clear()
        recomendedMedicines.addAll(list)
    }

    inner class CustomeViewHolder(val binding: ItemCureAcneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MedicineInformation) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomeViewHolder {
        return CustomeViewHolder(
            ItemCureAcneBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomeViewHolder, position: Int) {
        holder.bind(recomendedMedicines[position])
    }

    override fun getItemCount(): Int {
        return recomendedMedicines.size
    }

}