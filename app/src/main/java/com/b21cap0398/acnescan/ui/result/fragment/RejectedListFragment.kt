package com.b21cap0398.acnescan.ui.result.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.FragmentRejectedListBinding
import com.b21cap0398.acnescan.ui.detail.DetailActivity
import com.b21cap0398.acnescan.utils.dummydata.DummyResultAcne

class RejectedListFragment : Fragment() {

    private lateinit var binding: FragmentRejectedListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rejected_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRejectedListBinding.bind(view)

        hideEmptyListWarning()
        binding.progressBar.visibility = View.VISIBLE

        val adapter = RejectedListAdapter()
        binding.rvRejectedList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter.setOnItemClickCallback(object : RejectedListAdapter.OnItemClickCallback {
                override fun onItemClicked() {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    startActivity(intent)
                }

            })
            this.adapter = adapter
        }

        if (adapter.itemCount <= 0) {
            showEmptyListWarning()
        }
        binding.progressBar.visibility = View.GONE
    }

    private fun showEmptyListWarning() {
        val emptyListWarning = binding.warningEmptyList.root
        emptyListWarning.visibility = View.VISIBLE
    }

    private fun hideEmptyListWarning() {
        val emptyListWarning = binding.warningEmptyList.root
        emptyListWarning.visibility = View.GONE
    }
}