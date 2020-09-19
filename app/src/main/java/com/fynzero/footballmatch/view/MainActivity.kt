package com.fynzero.footballmatch.view

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.fynzero.footballmatch.R
import com.fynzero.footballmatch.adapter.LeagueAdapter
import com.fynzero.footballmatch.model.League
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var idLeague: Array<String>
    private lateinit var logo: TypedArray
    private lateinit var nameLeague: Array<String>
    private lateinit var webLeague: Array<String>
    private lateinit var facebookLeague: Array<String>
    private val leagues = ArrayList<League>()
    private val leagueAdapter = LeagueAdapter(leagues)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup recyclerview
        rv_league.layoutManager = GridLayoutManager(this, 2)
        rv_league.setHasFixedSize(true)
        rv_league.adapter = leagueAdapter

        prepare()
        addItem()

        leagueAdapter.setOnItemClickCallback(object : LeagueAdapter.OnItemClickCallback {
            override fun onItemClicked(league: League) {
                val intent = Intent(this@MainActivity, DetailLeagueActivity::class.java)
                intent.putExtra(DetailLeagueActivity.EXTRA_LEAGUE, league)
                startActivity(intent)
            }
        })
    }

    private fun prepare() {
        idLeague = resources.getStringArray(R.array.idLeague)
        logo = resources.obtainTypedArray(R.array.logo)
        nameLeague = resources.getStringArray(R.array.league_name)
        webLeague = resources.getStringArray(R.array.league_web)
        facebookLeague = resources.getStringArray(R.array.league_facebook)
    }

    private fun addItem() {
        for (i in idLeague.indices) {
            val league = League(
                idLeague[i],
                logo.getResourceId(i, 0),
                nameLeague[i],
                webLeague[i],
                facebookLeague[i]
            )
            leagues.add(league)
        }
    }
}