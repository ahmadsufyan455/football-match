package com.fynzero.footballmatch.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fynzero.footballmatch.R
import com.fynzero.footballmatch.model.LastMatch
import com.fynzero.footballmatch.viewmodel.DetailMatchViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var detailMatchViewModel: DetailMatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        val lastMatch = intent.getParcelableExtra<LastMatch>(EXTRA_DETAIL)
        val idEvent = lastMatch?.idEvent.toString()
        val idHomeTeam = lastMatch?.idHomeTeam.toString()
        val idAwayTeam = lastMatch?.idAwayTeam.toString()

        detailMatchViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMatchViewModel::class.java]

        detailMatchViewModel.setTeamHome(idHomeTeam)
        detailMatchViewModel.setTeamAway(idAwayTeam)

        detailMatchViewModel.setDetail(idEvent)
        detailMatchViewModel.getDetail().observe(this, { detailMatch ->
            if (detailMatch != null) {

                // get logo home
                detailMatchViewModel.getTeamHome().observe(this, { teams ->
                    Picasso.get().load(teams[0].strTeamBadge).into(img_logo_home)
                })

                // get logo away
                detailMatchViewModel.getTeamAway().observe(this, { teams ->
                    Picasso.get().load(teams[0].strTeamBadge).into(img_logo_away)
                })

                val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val inputDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                try {
                    val date = inputDate.parse(detailMatch[0].dateEventLocal)
                    val strDate = outputFormat.format(date!!)
                    txt_date.text = strDate
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                txt_league_name.text = detailMatch[0].strLeague
                txt_score_home.text = detailMatch[0].intHomeScore
                txt_score_away.text = detailMatch[0].intAwayScore
                txt_stadium.text = detailMatch[0].strVenue
                txt_home_goals.text = detailMatch[0].strHomeGoalDetails
                txt_away_goals.text = detailMatch[0].strAwayGoalDetails
            }
        })

        ic_back.setOnClickListener { finish() }
    }
}