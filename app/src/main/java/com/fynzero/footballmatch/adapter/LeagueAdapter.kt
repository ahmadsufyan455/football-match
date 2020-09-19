package com.fynzero.footballmatch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fynzero.footballmatch.R
import com.fynzero.footballmatch.model.League
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.league_item.view.*

class LeagueAdapter(private val leagueList: ArrayList<League>) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false)
        ))
    }

    override fun getItemCount(): Int = leagueList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(leagueList[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(leagueList[holder.adapterPosition])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(league: League) {
            with(itemView) {
                Picasso.get().load(league.logo).into(img_logo)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(league: League)
    }
}