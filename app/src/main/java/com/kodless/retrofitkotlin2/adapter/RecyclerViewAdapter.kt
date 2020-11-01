package com.kodless.retrofitkotlin2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kodless.retrofitkotlin2.R
import com.kodless.retrofitkotlin2.model.CryptoModel

class RecyclerViewAdapter(
    private val cryptoList: ArrayList<CryptoModel>,
    private val listener: Listener
) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(cryptoModel: CryptoModel)
    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textView: TextView? = null
        var textView2: TextView? = null

        init {
            textView = view.findViewById(R.id.textView1)
            textView2 = view.findViewById(R.id.textView2)
        }

        fun setData(oAnkiVeri: String, position: Int) {

           /* textView?.setOnClickListener {
                val intent = Intent(it.context, MainActivity2::class.java)
                intent.putExtra("veri", oAnkiVeri)
                it.context.startActivity(intent)
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.setData(cryptoList[position].price, position)
        holder.textView?.text = cryptoList[position].price
        holder.textView2?.text = cryptoList[position].currency
        holder.itemView.setOnClickListener {
            listener.onItemClick(cryptoList[position])
        }
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}