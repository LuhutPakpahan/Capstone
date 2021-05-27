package com.bffa3.myapplication.ui.result.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bffa3.myapplication.R
import com.bffa3.myapplication.data.entity.ResultAcne
import com.bffa3.myapplication.databinding.FragmentAcceptedListBinding
import com.bffa3.myapplication.utils.DummyResultAcne

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
            this.adapter = adapter
        }
    }
}