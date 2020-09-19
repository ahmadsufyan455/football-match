package com.fynzero.footballmatch.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fynzero.footballmatch.R
import com.fynzero.footballmatch.adapter.ViewPagerAdapter
import com.fynzero.footballmatch.model.League
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_LEAGUE = "extra_league"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        // get object league
        val league = intent.getParcelableExtra<League>(EXTRA_LEAGUE)
        txt_league_name.text = league?.nameLeague
        txt_web_url.text = league?.webLeague
        txt_facebook.text = league?.facebookLeague
        Picasso.get().load(league?.logo!!).into(img_logo_detail)

        // setup viewpager
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.leagueId = league.idLeague
        viewPager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewPager)

        ic_back.setOnClickListener { finish() }
    }
}