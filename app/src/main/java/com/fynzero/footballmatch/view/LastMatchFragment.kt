package com.fynzero.footballmatch.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fynzero.footballmatch.R
import com.fynzero.footballmatch.adapter.LastMatchAdapter
import com.fynzero.footballmatch.model.LastMatch
import com.fynzero.footballmatch.viewmodel.LastMatchViewModel
import kotlinx.android.synthetic.main.fragment_last_match.*

class LastMatchFragment : Fragment() {

    companion object {
        private const val LEAGUE_ID = "league_id"

        fun getLeagueId(id: String): LastMatchFragment {
            val fragment = LastMatchFragment()
            val bundle = Bundle()
            bundle.putString(LEAGUE_ID, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val lastMatch = ArrayList<LastMatch>()
    private val lastMatchAdapter = LastMatchAdapter(lastMatch)
    private lateinit var lastMatchViewModel: LastMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leagueId = arguments?.getString(LEAGUE_ID).toString()

        // setup rv
        rv_last_match.layoutManager = LinearLayoutManager(activity)
        rv_last_match.setHasFixedSize(true)
        lastMatchAdapter.notifyDataSetChanged()
        rv_last_match.adapter = lastMatchAdapter

        // get data from view model
        lastMatchViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(LastMatchViewModel::class.java)
        lastMatchViewModel.setMatch(leagueId)
        lastMatchViewModel.getMatch().observe(requireActivity(), { listMatch ->
            if (listMatch != null) {
                lastMatchAdapter.setData(listMatch)
                progressBar.visibility = View.GONE
            }
        })

        lastMatchAdapter.setOnItemClickCallback(object : LastMatchAdapter.OnItemCLickCallback {
            override fun onItemClicked(lastMatch: LastMatch) {
                val intent = Intent(context, DetailMatchActivity::class.java)
                intent.putExtra(DetailMatchActivity.EXTRA_DETAIL, lastMatch)
                startActivity(intent)
            }
        })
    }
}