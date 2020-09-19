package com.fynzero.footballmatch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fynzero.footballmatch.R
import com.fynzero.footballmatch.model.NextMatch
import kotlinx.android.synthetic.main.next_match_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class NextMatchAdapter(private val matchList: ArrayList<NextMatch>) :
    RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

    fun setData(list: ArrayList<NextMatch>) {
        matchList.clear()
        matchList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.next_match_item, parent, false)
        ))
    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(nextMatch: NextMatch) {
            with(itemView) {
                val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val inputDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                try {
                    val date = inputDate.parse(nextMatch.dateEventLocal)
                    val strDate = outputFormat.format(date!!)
                    txt_date.text = strDate
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                txt_home_name.text = nextMatch.strHomeTeam
                txt_away_name.text = nextMatch.strAwayTeam
                txt_stadium.text = nextMatch.strVenue
                txt_time.text = nextMatch.strTimeLocal
            }
        }
    }
}