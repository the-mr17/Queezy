package com.mr_17.queezy.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mr_17.queezy.R
import com.mr_17.queezy.adapter.SolutionsActivityRecyclerViewAdapter
import com.mr_17.queezy.model.Question
import com.mr_17.queezy.model.SolutionsActivityRecyclerViewModel

class SolutionsActivity : AppCompatActivity() {

    private var qAndA: Question? = null

    private var toolbar: Toolbar? = null

    private var selectedOptions: ArrayList<Int>? = null
    private var answer: ArrayList<Int>? = null

    private var recyclerView: RecyclerView? = null
    private var list: java.util.ArrayList<SolutionsActivityRecyclerViewModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solutions)

        InitializeFields()
        InitializeSolutionsRecyclerView()
    }

    private fun InitializeFields()
    {
        qAndA = intent.getSerializableExtra("qAndA") as Question?
        selectedOptions = intent.getSerializableExtra("selectedOptions") as ArrayList<Int>?
        answer = intent.getIntegerArrayListExtra("answers") as ArrayList<Int>

        recyclerView = findViewById(R.id.recycler_view)

        toolbar = findViewById(R.id.toolbar)
        toolbar!!.setNavigationOnClickListener { onBackPressed() }

    }

    private fun InitializeSolutionsRecyclerView() {
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        list = ArrayList()

        for (i in answer!!.indices) {
            list!!.add(SolutionsActivityRecyclerViewModel(
                "Question " + (i+1).toString() + " of " + answer!!.size,
                "",
                qAndA!!.question!!.get(i),
                qAndA!!.optA!!.get(i),
                qAndA!!.optB!!.get(i),
                qAndA!!.optC!!.get(i),
                qAndA!!.optD!!.get(i),
                answer!!.get(i),
                selectedOptions!!.get(i)
                )
            )
        }

        // This will pass the ArrayList to our Adapter
        val adapter = SolutionsActivityRecyclerViewAdapter(list!!)

        // Setting the Adapter with the recyclerview
        recyclerView!!.adapter = adapter
    }
}