package com.bffa3.myapplication.ui.result

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bffa3.myapplication.ui.result.fragment.AcceptedListFragment
import com.bffa3.myapplication.ui.result.fragment.PendingListFragment
import com.bffa3.myapplication.ui.result.fragment.RejectedListFragment

class ResultPageAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = AcceptedListFragment()
            1 -> fragment = PendingListFragment()
            2 -> fragment = RejectedListFragment()
        }

        return fragment as Fragment
    }
}