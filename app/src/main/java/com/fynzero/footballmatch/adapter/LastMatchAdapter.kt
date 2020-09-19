package com.fynzero.footballmatch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fynzero.footballmatch.R
import com.fynzero.footballmatch.model.LastMatch
import kotlinx.android.synthetic.main.last_match_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class LastMatchAdapter(private val matchList: ArrayList<LastMatch>) :
    RecyclerView.Adapter<LastMatchAdapter.ViewHolder>() {

    fun setData(list: ArrayList<LastMatch>) {
        matchList.clear()
        matchList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.last_match_item, parent, false)
        ))
    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(lastMatch: LastMatch) {
            with(itemView) {
                val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val inputDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                try {
                    val date = inputDate.parse(lastMatch.dateEventLocal)
                    val strDate = outputFormat.format(date!!)
                    txt_date.text = strDate
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                txt_home_name.text = lastMatch.strHomeTeam
                txt_away_name.text = lastMatch.strAwayTeam
                txt_score_home.text = lastMatch.intHomeScore
                txt_score_away.text = lastMatch.intAwayScore
                txt_stadium.text = lastMatch.strVenue
            }
        }
    }
}