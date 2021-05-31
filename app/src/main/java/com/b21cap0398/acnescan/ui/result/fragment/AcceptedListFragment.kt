package com.b21cap0398.acnescan.ui.result.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0398.acnescan.R
import com.b21cap0398.acnescan.data.source.local.entity.AcneScanResult
import com.b21cap0398.acnescan.databinding.FragmentAcceptedListBinding
import com.b21cap0398.acnescan.ui.detail.DetailActivity
import com.b21cap0398.acnescan.ui.result.ResultViewModel
import com.b21cap0398.acnescan.utils.dummydata.DummyResultAcne
import com.b21cap0398.acnescan.viewmodel.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class AcceptedListFragment : Fragment() {

    private lateinit var binding: FragmentAcceptedListBinding

    private val auth = FirebaseAuth.getInstance()

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

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ResultViewModel::class.java]

        viewModel.getAllAcceptedAcneScanResult(auth.currentUser?.email!!).observe(viewLifecycleOwner, {
            val adapter = AcceptedListAdapter()
            adapter.setList(it)
            binding.rvAcceptedList.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter.setOnItemClickCallback(object : AcceptedListAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: AcneScanResult) {
                        val intent = Intent(requireContext(), DetailActivity::class.java)
                        intent.putExtra(DetailActivity.RESULT_ID, data.result_id)
                        startActivity(intent)
                    }
                })
                this.adapter = adapter
            }
        })
    }
}