package com.example.recycleviewapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_row.view.*

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val contactRow = layoutInflater.inflate(R.layout.contact_row, viewGroup, false)

        return MyViewHolder(contactRow)
    }

    override fun getItemCount(): Int {
        return ContactDataBase.contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name = holder.view.contact_name
        val number = holder.view.contact_number

        name.text = ContactDataBase.contactList[position]
        number.text = ContactDataBase.contactNumberList[position]
    }

}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)