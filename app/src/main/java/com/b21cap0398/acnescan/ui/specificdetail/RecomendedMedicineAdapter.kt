package com.b21cap0398.acnescan.ui.specificdetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.data.source.local.entity.MedicineInformation
import com.b21cap0398.acnescan.databinding.ItemCureAcneBinding
import com.b21cap0398.acnescan.utils.helper.FirebaseStorageEndpointHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class RecomendedMedicineAdapter :
    RecyclerView.Adapter<RecomendedMedicineAdapter.CustomeViewHolder>() {

    private val recomendedMedicines = ArrayList<MedicineInformation>()

    fun setList(list: List<MedicineInformation>) {
        recomendedMedicines.clear()
        recomendedMedicines.addAll(list)
    }

    inner class CustomeViewHolder(val binding: ItemCureAcneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: MedicineInformation) {
            binding.apply {
                tvNameOfAcne.text = data.name
                tvPriceOfMedicine.text = "Price: " + data.price
                FirebaseStorageEndpointHelper.getDownloadUrlOfReference(data.image_path).addOnSuccessListener {
                    Glide.with(itemView.context)
                        .load(it)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivImageCureAcne)
                }
            }
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