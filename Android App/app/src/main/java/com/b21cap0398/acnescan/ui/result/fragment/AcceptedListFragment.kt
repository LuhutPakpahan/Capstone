package com.b21cap0398.acnescan.ui.result.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.databinding.FragmentAcceptedListBinding
import com.b21cap0398.acnescan.ui.detail.DetailActivity
import com.b21cap0398.acnescan.utils.DummyResultAcne

class AcceptedListFragment : Fragment() {

    private lateinit var binding: FragmentAcceptedListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accepted_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAcceptedListBinding.bind(view)

        val adapter = AcceptedListAdapter()
        adapter.setList(DummyResultAcne.addDummyResultAcne())
        binding.rvAcceptedList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter.setOnItemClickCallback(object : AcceptedListAdapter.OnItemClickCallback {
                override fun onItemClicked() {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    startActivity(intent)
                }
            })
            this.adapter = adapter
        }
    }
}