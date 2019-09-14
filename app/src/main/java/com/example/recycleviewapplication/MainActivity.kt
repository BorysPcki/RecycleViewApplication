package com.example.recycleviewapplication

import android.content.ContentResolver
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        val contactsList = arrayListOf<String>()
        val numberList = arrayListOf<String>()
        val idList = arrayListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null)
            screen.text = savedInstanceState.getString("Number")
        else
            screen.text = "${Random().nextInt(100)}"

        Log.d("ZMIANA", "Wykonala sie metoda onCreate")

        //Element odpowiedzialny za ustawienie widokow w formie listy
        var recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        //Element odpowiedzielny za polaczenie miedzy danymi, a widokami
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MyAdapter()

        val contentResolver = contentResolver
        var cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null, null
        )

        try {

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    var id =
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                    idList.add(id)
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                    contactsList.add(name)

                    readPhoneNumber(contentResolver, id)
                }


            }

        } finally {
            cursor?.close()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Number",screen.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        idList.clear()
        numberList.clear()
        contactsList.clear()

        Log.d("ZMIANA", "Wykonala sie metoda onDestroy")

    }

    fun readPhoneNumber(contentResolver: ContentResolver, id: String) {

        val phoneCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?", arrayOf(id), null
        )

        if (phoneCursor != null) {
            if (phoneCursor.moveToFirst()) {
                var number = phoneCursor.getString(
                    phoneCursor
                        .getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
                )
                numberList.add(number)
            } else
                numberList.add("No number")
        }

        phoneCursor?.close()


    }
}
