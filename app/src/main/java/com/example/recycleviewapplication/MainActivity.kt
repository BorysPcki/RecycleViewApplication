package com.example.recycleviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //czemu potrzebne ?
        var recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        //tworzy liste
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MyAdapter()
    }
}
