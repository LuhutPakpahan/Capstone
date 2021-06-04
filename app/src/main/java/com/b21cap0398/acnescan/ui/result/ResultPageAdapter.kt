package com.b21cap0398.acnescan.ui.result

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.b21cap0398.acnescan.ui.result.fragment.AcceptedListFragment
import com.b21cap0398.acnescan.ui.result.fragment.PendingListFragment
import com.b21cap0398.acnescan.ui.result.fragment.RejectedListFragment

class ResultPageAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = AcceptedListFragment()
            1 -> fragment = RejectedListFragment()
        }

        return fragment as Fragment
    }
}