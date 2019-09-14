package com.example.recycleviewapplication

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewapplication.MainActivity.Companion.contactsList
import com.example.recycleviewapplication.MainActivity.Companion.idList
import com.example.recycleviewapplication.MainActivity.Companion.numberList
import kotlinx.android.synthetic.main.contact_row.view.*

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val contactRow = layoutInflater.inflate(R.layout.contact_row, viewGroup, false)

        return MyViewHolder(contactRow)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name = holder.view.contact_name
        val number = holder.view.contact_number
        val callBT = holder.view.call_BT
        val smsBT = holder.view.sms_BT
        //val contentResolver = holder.itemView.context.contentResolver

        holder.itemView.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                val contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                    idList[holder.adapterPosition].toLong())

                val editIntent = Intent()
                    editIntent.action = Intent.ACTION_EDIT
                    editIntent.data = contactUri

                startActivity(holder.itemView.context, editIntent, null)

                return false
            }
        })


        name.text = contactsList[position]
        number.text = numberList[position]

        smsBT.setOnClickListener{
            val smsIntent = Intent()
            if(numberList[position] == "No number") {
                Toast.makeText(holder.itemView.context, "Invalid number or no number", Toast.LENGTH_SHORT)
                    .show()
            }

            else {
                smsIntent.data = Uri.parse("sms: " + numberList[position])
                smsIntent.action = Intent.ACTION_VIEW
                startActivity(holder.itemView.context, smsIntent, null)
            }
        }

        callBT.setOnClickListener {
            val callIntent = Intent()
            if(numberList[position] == "No number") {
                Toast.makeText(holder.itemView.context, "Invalid number or no number", Toast.LENGTH_SHORT)
                    .show()
            }

            else {
                callIntent.data = Uri.parse("tel: " + numberList[position])
                callIntent.action = Intent.ACTION_CALL
                startActivity(holder.itemView.context, callIntent, null)
            }
        }


    }

}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)