package com.example.purworejoapik.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.purworejoapik.data.entity.User
import com.example.purworejoapik.R

class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fullName: TextView
        var email: TextView
        var phone: TextView
        var date: TextView
        var time: TextView

        init {
            fullName = view.findViewById(R.id.full_name)
            email = view.findViewById(R.id.email)
            phone = view.findViewById(R.id.phone)
            date = view.findViewById(R.id.date)
            time = view.findViewById(R.id.time)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_lomba, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fullName.text = list[position].fullName
        holder.email.text = list[position].email
        holder.phone.text = list[position].phone
        holder.date.text = list[position].date
        holder.time.text = list[position].time
    }
}