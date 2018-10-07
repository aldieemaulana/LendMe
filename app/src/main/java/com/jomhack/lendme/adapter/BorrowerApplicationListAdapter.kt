package com.jomhack.lendme.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.jomhack.lendme.R
import com.jomhack.lendme.R.id.btnSubmit
import com.jomhack.lendme.R.id.parent
import com.jomhack.lendme.model.Audit
import kotlinx.android.synthetic.main.item_list_history.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.coroutines.experimental.coroutineContext

class BorrowerApplicationListAdapter(private var audits: List<Audit?>, private var context: Context, private var isPay: Boolean) : RecyclerView.Adapter<BorrowerApplicationListAdapter.ViewHolder>() {

    var selectedItem : ViewHolder? = null

    override fun getItemCount(): Int {
        return audits.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textState: AppCompatTextView = itemView.textState
        val textDate: AppCompatTextView = itemView.textDate
        val textTime: AppCompatTextView = itemView.textTime
        val textAccount: AppCompatTextView = itemView.textAccount
        val textAmount: AppCompatTextView = itemView.textAmount
        val layoutParent: LinearLayout = itemView.parentLayout
        val btnSubmit: Button = itemView.btnSubmit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_history, parent, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val audit = audits[holder.adapterPosition]

        holder.textState.text = audit?.status
        holder.textDate.text = getDate(audit?.updateDate.toString())
        holder.textTime.text = getTime(audit?.updateDate.toString())
        holder.textAccount.text = "${audit?.pointOfInterest.toString()}% for ${audit?.numberOfMonth} month (s)"
        if (audit != null && audit.status.equals("Paid")) {
            holder.textAmount.isEnabled = true;

        } else if (audit != null && audit.status.equals("Waiting")) {
            holder.textAmount.isEnabled = false;
        } else {
            holder.textAmount.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
        }

        holder.textAmount.text = (if (audit?.bankinType.equals("RENT")) "+ " else "+ ") + "MYR ${audit?.amount.toString()}"


        holder.layoutParent.setOnClickListener {
            if (audit != null && audit.status.equals("Paid")) {
                selectedItem?.btnSubmit?.visibility = View.GONE
            } else if (audit != null && audit.status.equals("Waiting")) {
                selectedItem?.btnSubmit?.visibility = View.GONE
            } else {
                holder.btnSubmit.visibility = View.VISIBLE
            }

            selectedItem = holder
        }

        holder.btnSubmit.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hlb.com.my/en/personal-banking.html"));
            context.startActivity(browserIntent);
        }

    }

    private fun getDate(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("d MMM")
            val l = SimpleDateFormat("yyy-MM-dd h:m:s").parse(s)

            sdf.format(l)
        } catch (e: Exception) {
            ""
        }
    }

    private fun getTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("h:s")
            val l = SimpleDateFormat("yyy-MM-dd h:m:s").parse(s)

            sdf.format(l)
        } catch (e: Exception) {
            ""
        }
    }
}
