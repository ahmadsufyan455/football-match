package com.fynzero.footballmatch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fynzero.footballmatch.R
import com.fynzero.footballmatch.adapter.NextMatchAdapter
import com.fynzero.footballmatch.model.NextMatch
import com.fynzero.footballmatch.viewmodel.NextMatchViewModel
import kotlinx.android.synthetic.main.fragment_next_match.*

class NextMatchFragment : Fragment() {

    companion object {
        private const val LEAGUE_ID = "league_id"

        fun getLeagueId(id: String): NextMatchFragment {
            val fragment = NextMatchFragment()
            val bundle = Bundle()
            bundle.putString(LEAGUE_ID, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val matchList = ArrayList<NextMatch>()
    private val nextMatchAdapter = NextMatchAdapter(matchList)
    private lateinit var nextMatchViewModel: NextMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get id
        val leagueId = arguments?.getString(LEAGUE_ID).toString()

        // setup rv
        rv_next_match.layoutManager = LinearLayoutManager(activity)
        rv_next_match.setHasFixedSize(true)
        nextMatchAdapter.notifyDataSetChanged()
        rv_next_match.adapter = nextMatchAdapter

        // get data view model
        nextMatchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(NextMatchViewModel::class.java)
        nextMatchViewModel.setMatch(leagueId)
        nextMatchViewModel.getMatch().observe(requireActivity(), { matches ->
            if (matches != null) {
                nextMatchAdapter.setData(matches)
                progressBar.visibility = View.GONE
            }
        })
    }
}