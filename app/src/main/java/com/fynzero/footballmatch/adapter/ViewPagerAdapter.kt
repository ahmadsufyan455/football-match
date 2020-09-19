package com.fynzero.footballmatch.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fynzero.footballmatch.view.LastMatchFragment
import com.fynzero.footballmatch.view.NextMatchFragment

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var leagueId: String? = null

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = LastMatchFragment.getLeagueId(leagueId.toString())
            1 -> fragment = NextMatchFragment.getLeagueId(leagueId.toString())
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            else -> "Next Match"
        }
    }

    override fun getCount(): Int = 2
}