package com.mr_17.queezy.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.mr_17.queezy.R
import com.mr_17.queezy.model.SolutionsActivityRecyclerViewModel
import org.w3c.dom.Text

class SolutionsActivityRecyclerViewAdapter(private val mList: List<SolutionsActivityRecyclerViewModel>) : RecyclerView.Adapter<SolutionsActivityRecyclerViewAdapter.ViewHolder>() {

    var parent: ViewGroup? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_solutions, parent, false)

        this.parent = parent

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = mList[position]

        holder.questionCountTextView.text = model.questionCount
        holder.questionStatusTextView.text = model.questionStatus
        holder.questionTextView.text = model.question
        holder.optATextView.text = model.optA
        holder.optBTextView.text = model.optB
        holder.optCTextView.text = model.optC
        holder.optDTextView.text = model.optD

        if(model.selectedOpt == 0) {
            holder.questionStatusTextView.setText("Skipped")
            holder.questionStatusTextView.setTextColor(AppCompatResources.getColorStateList(parent!!.context, R.color.grey))
            for(i in 1..4)
            {
                if(i == model.correctOpt)
                    holder.optionsTextView.get(i-1).backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.green)
                else
                    holder.optionsTextView.get(i-1).backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
            }
        }
        else if(model.correctOpt == model.selectedOpt) {
            holder.questionStatusTextView.setText("Correct")
            holder.questionStatusTextView.setTextColor(AppCompatResources.getColorStateList(parent!!.context, R.color.green))
            for(i in 1..4)
            {
                if(i == model.correctOpt)
                    holder.optionsTextView.get(i-1).backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.green)
                else
                    holder.optionsTextView.get(i-1).backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
            }
        }
        else {
            holder.questionStatusTextView.setText("Incorrect")
            holder.questionStatusTextView.setTextColor(AppCompatResources.getColorStateList(parent!!.context, R.color.dark_pink))
            for(i in 1..4)
            {
                if(i == model.correctOpt)
                    holder.optionsTextView.get(i-1).backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.green)
                else if( i == model.selectedOpt)
                    holder.optionsTextView.get(i-1).backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.dark_pink)
                else
                    holder.optionsTextView.get(i-1).backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
            }
        }

        /*when (model.correctOpt) {
            1 -> {
                holder.optATextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.green)
                holder.optBTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
                holder.optCTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
                holder.optDTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
            }
            2 -> {
                holder.optATextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
                holder.optBTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.green)
                holder.optCTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
                holder.optDTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
            }
            3 -> {
                holder.optATextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
                holder.optBTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
                holder.optCTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.green)
                holder.optDTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
            }
            4 -> {
                holder.optATextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
                holder.optBTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
                holder.optCTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.light_purple)
                holder.optDTextView.backgroundTintList = AppCompatResources.getColorStateList(parent!!.context, R.color.green)
            }
        }*/

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val questionCountTextView: TextView = itemView.findViewById(R.id.question_count)
        val questionStatusTextView: TextView = itemView.findViewById(R.id.question_status)
        val questionTextView: TextView = itemView.findViewById(R.id.question)
        val optATextView: TextView = itemView.findViewById(R.id.optA)
        val optBTextView: TextView = itemView.findViewById(R.id.optB)
        val optCTextView: TextView = itemView.findViewById(R.id.optC)
        val optDTextView: TextView = itemView.findViewById(R.id.optD)

        val optionsTextView = arrayListOf<TextView>(optATextView, optBTextView, optCTextView, optDTextView)
    }
}