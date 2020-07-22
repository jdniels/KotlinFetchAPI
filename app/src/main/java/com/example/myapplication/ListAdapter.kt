package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatTextView

class ListAdapter (private val context: Context, private val users: ArrayList<UserEntity>) : BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false)

        val userId = view.findViewById(R.id.user_id) as AppCompatTextView
        val name = view.findViewById(R.id.userName) as AppCompatTextView
        val userName = view.findViewById(R.id.user_name) as AppCompatTextView
        val userEmail = view.findViewById(R.id.user_email) as AppCompatTextView

        userId.text = users[position].id.toString()
        userName.text = users[position].username
        name.text = users[position].name
        userEmail.text = users[position].email

        return view
    }

    override fun getItem(position: Int): Any {
        return users[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return users.size
    }


}