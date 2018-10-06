package com.jomhack.lendme.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.jomhack.lendme.R
import com.jomhack.lendme.model.User
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_card_contact.view.*

class UserCardAdapter(private var users: List<User?>) : RecyclerView.Adapter<UserCardAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: AppCompatTextView = itemView.textName
        val imageFoto: CircleImageView = itemView.imageFoto
        val layoutParent: LinearLayout = itemView.parentLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_contact, parent, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[holder.adapterPosition]

        holder.textName.text = "${user?.firstName} ${user?.lastName}"
    }
}
